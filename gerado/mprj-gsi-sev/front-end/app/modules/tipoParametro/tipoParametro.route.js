(function() {

	'use strict';

    angular.module('sevApp.tipoParametro').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var tipoParametroSearch = {
            name: "tipoParametroSearch",
            url: "/tipoParametro",
            controller: 'tipoParametroController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoParametro/tipoParametro.html"
        };
        var tipoParametroEdit = {
            abstract: true,
            name: "tipoParametroEdit",
            url: "/tipoParametro/{id}",
            controller: 'tipoParametroEditController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoParametro/tipoParametro.edit.html"
        };
        var tipoParametroEditMain = {name: "tipoParametroEdit.main",
            url: "",
            controller: 'tipoParametroEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoParametro/tipoParametro.edit.main.html",
        };

        $stateProvider
            .state( tipoParametroSearch )
            .state( tipoParametroEdit )
            .state( tipoParametroEditMain )
             .state('tipoParametroEdit.parametroCollection', {
                url: "/parametroCollection",
                controller: 'parametroChildController',
                controllerAs: 'vm',
                templateUrl: "modules/parametro/details/parametro.child.html"
            })
        ;
    }


})();
