(function() {

	'use strict';

    angular.module('sevApp.arquivoRelatorio').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var arquivoRelatorioSearch = {
            name: "arquivoRelatorioSearch",
            url: "/arquivoRelatorio",
            controller: 'arquivoRelatorioController',
            controllerAs: 'vm',
            templateUrl: "modules/arquivoRelatorio/arquivoRelatorio.html"
        };
        var arquivoRelatorioEdit = {
            abstract: true,
            name: "arquivoRelatorioEdit",
            url: "/arquivoRelatorio/{id}",
            controller: 'arquivoRelatorioEditController',
            controllerAs: 'vm',
            templateUrl: "modules/arquivoRelatorio/arquivoRelatorio.edit.html"
        };
        var arquivoRelatorioEditMain = {name: "arquivoRelatorioEdit.main",
            url: "",
            controller: 'arquivoRelatorioEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/arquivoRelatorio/arquivoRelatorio.edit.main.html",
        };

        $stateProvider
            .state( arquivoRelatorioSearch )
            .state( arquivoRelatorioEdit )
            .state( arquivoRelatorioEditMain )
            .state('arquivoRelatorioEdit.eleicao', {
                url: "/eleicao",
                controller: 'arquivoRelatorioExtController',
                controllerAs: 'vm',
                templateUrl: "modules/eleicao/details/eleicao.compound.html"
            })
            .state('arquivoRelatorioEdit.evento', {
                url: "/evento",
                controller: 'arquivoRelatorioExtController',
                controllerAs: 'vm',
                templateUrl: "modules/evento/details/evento.compound.html"
            })
        ;
    }


})();
