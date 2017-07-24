(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:integranteComissaoController
     * @description
     * # integranteComissaoController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('integranteComissaoEditController', integranteComissaoEditController);

        integranteComissaoEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'integranteComissaoService', 'eleicaoService', 'tipoIntegranteComissaoService'];

        function integranteComissaoEditController($scope, $state, $stateParams , $modal, $location, jsog, integranteComissaoService , eleicaoService, tipoIntegranteComissaoService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'integranteComissao';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = integranteComissaoService.create();
            vm.searchSelects = integranteComissaoService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************integranteComissao
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                integranteComissaoService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "integranteComissaoEdit.main", icon: "mdi-home", visible: "true"});
            // Many to One:
            vm.tabs.push({heading: "Eleicao", route: "integranteComissaoEdit.eleicao", icon: "mdi-eye", visible: $stateParams.id});
            // One to Many:

            vm.tabSelected = 'integranteComissaoEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************


            // Selects to Fullfill select boxes
            // Begin block
            tipoIntegranteComissaoService.findAll( {page:1 , size:vm.maxSelectBoxSize}, function (data) {
                vm.tipoIntegranteComissaoList = jsog.decode(data.content);
            });

        }

})();
