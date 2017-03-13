(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:tipoRelatorioController
     * @description
     * # tipoRelatorioController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('tipoRelatorioEditController', tipoRelatorioEditController);

        tipoRelatorioEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'tipoRelatorioService', 'arquivoRelatorioService'];

        function tipoRelatorioEditController($scope, $state, $stateParams , $modal, $location, jsog, tipoRelatorioService , arquivoRelatorioService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'tipoRelatorio';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = tipoRelatorioService.create();
            vm.searchSelects = tipoRelatorioService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************tipoRelatorio
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                tipoRelatorioService.get( {'id':id} , function (data) {
                    vm.entity = data;                           // set managed entity from service
                    vm.original = angular.copy(vm.entity);                // backup original values;
                    vm.searchSelects.fromEntity($scope.entity); // update search selects values;
                }, function (error){
                    console.log(error);                             // TODO:
                });
            };

            vm.clearForm = function () {
                vm.entity = angular.copy(vm.original);
            };

            // ************************************
            // watches
            // ************************************
            vm.changeTab = function (tab) {
                $state.go(tab);
                vm.tabSelected = tab;
            }

            // ****************************************
            // Controller init (on load) service calls
            // ****************************************
            vm.tabs = [];
            vm.tabs.push({heading: "Principal", route: "tipoRelatorioEdit.main", icon: "mdi-home", visible: "true"});
            // Many to One:
            // One to Many:
             vm.tabs.push({heading: "Arquivo Relatorio ", route: "tipoRelatorioEdit.arquivoRelatorioCollection", icon: "mdi-label", visible: $stateParams.id});

            vm.tabSelected = 'tipoRelatorioEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************
             $scope.selectedArquivoRelatorio = null;

            $scope.getArquivoRelatorio = function ( arquivoRelatorioId ) {
                arquivoRelatorioService.get( {'id':arquivoRelatorioId} , function (data) {
                    $scope.selectedArquivoRelatorio = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/tipoRelatorio/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'ArquivoRelatorio' && $stateParams.arquivoRelatorioId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.arquivoRelatorioId) {
                    $scope.getArquivoRelatorio( $stateParams.arquivoRelatorioId );
                }
            }

            $scope.findArquivoRelatorio = function() {
                console.log('Realizando busca de 1 x N( arquivoRelatorio.tipoRelatorio.id):' + $stateParams.id);

                $scope.gridOptions = arquivoRelatorioService.createGridOptions(true);
                $scope.arquivoRelatorioPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                arquivoRelatorioService.search( {'content':{ 'tipoRelatorio':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchArquivoRelatorio = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( arquivoRelatorio.tipoRelatorio.id):' + $scope.entity.id);
                $scope.resultPage = null;
                arquivoRelatorioService.search( {'content':{ 'tipoRelatorio':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeArquivoRelatorio = function (obj){
                console.log('Deleting arquivoRelatorio with id:' + obj.id);
                arquivoRelatorioService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveArquivoRelatorio = function (){
                console.log('Requesting save on ${arquivoRelatorio}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                arquivoRelatorioService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = arquivoRelatorioService.create();
                    if (!$scope.editMode) {
                        $scope.searchArquivoRelatorio();
                    } else {
                        $location.path('/tipoRelatorio/'+ $stateParams.id + '/ArquivoRelatorio');
                    }
                });
            };
            $scope.editArquivoRelatorio = function (obj) {
                $scope.selectedArquivoRelatorio = obj
                $location.path('/tipoRelatorio/'+ $scope.entity.id +'/ArquivoRelatorio/' + obj.id );
            };
            $scope.createArquivoRelatorio = function (obj) {
                $scope.selectedArquivoRelatorio = arquivoRelatorioService.create();
                $scope.selectedArquivoRelatorio.tipoRelatorio = $scope.entity;
                $location.path('/tipoRelatorio/'+ $scope.entity.id +'/ArquivoRelatorio/');
            };

            $scope.arquivoRelatorioPaginationChanged = function(newPage) {
                $scope.arquivoRelatorioPagination.currentPage = newPage;
                $scope.findArquivoRelatorio();
            };

            $scope.isArquivoRelatorioActive = ($stateParams.child === 'ArquivoRelatorio' && $stateParams.arquivoRelatorioId) ;


            // Selects to Fullfill select boxes
            // Begin block

        }

})();
