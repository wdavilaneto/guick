(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:candidatoPadraoController
     * @description
     * # candidatoPadraoController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('candidatoPadraoEditController', candidatoPadraoEditController);

        candidatoPadraoEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'candidatoPadraoService', 'candidatoService'];

        function candidatoPadraoEditController($scope, $state, $stateParams , $modal, $location, jsog, candidatoPadraoService , candidatoService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'candidatoPadrao';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = candidatoPadraoService.create();
            vm.searchSelects = candidatoPadraoService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************candidatoPadrao
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                candidatoPadraoService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "candidatoPadraoEdit.main", icon: "mdi-home", visible: "true"});
            // Many to One:
            // One to Many:
             vm.tabs.push({heading: "Candidato ", route: "candidatoPadraoEdit.candidatoCollection", icon: "mdi-label", visible: $stateParams.id});

            vm.tabSelected = 'candidatoPadraoEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************
             $scope.selectedCandidato = null;

            $scope.getCandidato = function ( candidatoId ) {
                candidatoService.get( {'id':candidatoId} , function (data) {
                    $scope.selectedCandidato = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/candidatoPadrao/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'Candidato' && $stateParams.candidatoId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.candidatoId) {
                    $scope.getCandidato( $stateParams.candidatoId );
                }
            }

            $scope.findCandidato = function() {
                console.log('Realizando busca de 1 x N( candidato.candidatoPadrao.id):' + $stateParams.id);

                $scope.gridOptions = candidatoService.createGridOptions(true);
                $scope.candidatoPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                candidatoService.search( {'content':{ 'candidatoPadrao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchCandidato = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( candidato.candidatoPadrao.id):' + $scope.entity.id);
                $scope.resultPage = null;
                candidatoService.search( {'content':{ 'candidatoPadrao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeCandidato = function (obj){
                console.log('Deleting candidato with id:' + obj.id);
                candidatoService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveCandidato = function (){
                console.log('Requesting save on ${candidato}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                candidatoService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = candidatoService.create();
                    if (!$scope.editMode) {
                        $scope.searchCandidato();
                    } else {
                        $location.path('/candidatoPadrao/'+ $stateParams.id + '/Candidato');
                    }
                });
            };
            $scope.editCandidato = function (obj) {
                $scope.selectedCandidato = obj
                $location.path('/candidatoPadrao/'+ $scope.entity.id +'/Candidato/' + obj.id );
            };
            $scope.createCandidato = function (obj) {
                $scope.selectedCandidato = candidatoService.create();
                $scope.selectedCandidato.candidatoPadrao = $scope.entity;
                $location.path('/candidatoPadrao/'+ $scope.entity.id +'/Candidato/');
            };

            $scope.candidatoPaginationChanged = function(newPage) {
                $scope.candidatoPagination.currentPage = newPage;
                $scope.findCandidato();
            };

            $scope.isCandidatoActive = ($stateParams.child === 'Candidato' && $stateParams.candidatoId) ;


            // Selects to Fullfill select boxes
            // Begin block

        }

})();
