(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:candidatoController
     * @description
     * # candidatoController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('candidatoEditController', candidatoEditController);

        candidatoEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'candidatoService', 'candidatoPadraoService', 'eleicaoService', 'resultadoApuracaoService', 'cedulaService'];

        function candidatoEditController($scope, $state, $stateParams , $modal, $location, jsog, candidatoService , candidatoPadraoService, eleicaoService, resultadoApuracaoService, cedulaService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'candidato';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = candidatoService.create();
            vm.searchSelects = candidatoService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************candidato
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                candidatoService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "candidatoEdit.main", icon: "mdi-home", visible: "true"});
            vm.tabs.push({heading: "Candidato Padrao", route: "candidatoEdit.candidatoPadrao", icon: "mdi-eye", visible: $stateParams.id});
            vm.tabs.push({heading: "Eleicao", route: "candidatoEdit.eleicao", icon: "mdi-eye", visible: $stateParams.id});
             vm.tabs.push({heading: "Resultado Apuracao ", route: "candidatoEdit.resultadoApuracaoCollection", icon: "mdi-label", visible: $stateParams.id});

            vm.tabSelected = 'candidatoEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************
             $scope.selectedResultadoApuracao = null;

            $scope.getResultadoApuracao = function ( resultadoApuracaoId ) {
                resultadoApuracaoService.get( {'id':resultadoApuracaoId} , function (data) {
                    $scope.selectedResultadoApuracao = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/candidato/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'ResultadoApuracao' && $stateParams.resultadoApuracaoId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.resultadoApuracaoId) {
                    $scope.getResultadoApuracao( $stateParams.resultadoApuracaoId );
                }
            }

            $scope.findResultadoApuracao = function() {
                console.log('Realizando busca de 1 x N( resultadoApuracao.candidato.id):' + $stateParams.id);

                $scope.gridOptions = resultadoApuracaoService.createGridOptions(true);
                $scope.resultadoApuracaoPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                resultadoApuracaoService.search( {'content':{ 'candidato':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchResultadoApuracao = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( resultadoApuracao.candidato.id):' + $scope.entity.id);
                $scope.resultPage = null;
                resultadoApuracaoService.search( {'content':{ 'candidato':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeResultadoApuracao = function (obj){
                console.log('Deleting resultadoApuracao with id:' + obj.id);
                resultadoApuracaoService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveResultadoApuracao = function (){
                console.log('Requesting save on ${resultadoApuracao}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                resultadoApuracaoService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = resultadoApuracaoService.create();
                    if (!$scope.editMode) {
                        $scope.searchResultadoApuracao();
                    } else {
                        $location.path('/candidato/'+ $stateParams.id + '/ResultadoApuracao');
                    }
                });
            };
            $scope.editResultadoApuracao = function (obj) {
                $scope.selectedResultadoApuracao = obj
                $location.path('/candidato/'+ $scope.entity.id +'/ResultadoApuracao/' + obj.id );
            };
            $scope.createResultadoApuracao = function (obj) {
                $scope.selectedResultadoApuracao = resultadoApuracaoService.create();
                $scope.selectedResultadoApuracao.candidato = $scope.entity;
                $location.path('/candidato/'+ $scope.entity.id +'/ResultadoApuracao/');
            };

            $scope.resultadoApuracaoPaginationChanged = function(newPage) {
                $scope.resultadoApuracaoPagination.currentPage = newPage;
                $scope.findResultadoApuracao();
            };

            $scope.isResultadoApuracaoActive = ($stateParams.child === 'ResultadoApuracao' && $stateParams.resultadoApuracaoId) ;


            // Selects to Fullfill select boxes
            // Begin block
            candidatoPadraoService.findAll( {page:1 , size:vm.maxSelectBoxSize}, function (data) {
                vm.candidatoPadraoList = jsog.decode(data.content);
            });

        }

})();
