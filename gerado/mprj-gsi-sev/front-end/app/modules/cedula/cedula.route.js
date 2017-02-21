(function() {

	'use strict';

    angular.module('sevApp.cedula').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var cedulaSearch = {
            name: "cedulaSearch",
            url: "/cedula",
            controller: 'cedulaController',
            controllerAs: 'vm',
            templateUrl: "modules/cedula/cedula.html"
        };
        var cedulaEdit = {
            abstract: true,
            name: "cedulaEdit",
            url: "/cedula/{id}",
            controller: 'cedulaEditController',
            controllerAs: 'vm',
            templateUrl: "modules/cedula/cedula.edit.html"
        };
        var cedulaEditMain = {name: "cedulaEdit.main",
            url: "",
            controller: 'cedulaEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/cedula/cedula.edit.main.html",
        };

        $stateProvider
            .state( cedulaSearch )
            .state( cedulaEdit )
            .state( cedulaEditMain )
            .state('cedulaEdit.urna', {
                url: "/urna",
                controller: 'cedulaExtController',
                controllerAs: 'vm',
                templateUrl: "modules/urna/details/urna.compound.html"
            })
        ;
    }


})();
