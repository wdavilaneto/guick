(function() {

	'use strict';

    angular.module('sevApp.resultadoApuracao').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var resultadoApuracaoSearch = {
            name: "resultadoApuracaoSearch",
            url: "/resultadoApuracao",
            controller: 'resultadoApuracaoController',
            controllerAs: 'vm',
            templateUrl: "modules/resultadoApuracao/resultadoApuracao.html"
        };
        var resultadoApuracaoEdit = {
            abstract: true,
            name: "resultadoApuracaoEdit",
            url: "/resultadoApuracao/{id}",
            controller: 'resultadoApuracaoEditController',
            controllerAs: 'vm',
            templateUrl: "modules/resultadoApuracao/resultadoApuracao.edit.html"
        };
        var resultadoApuracaoEditMain = {name: "resultadoApuracaoEdit.main",
            url: "",
            controller: 'resultadoApuracaoEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/resultadoApuracao/resultadoApuracao.edit.main.html",
        };

        $stateProvider
            .state( resultadoApuracaoSearch )
            .state( resultadoApuracaoEdit )
            .state( resultadoApuracaoEditMain )
            .state('resultadoApuracaoEdit.candidato', {
                url: "/candidato",
                controller: 'resultadoApuracaoEditMainController',
                controllerAs: 'vm',
                templateUrl: "modules/candidato/details/candidato.compound.html"
            })
        ;
    }


})();
