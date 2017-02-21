(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:situacaoEleicaoController
     * @description
     * # situacaoEleicaoController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('situacaoEleicaoEditController', situacaoEleicaoEditController);

        situacaoEleicaoEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'situacaoEleicaoService', 'eleicaoService'];

        function situacaoEleicaoEditController($scope, $state, $stateParams , $modal, $location, jsog, situacaoEleicaoService , eleicaoService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'situacaoEleicao';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = situacaoEleicaoService.create();
            vm.searchSelects = situacaoEleicaoService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************situacaoEleicao
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                situacaoEleicaoService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "situacaoEleicaoEdit.main", icon: "mdi-home", visible: "true"});
             vm.tabs.push({heading: "Eleicao ", route: "situacaoEleicaoEdit.eleicaoCollection", icon: "mdi-label", visible: $stateParams.id});

            vm.tabSelected = 'situacaoEleicaoEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************
             $scope.selectedEleicao = null;

            $scope.getEleicao = function ( eleicaoId ) {
                eleicaoService.get( {'id':eleicaoId} , function (data) {
                    $scope.selectedEleicao = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/situacaoEleicao/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'Eleicao' && $stateParams.eleicaoId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.eleicaoId) {
                    $scope.getEleicao( $stateParams.eleicaoId );
                }
            }

            $scope.findEleicao = function() {
                console.log('Realizando busca de 1 x N( eleicao.situacaoEleicao.id):' + $stateParams.id);

                $scope.gridOptions = eleicaoService.createGridOptions(true);
                $scope.eleicaoPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                eleicaoService.search( {'content':{ 'situacaoEleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchEleicao = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( eleicao.situacaoEleicao.id):' + $scope.entity.id);
                $scope.resultPage = null;
                eleicaoService.search( {'content':{ 'situacaoEleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeEleicao = function (obj){
                console.log('Deleting eleicao with id:' + obj.id);
                eleicaoService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveEleicao = function (){
                console.log('Requesting save on ${eleicao}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                eleicaoService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = eleicaoService.create();
                    if (!$scope.editMode) {
                        $scope.searchEleicao();
                    } else {
                        $location.path('/situacaoEleicao/'+ $stateParams.id + '/Eleicao');
                    }
                });
            };
            $scope.editEleicao = function (obj) {
                $scope.selectedEleicao = obj
                $location.path('/situacaoEleicao/'+ $scope.entity.id +'/Eleicao/' + obj.id );
            };
            $scope.createEleicao = function (obj) {
                $scope.selectedEleicao = eleicaoService.create();
                $scope.selectedEleicao.situacaoEleicao = $scope.entity;
                $location.path('/situacaoEleicao/'+ $scope.entity.id +'/Eleicao/');
            };

            $scope.eleicaoPaginationChanged = function(newPage) {
                $scope.eleicaoPagination.currentPage = newPage;
                $scope.findEleicao();
            };

            $scope.isEleicaoActive = ($stateParams.child === 'Eleicao' && $stateParams.eleicaoId) ;


            // Selects to Fullfill select boxes
            // Begin block

        }

})();
