(function() {

	'use strict';

    angular.module('sevApp.tipoIntegranteComissao').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var tipoIntegranteComissaoSearch = {
            name: "tipoIntegranteComissaoSearch",
            url: "/tipoIntegranteComissao",
            controller: 'tipoIntegranteComissaoController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoIntegranteComissao/tipoIntegranteComissao.html"
        };
        var tipoIntegranteComissaoEdit = {
            abstract: true,
            name: "tipoIntegranteComissaoEdit",
            url: "/tipoIntegranteComissao/{id}",
            controller: 'tipoIntegranteComissaoEditController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoIntegranteComissao/tipoIntegranteComissao.edit.html"
        };
        var tipoIntegranteComissaoEditMain = {name: "tipoIntegranteComissaoEdit.main",
            url: "",
            controller: 'tipoIntegranteComissaoEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoIntegranteComissao/tipoIntegranteComissao.edit.main.html",
        };

        $stateProvider
            .state( tipoIntegranteComissaoSearch )
            .state( tipoIntegranteComissaoEdit )
            .state( tipoIntegranteComissaoEditMain )
             .state('tipoIntegranteComissaoEdit.integranteComissaoCollection', {
                url: "/integranteComissaoCollection",
                controller: 'integranteComissaoChildController',
                controllerAs: 'vm',
                templateUrl: "modules/integranteComissao/details/integranteComissao.child.html"
            })
        ;
    }


})();
