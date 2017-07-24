(function() {

	'use strict';

    angular.module('sevApp.parametro').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var parametroSearch = {
            name: "parametroSearch",
            url: "/parametro",
            controller: 'parametroController',
            controllerAs: 'vm',
            templateUrl: "modules/parametro/parametro.html"
        };
        var parametroEdit = {
            abstract: true,
            name: "parametroEdit",
            url: "/parametro/{id}",
            controller: 'parametroEditController',
            controllerAs: 'vm',
            templateUrl: "modules/parametro/parametro.edit.html"
        };
        var parametroEditMain = {name: "parametroEdit.main",
            url: "",
            controller: 'parametroEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/parametro/parametro.edit.main.html",
        };

        $stateProvider
            .state( parametroSearch )
            .state( parametroEdit )
            .state( parametroEditMain )
             .state('parametroEdit.eleicaoParametroCollection', {
                url: "/eleicaoParametroCollection",
                controller: 'eleicaoParametroChildController',
                controllerAs: 'vm',
                templateUrl: "modules/eleicaoParametro/details/eleicaoParametro.child.html"
            })
        ;
    }


})();
