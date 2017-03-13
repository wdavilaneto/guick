(function() {

	'use strict';

    angular.module('sevApp.eleicaoParametro').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var eleicaoParametroSearch = {
            name: "eleicaoParametroSearch",
            url: "/eleicaoParametro",
            controller: 'eleicaoParametroController',
            controllerAs: 'vm',
            templateUrl: "modules/eleicaoParametro/eleicaoParametro.html"
        };
        var eleicaoParametroEdit = {
            abstract: true,
            name: "eleicaoParametroEdit",
            url: "/eleicaoParametro/{id}",
            controller: 'eleicaoParametroEditController',
            controllerAs: 'vm',
            templateUrl: "modules/eleicaoParametro/eleicaoParametro.edit.html"
        };
        var eleicaoParametroEditMain = {name: "eleicaoParametroEdit.main",
            url: "",
            controller: 'eleicaoParametroEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/eleicaoParametro/eleicaoParametro.edit.main.html",
        };

        $stateProvider
            .state( eleicaoParametroSearch )
            .state( eleicaoParametroEdit )
            .state( eleicaoParametroEditMain )
            .state('eleicaoParametroEdit.eleicao', {
                url: "/eleicao",
                controller: 'eleicaoParametroEditMainController',
                controllerAs: 'vm',
                templateUrl: "modules/eleicao/details/eleicao.compound.html"
            })
            .state('eleicaoParametroEdit.parametro', {
                url: "/parametro",
                controller: 'eleicaoParametroEditMainController',
                controllerAs: 'vm',
                templateUrl: "modules/parametro/details/parametro.compound.html"
            })
        ;
    }


})();
