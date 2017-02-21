(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:cedulaController
     * @description
     * # cedulaController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('cedulaEditController', cedulaEditController);

        cedulaEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'cedulaService', 'urnaService', 'candidatoService'];

        function cedulaEditController($scope, $state, $stateParams , $modal, $location, jsog, cedulaService , urnaService, candidatoService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'cedula';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = cedulaService.create();
            vm.searchSelects = cedulaService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************cedula
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                cedulaService.get( {'id':id} , function (data) {
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
            vm.tabs.push({heading: "Principal", route: "cedulaEdit.main", icon: "mdi-home", visible: "true"});
            vm.tabs.push({heading: "Urna", route: "cedulaEdit.urna", icon: "mdi-eye", visible: $stateParams.id});

            vm.tabSelected = 'cedulaEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************


            // Selects to Fullfill select boxes
            // Begin block
            urnaService.findAll( {page:1 , size:vm.maxSelectBoxSize}, function (data) {
                vm.urnaList = jsog.decode(data.content);
            });

        }

})();
