(function() {

	'use strict';

    angular.module('sevApp.evento').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var eventoSearch = {
            name: "eventoSearch",
            url: "/evento",
            controller: 'eventoController',
            controllerAs: 'vm',
            templateUrl: "modules/evento/evento.html"
        };
        var eventoEdit = {
            abstract: true,
            name: "eventoEdit",
            url: "/evento/{id}",
            controller: 'eventoEditController',
            controllerAs: 'vm',
            templateUrl: "modules/evento/evento.edit.html"
        };
        var eventoEditMain = {name: "eventoEdit.main",
            url: "",
            controller: 'eventoEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/evento/evento.edit.main.html",
        };

        $stateProvider
            .state( eventoSearch )
            .state( eventoEdit )
            .state( eventoEditMain )
            .state('eventoEdit.eleicao', {
                url: "/eleicao",
                controller: 'eventoExtController',
                controllerAs: 'vm',
                templateUrl: "modules/eleicao/details/eleicao.compound.html"
            })
             .state('eventoEdit.arquivoRelatorioCollection', {
                url: "/arquivoRelatorioCollection",
                controller: 'arquivoRelatorioChildController',
                controllerAs: 'vm',
                templateUrl: "modules/arquivoRelatorio/details/arquivoRelatorio.child.html"
            })
        ;
    }


})();
