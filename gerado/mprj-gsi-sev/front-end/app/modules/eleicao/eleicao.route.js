(function() {

	'use strict';

    angular.module('sevApp.eleicao').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var eleicaoSearch = {
            name: "eleicaoSearch",
            url: "/eleicao",
            controller: 'eleicaoController',
            controllerAs: 'vm',
            templateUrl: "modules/eleicao/eleicao.html"
        };
        var eleicaoEdit = {
            abstract: true,
            name: "eleicaoEdit",
            url: "/eleicao/{id}",
            controller: 'eleicaoEditController',
            controllerAs: 'vm',
            templateUrl: "modules/eleicao/eleicao.edit.html"
        };
        var eleicaoEditMain = {name: "eleicaoEdit.main",
            url: "",
            controller: 'eleicaoEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/eleicao/eleicao.edit.main.html",
        };

        $stateProvider
            .state( eleicaoSearch )
            .state( eleicaoEdit )
            .state( eleicaoEditMain )
             .state('eleicaoEdit.arquivoRelatorioCollection', {
                url: "/arquivoRelatorioCollection",
                controller: 'arquivoRelatorioChildController',
                controllerAs: 'vm',
                templateUrl: "modules/arquivoRelatorio/details/arquivoRelatorio.child.html"
            })
             .state('eleicaoEdit.candidatoCollection', {
                url: "/candidatoCollection",
                controller: 'candidatoChildController',
                controllerAs: 'vm',
                templateUrl: "modules/candidato/details/candidato.child.html"
            })
             .state('eleicaoEdit.eleicaoParametroCollection', {
                url: "/eleicaoParametroCollection",
                controller: 'eleicaoParametroChildController',
                controllerAs: 'vm',
                templateUrl: "modules/eleicaoParametro/details/eleicaoParametro.child.html"
            })
             .state('eleicaoEdit.eleitorCollection', {
                url: "/eleitorCollection",
                controller: 'eleitorChildController',
                controllerAs: 'vm',
                templateUrl: "modules/eleitor/details/eleitor.child.html"
            })
             .state('eleicaoEdit.eventoCollection', {
                url: "/eventoCollection",
                controller: 'eventoChildController',
                controllerAs: 'vm',
                templateUrl: "modules/evento/details/evento.child.html"
            })
             .state('eleicaoEdit.integranteComissaoCollection', {
                url: "/integranteComissaoCollection",
                controller: 'integranteComissaoChildController',
                controllerAs: 'vm',
                templateUrl: "modules/integranteComissao/details/integranteComissao.child.html"
            })
             .state('eleicaoEdit.tipoFiltroPessoaCollection', {
                url: "/tipoFiltroPessoaCollection",
                controller: 'tipoFiltroPessoaChildController',
                controllerAs: 'vm',
                templateUrl: "modules/tipoFiltroPessoa/details/tipoFiltroPessoa.child.html"
            })
             .state('eleicaoEdit.urnaCollection', {
                url: "/urnaCollection",
                controller: 'urnaChildController',
                controllerAs: 'vm',
                templateUrl: "modules/urna/details/urna.child.html"
            })
        ;
    }


})();
