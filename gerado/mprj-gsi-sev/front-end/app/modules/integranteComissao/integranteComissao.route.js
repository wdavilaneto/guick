(function() {

	'use strict';

    angular.module('sevApp.integranteComissao').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var integranteComissaoSearch = {
            name: "integranteComissaoSearch",
            url: "/integranteComissao",
            controller: 'integranteComissaoController',
            controllerAs: 'vm',
            templateUrl: "modules/integranteComissao/integranteComissao.html"
        };
        var integranteComissaoEdit = {
            abstract: true,
            name: "integranteComissaoEdit",
            url: "/integranteComissao/{id}",
            controller: 'integranteComissaoEditController',
            controllerAs: 'vm',
            templateUrl: "modules/integranteComissao/integranteComissao.edit.html"
        };
        var integranteComissaoEditMain = {name: "integranteComissaoEdit.main",
            url: "",
            controller: 'integranteComissaoEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/integranteComissao/integranteComissao.edit.main.html",
        };

        $stateProvider
            .state( integranteComissaoSearch )
            .state( integranteComissaoEdit )
            .state( integranteComissaoEditMain )
            .state('integranteComissaoEdit.eleicao', {
                url: "/eleicao",
                controller: 'integranteComissaoEditMainController',
                controllerAs: 'vm',
                templateUrl: "modules/eleicao/details/eleicao.compound.html"
            })
        ;
    }


})();
