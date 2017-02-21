(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:arquivoRelatorioController
     * @description
     * # arquivoRelatorioController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('arquivoRelatorioEditController', arquivoRelatorioEditController);

        arquivoRelatorioEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'arquivoRelatorioService', 'eleicaoService', 'eventoService', 'tipoRelatorioService'];

        function arquivoRelatorioEditController($scope, $state, $stateParams , $modal, $location, jsog, arquivoRelatorioService , eleicaoService, eventoService, tipoRelatorioService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'arquivoRelatorio';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = arquivoRelatorioService.create();
            vm.searchSelects = arquivoRelatorioService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************arquivoRelatorio
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                arquivoRelatorioService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "arquivoRelatorioEdit.main", icon: "mdi-home", visible: "true"});
            vm.tabs.push({heading: "Eleicao", route: "arquivoRelatorioEdit.eleicao", icon: "mdi-eye", visible: $stateParams.id});
            vm.tabs.push({heading: "Evento", route: "arquivoRelatorioEdit.evento", icon: "mdi-eye", visible: $stateParams.id});

            vm.tabSelected = 'arquivoRelatorioEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************


            // Selects to Fullfill select boxes
            // Begin block
            tipoRelatorioService.findAll( {page:1 , size:vm.maxSelectBoxSize}, function (data) {
                vm.tipoRelatorioList = jsog.decode(data.content);
            });

        }

})();
