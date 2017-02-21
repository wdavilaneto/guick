(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:resultadoApuracaoController
     * @description
     * # resultadoApuracaoController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('resultadoApuracaoEditController', resultadoApuracaoEditController);

        resultadoApuracaoEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'resultadoApuracaoService', 'candidatoService'];

        function resultadoApuracaoEditController($scope, $state, $stateParams , $modal, $location, jsog, resultadoApuracaoService , candidatoService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'resultadoApuracao';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = resultadoApuracaoService.create();
            vm.searchSelects = resultadoApuracaoService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************resultadoApuracao
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                resultadoApuracaoService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "resultadoApuracaoEdit.main", icon: "mdi-home", visible: "true"});
            vm.tabs.push({heading: "Candidato", route: "resultadoApuracaoEdit.candidato", icon: "mdi-eye", visible: $stateParams.id});

            vm.tabSelected = 'resultadoApuracaoEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************


            // Selects to Fullfill select boxes
            // Begin block

        }

})();
