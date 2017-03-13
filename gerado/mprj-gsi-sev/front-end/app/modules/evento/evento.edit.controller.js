(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:eventoController
     * @description
     * # eventoController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('eventoEditController', eventoEditController);

        eventoEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'eventoService', 'arquivoRelatorioService', 'eleicaoService', 'tipoEventoService'];

        function eventoEditController($scope, $state, $stateParams , $modal, $location, jsog, eventoService , arquivoRelatorioService, eleicaoService, tipoEventoService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'evento';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = eventoService.create();
            vm.searchSelects = eventoService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************evento
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                eventoService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "eventoEdit.main", icon: "mdi-home", visible: "true"});
            // Many to One:
            vm.tabs.push({heading: "Eleicao", route: "eventoEdit.eleicao", icon: "mdi-eye", visible: $stateParams.id});
            // One to Many:
             vm.tabs.push({heading: "Arquivo Relatorio ", route: "eventoEdit.arquivoRelatorioCollection", icon: "mdi-label", visible: $stateParams.id});

            vm.tabSelected = 'eventoEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************
             $scope.selectedArquivoRelatorio = null;

            $scope.getArquivoRelatorio = function ( arquivoRelatorioId ) {
                arquivoRelatorioService.get( {'id':arquivoRelatorioId} , function (data) {
                    $scope.selectedArquivoRelatorio = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/evento/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'ArquivoRelatorio' && $stateParams.arquivoRelatorioId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.arquivoRelatorioId) {
                    $scope.getArquivoRelatorio( $stateParams.arquivoRelatorioId );
                }
            }

            $scope.findArquivoRelatorio = function() {
                console.log('Realizando busca de 1 x N( arquivoRelatorio.evento.id):' + $stateParams.id);

                $scope.gridOptions = arquivoRelatorioService.createGridOptions(true);
                $scope.arquivoRelatorioPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                arquivoRelatorioService.search( {'content':{ 'evento':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
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
                console.log('Realizando busca de 1 x N( arquivoRelatorio.evento.id):' + $scope.entity.id);
                $scope.resultPage = null;
                arquivoRelatorioService.search( {'content':{ 'evento':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
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
                        $location.path('/evento/'+ $stateParams.id + '/ArquivoRelatorio');
                    }
                });
            };
            $scope.editArquivoRelatorio = function (obj) {
                $scope.selectedArquivoRelatorio = obj
                $location.path('/evento/'+ $scope.entity.id +'/ArquivoRelatorio/' + obj.id );
            };
            $scope.createArquivoRelatorio = function (obj) {
                $scope.selectedArquivoRelatorio = arquivoRelatorioService.create();
                $scope.selectedArquivoRelatorio.evento = $scope.entity;
                $location.path('/evento/'+ $scope.entity.id +'/ArquivoRelatorio/');
            };

            $scope.arquivoRelatorioPaginationChanged = function(newPage) {
                $scope.arquivoRelatorioPagination.currentPage = newPage;
                $scope.findArquivoRelatorio();
            };

            $scope.isArquivoRelatorioActive = ($stateParams.child === 'ArquivoRelatorio' && $stateParams.arquivoRelatorioId) ;


            // Selects to Fullfill select boxes
            // Begin block
            tipoEventoService.findAll( {page:1 , size:vm.maxSelectBoxSize}, function (data) {
                vm.tipoEventoList = jsog.decode(data.content);
            });

        }

})();
