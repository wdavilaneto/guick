(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:tipoEventoController
     * @description
     * # tipoEventoController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('tipoEventoEditController', tipoEventoEditController);

        tipoEventoEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'tipoEventoService', 'eventoService'];

        function tipoEventoEditController($scope, $state, $stateParams , $modal, $location, jsog, tipoEventoService , eventoService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'tipoEvento';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = tipoEventoService.create();
            vm.searchSelects = tipoEventoService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************tipoEvento
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                tipoEventoService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "tipoEventoEdit.main", icon: "mdi-home", visible: "true"});
             vm.tabs.push({heading: "Evento ", route: "tipoEventoEdit.eventoCollection", icon: "mdi-label", visible: $stateParams.id});

            vm.tabSelected = 'tipoEventoEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************
             $scope.selectedEvento = null;

            $scope.getEvento = function ( eventoId ) {
                eventoService.get( {'id':eventoId} , function (data) {
                    $scope.selectedEvento = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/tipoEvento/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'Evento' && $stateParams.eventoId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.eventoId) {
                    $scope.getEvento( $stateParams.eventoId );
                }
            }

            $scope.findEvento = function() {
                console.log('Realizando busca de 1 x N( evento.tipoEvento.id):' + $stateParams.id);

                $scope.gridOptions = eventoService.createGridOptions(true);
                $scope.eventoPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                eventoService.search( {'content':{ 'tipoEvento':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchEvento = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( evento.tipoEvento.id):' + $scope.entity.id);
                $scope.resultPage = null;
                eventoService.search( {'content':{ 'tipoEvento':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeEvento = function (obj){
                console.log('Deleting evento with id:' + obj.id);
                eventoService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveEvento = function (){
                console.log('Requesting save on ${evento}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                eventoService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = eventoService.create();
                    if (!$scope.editMode) {
                        $scope.searchEvento();
                    } else {
                        $location.path('/tipoEvento/'+ $stateParams.id + '/Evento');
                    }
                });
            };
            $scope.editEvento = function (obj) {
                $scope.selectedEvento = obj
                $location.path('/tipoEvento/'+ $scope.entity.id +'/Evento/' + obj.id );
            };
            $scope.createEvento = function (obj) {
                $scope.selectedEvento = eventoService.create();
                $scope.selectedEvento.tipoEvento = $scope.entity;
                $location.path('/tipoEvento/'+ $scope.entity.id +'/Evento/');
            };

            $scope.eventoPaginationChanged = function(newPage) {
                $scope.eventoPagination.currentPage = newPage;
                $scope.findEvento();
            };

            $scope.isEventoActive = ($stateParams.child === 'Evento' && $stateParams.eventoId) ;


            // Selects to Fullfill select boxes
            // Begin block

        }

})();
