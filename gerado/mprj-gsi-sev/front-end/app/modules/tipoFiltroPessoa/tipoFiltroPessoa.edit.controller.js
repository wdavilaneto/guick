(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:tipoFiltroPessoaController
     * @description
     * # tipoFiltroPessoaController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('tipoFiltroPessoaEditController', tipoFiltroPessoaEditController);

        tipoFiltroPessoaEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'tipoFiltroPessoaService', 'eleicaoService'];

        function tipoFiltroPessoaEditController($scope, $state, $stateParams , $modal, $location, jsog, tipoFiltroPessoaService , eleicaoService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'tipoFiltroPessoa';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = tipoFiltroPessoaService.create();
            vm.searchSelects = tipoFiltroPessoaService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************tipoFiltroPessoa
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                tipoFiltroPessoaService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "tipoFiltroPessoaEdit.main", icon: "mdi-home", visible: "true"});
            // Many to One:
            vm.tabs.push({heading: "Eleicao", route: "tipoFiltroPessoaEdit.eleicao", icon: "mdi-eye", visible: $stateParams.id});
            // One to Many:

            vm.tabSelected = 'tipoFiltroPessoaEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************


            // Selects to Fullfill select boxes
            // Begin block

        }

})();
