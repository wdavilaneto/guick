(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:parametroController
     * @description
     * # parametroController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('parametroEditController', parametroEditController);

        parametroEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'parametroService', 'eleicaoParametroService', 'tipoParametroService'];

        function parametroEditController($scope, $state, $stateParams , $modal, $location, jsog, parametroService , eleicaoParametroService, tipoParametroService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'parametro';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = parametroService.create();
            vm.searchSelects = parametroService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************parametro
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                parametroService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "parametroEdit.main", icon: "mdi-home", visible: "true"});
            // Many to One:
            // One to Many:
             vm.tabs.push({heading: "Eleicao Parametro ", route: "parametroEdit.eleicaoParametroCollection", icon: "mdi-label", visible: $stateParams.id});

            vm.tabSelected = 'parametroEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************
             $scope.selectedEleicaoParametro = null;

            $scope.getEleicaoParametro = function ( eleicaoParametroId ) {
                eleicaoParametroService.get( {'id':eleicaoParametroId} , function (data) {
                    $scope.selectedEleicaoParametro = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/parametro/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'EleicaoParametro' && $stateParams.eleicaoParametroId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.eleicaoParametroId) {
                    $scope.getEleicaoParametro( $stateParams.eleicaoParametroId );
                }
            }

            $scope.findEleicaoParametro = function() {
                console.log('Realizando busca de 1 x N( eleicaoParametro.parametro.id):' + $stateParams.id);

                $scope.gridOptions = eleicaoParametroService.createGridOptions(true);
                $scope.eleicaoParametroPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                eleicaoParametroService.search( {'content':{ 'parametro':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchEleicaoParametro = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( eleicaoParametro.parametro.id):' + $scope.entity.id);
                $scope.resultPage = null;
                eleicaoParametroService.search( {'content':{ 'parametro':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeEleicaoParametro = function (obj){
                console.log('Deleting eleicaoParametro with id:' + obj.id);
                eleicaoParametroService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveEleicaoParametro = function (){
                console.log('Requesting save on ${eleicaoParametro}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                eleicaoParametroService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = eleicaoParametroService.create();
                    if (!$scope.editMode) {
                        $scope.searchEleicaoParametro();
                    } else {
                        $location.path('/parametro/'+ $stateParams.id + '/EleicaoParametro');
                    }
                });
            };
            $scope.editEleicaoParametro = function (obj) {
                $scope.selectedEleicaoParametro = obj
                $location.path('/parametro/'+ $scope.entity.id +'/EleicaoParametro/' + obj.id );
            };
            $scope.createEleicaoParametro = function (obj) {
                $scope.selectedEleicaoParametro = eleicaoParametroService.create();
                $scope.selectedEleicaoParametro.parametro = $scope.entity;
                $location.path('/parametro/'+ $scope.entity.id +'/EleicaoParametro/');
            };

            $scope.eleicaoParametroPaginationChanged = function(newPage) {
                $scope.eleicaoParametroPagination.currentPage = newPage;
                $scope.findEleicaoParametro();
            };

            $scope.isEleicaoParametroActive = ($stateParams.child === 'EleicaoParametro' && $stateParams.eleicaoParametroId) ;


            // Selects to Fullfill select boxes
            // Begin block
            tipoParametroService.findAll( {page:1 , size:vm.maxSelectBoxSize}, function (data) {
                vm.tipoParametroList = jsog.decode(data.content);
            });

        }

})();
