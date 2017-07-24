(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:tipoParametroController
     * @description
     * # tipoParametroController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('tipoParametroEditController', tipoParametroEditController);

        tipoParametroEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'tipoParametroService', 'parametroService'];

        function tipoParametroEditController($scope, $state, $stateParams , $modal, $location, jsog, tipoParametroService , parametroService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'tipoParametro';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = tipoParametroService.create();
            vm.searchSelects = tipoParametroService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************tipoParametro
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                tipoParametroService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "tipoParametroEdit.main", icon: "mdi-home", visible: "true"});
            // Many to One:
            // One to Many:
             vm.tabs.push({heading: "Parametro ", route: "tipoParametroEdit.parametroCollection", icon: "mdi-label", visible: $stateParams.id});

            vm.tabSelected = 'tipoParametroEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************
             $scope.selectedParametro = null;

            $scope.getParametro = function ( parametroId ) {
                parametroService.get( {'id':parametroId} , function (data) {
                    $scope.selectedParametro = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/tipoParametro/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'Parametro' && $stateParams.parametroId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.parametroId) {
                    $scope.getParametro( $stateParams.parametroId );
                }
            }

            $scope.findParametro = function() {
                console.log('Realizando busca de 1 x N( parametro.tipoParametro.id):' + $stateParams.id);

                $scope.gridOptions = parametroService.createGridOptions(true);
                $scope.parametroPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                parametroService.search( {'content':{ 'tipoParametro':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchParametro = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( parametro.tipoParametro.id):' + $scope.entity.id);
                $scope.resultPage = null;
                parametroService.search( {'content':{ 'tipoParametro':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeParametro = function (obj){
                console.log('Deleting parametro with id:' + obj.id);
                parametroService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveParametro = function (){
                console.log('Requesting save on ${parametro}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                parametroService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = parametroService.create();
                    if (!$scope.editMode) {
                        $scope.searchParametro();
                    } else {
                        $location.path('/tipoParametro/'+ $stateParams.id + '/Parametro');
                    }
                });
            };
            $scope.editParametro = function (obj) {
                $scope.selectedParametro = obj
                $location.path('/tipoParametro/'+ $scope.entity.id +'/Parametro/' + obj.id );
            };
            $scope.createParametro = function (obj) {
                $scope.selectedParametro = parametroService.create();
                $scope.selectedParametro.tipoParametro = $scope.entity;
                $location.path('/tipoParametro/'+ $scope.entity.id +'/Parametro/');
            };

            $scope.parametroPaginationChanged = function(newPage) {
                $scope.parametroPagination.currentPage = newPage;
                $scope.findParametro();
            };

            $scope.isParametroActive = ($stateParams.child === 'Parametro' && $stateParams.parametroId) ;


            // Selects to Fullfill select boxes
            // Begin block

        }

})();
