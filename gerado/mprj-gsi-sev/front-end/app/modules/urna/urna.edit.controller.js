(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:urnaController
     * @description
     * # urnaController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('urnaEditController', urnaEditController);

        urnaEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'urnaService', 'cedulaService', 'eleicaoService'];

        function urnaEditController($scope, $state, $stateParams , $modal, $location, jsog, urnaService , cedulaService, eleicaoService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'urna';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = urnaService.create();
            vm.searchSelects = urnaService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************urna
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                urnaService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "urnaEdit.main", icon: "mdi-home", visible: "true"});
            // Many to One:
            vm.tabs.push({heading: "Eleicao", route: "urnaEdit.eleicao", icon: "mdi-eye", visible: $stateParams.id});
            // One to Many:
             vm.tabs.push({heading: "Cedula ", route: "urnaEdit.cedulaCollection", icon: "mdi-label", visible: $stateParams.id});

            vm.tabSelected = 'urnaEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************
             $scope.selectedCedula = null;

            $scope.getCedula = function ( cedulaId ) {
                cedulaService.get( {'id':cedulaId} , function (data) {
                    $scope.selectedCedula = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/urna/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'Cedula' && $stateParams.cedulaId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.cedulaId) {
                    $scope.getCedula( $stateParams.cedulaId );
                }
            }

            $scope.findCedula = function() {
                console.log('Realizando busca de 1 x N( cedula.urna.id):' + $stateParams.id);

                $scope.gridOptions = cedulaService.createGridOptions(true);
                $scope.cedulaPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                cedulaService.search( {'content':{ 'urna':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchCedula = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( cedula.urna.id):' + $scope.entity.id);
                $scope.resultPage = null;
                cedulaService.search( {'content':{ 'urna':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeCedula = function (obj){
                console.log('Deleting cedula with id:' + obj.id);
                cedulaService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveCedula = function (){
                console.log('Requesting save on ${cedula}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                cedulaService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = cedulaService.create();
                    if (!$scope.editMode) {
                        $scope.searchCedula();
                    } else {
                        $location.path('/urna/'+ $stateParams.id + '/Cedula');
                    }
                });
            };
            $scope.editCedula = function (obj) {
                $scope.selectedCedula = obj
                $location.path('/urna/'+ $scope.entity.id +'/Cedula/' + obj.id );
            };
            $scope.createCedula = function (obj) {
                $scope.selectedCedula = cedulaService.create();
                $scope.selectedCedula.urna = $scope.entity;
                $location.path('/urna/'+ $scope.entity.id +'/Cedula/');
            };

            $scope.cedulaPaginationChanged = function(newPage) {
                $scope.cedulaPagination.currentPage = newPage;
                $scope.findCedula();
            };

            $scope.isCedulaActive = ($stateParams.child === 'Cedula' && $stateParams.cedulaId) ;


            // Selects to Fullfill select boxes
            // Begin block

        }

})();
