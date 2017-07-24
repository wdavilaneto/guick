(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:eleicaoParametroController
     * @description
     * # eleicaoParametroController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('eleicaoParametroEditController', eleicaoParametroEditController);

        eleicaoParametroEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'eleicaoParametroService', 'eleicaoService', 'parametroService'];

        function eleicaoParametroEditController($scope, $state, $stateParams , $modal, $location, jsog, eleicaoParametroService , eleicaoService, parametroService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'eleicaoParametro';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = eleicaoParametroService.create();
            vm.searchSelects = eleicaoParametroService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************eleicaoParametro
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                eleicaoParametroService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "eleicaoParametroEdit.main", icon: "mdi-home", visible: "true"});
            // Many to One:
            vm.tabs.push({heading: "Eleicao", route: "eleicaoParametroEdit.eleicao", icon: "mdi-eye", visible: $stateParams.id});
            vm.tabs.push({heading: "Parametro", route: "eleicaoParametroEdit.parametro", icon: "mdi-eye", visible: $stateParams.id});
            // One to Many:

            vm.tabSelected = 'eleicaoParametroEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************


            // Selects to Fullfill select boxes
            // Begin block
            parametroService.findAll( {page:1 , size:vm.maxSelectBoxSize}, function (data) {
                vm.parametroList = jsog.decode(data.content);
            });

        }

})();
