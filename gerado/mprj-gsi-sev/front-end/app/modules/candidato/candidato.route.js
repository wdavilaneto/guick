(function() {

	'use strict';

    angular.module('sevApp.candidato').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var candidatoSearch = {
            name: "candidatoSearch",
            url: "/candidato",
            controller: 'candidatoController',
            controllerAs: 'vm',
            templateUrl: "modules/candidato/candidato-card.html"
        };
        var candidatoEdit = {
            abstract: true,
            name: "candidatoEdit",
            url: "/candidato/{id}",
            controller: 'candidatoEditController',
            controllerAs: 'vm',
            templateUrl: "modules/candidato/candidato.edit.html"
        };
        var candidatoEditMain = {name: "candidatoEdit.main",
            url: "",
            controller: 'candidatoEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/candidato/candidato.edit.main.html",
        };

        $stateProvider
            .state( candidatoSearch )
            .state( candidatoEdit )
            .state( candidatoEditMain )
            .state('candidatoEdit.candidatoPadrao', {
                url: "/candidatoPadrao",
                controller: 'candidatoEditMainController',
                controllerAs: 'vm',
                templateUrl: "modules/candidatoPadrao/details/candidatoPadrao.compound.html"
            })
            .state('candidatoEdit.eleicao', {
                url: "/eleicao",
                controller: 'candidatoEditMainController',
                controllerAs: 'vm',
                templateUrl: "modules/eleicao/details/eleicao.compound.html"
            })
             .state('candidatoEdit.resultadoApuracaoCollection', {
                url: "/resultadoApuracaoCollection",
                controller: 'resultadoApuracaoChildController',
                controllerAs: 'vm',
                templateUrl: "modules/resultadoApuracao/details/resultadoApuracao.child.html"
            })
        ;
    }


})();
