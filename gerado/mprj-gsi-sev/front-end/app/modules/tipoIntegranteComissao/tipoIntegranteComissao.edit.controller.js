(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:tipoIntegranteComissaoController
     * @description
     * # tipoIntegranteComissaoController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('tipoIntegranteComissaoEditController', tipoIntegranteComissaoEditController);

        tipoIntegranteComissaoEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'tipoIntegranteComissaoService', 'integranteComissaoService'];

        function tipoIntegranteComissaoEditController($scope, $state, $stateParams , $modal, $location, jsog, tipoIntegranteComissaoService , integranteComissaoService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'tipoIntegranteComissao';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = tipoIntegranteComissaoService.create();
            vm.searchSelects = tipoIntegranteComissaoService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************tipoIntegranteComissao
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                tipoIntegranteComissaoService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "tipoIntegranteComissaoEdit.main", icon: "mdi-home", visible: "true"});
            // Many to One:
            // One to Many:
             vm.tabs.push({heading: "Integrante Comissao ", route: "tipoIntegranteComissaoEdit.integranteComissaoCollection", icon: "mdi-label", visible: $stateParams.id});

            vm.tabSelected = 'tipoIntegranteComissaoEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************
             $scope.selectedIntegranteComissao = null;

            $scope.getIntegranteComissao = function ( integranteComissaoId ) {
                integranteComissaoService.get( {'id':integranteComissaoId} , function (data) {
                    $scope.selectedIntegranteComissao = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/tipoIntegranteComissao/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'IntegranteComissao' && $stateParams.integranteComissaoId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.integranteComissaoId) {
                    $scope.getIntegranteComissao( $stateParams.integranteComissaoId );
                }
            }

            $scope.findIntegranteComissao = function() {
                console.log('Realizando busca de 1 x N( integranteComissao.tipoIntegranteComissao.id):' + $stateParams.id);

                $scope.gridOptions = integranteComissaoService.createGridOptions(true);
                $scope.integranteComissaoPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                integranteComissaoService.search( {'content':{ 'tipoIntegranteComissao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchIntegranteComissao = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( integranteComissao.tipoIntegranteComissao.id):' + $scope.entity.id);
                $scope.resultPage = null;
                integranteComissaoService.search( {'content':{ 'tipoIntegranteComissao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeIntegranteComissao = function (obj){
                console.log('Deleting integranteComissao with id:' + obj.id);
                integranteComissaoService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveIntegranteComissao = function (){
                console.log('Requesting save on ${integranteComissao}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                integranteComissaoService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = integranteComissaoService.create();
                    if (!$scope.editMode) {
                        $scope.searchIntegranteComissao();
                    } else {
                        $location.path('/tipoIntegranteComissao/'+ $stateParams.id + '/IntegranteComissao');
                    }
                });
            };
            $scope.editIntegranteComissao = function (obj) {
                $scope.selectedIntegranteComissao = obj
                $location.path('/tipoIntegranteComissao/'+ $scope.entity.id +'/IntegranteComissao/' + obj.id );
            };
            $scope.createIntegranteComissao = function (obj) {
                $scope.selectedIntegranteComissao = integranteComissaoService.create();
                $scope.selectedIntegranteComissao.tipoIntegranteComissao = $scope.entity;
                $location.path('/tipoIntegranteComissao/'+ $scope.entity.id +'/IntegranteComissao/');
            };

            $scope.integranteComissaoPaginationChanged = function(newPage) {
                $scope.integranteComissaoPagination.currentPage = newPage;
                $scope.findIntegranteComissao();
            };

            $scope.isIntegranteComissaoActive = ($stateParams.child === 'IntegranteComissao' && $stateParams.integranteComissaoId) ;


            // Selects to Fullfill select boxes
            // Begin block

        }

})();
