(function() {

	'use strict';

    angular.module('sevApp.candidatoPadrao').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var candidatoPadraoSearch = {
            name: "candidatoPadraoSearch",
            url: "/candidatoPadrao",
            controller: 'candidatoPadraoController',
            controllerAs: 'vm',
            templateUrl: "modules/candidatoPadrao/candidatoPadrao.html"
        };
        var candidatoPadraoEdit = {
            abstract: true,
            name: "candidatoPadraoEdit",
            url: "/candidatoPadrao/{id}",
            controller: 'candidatoPadraoEditController',
            controllerAs: 'vm',
            templateUrl: "modules/candidatoPadrao/candidatoPadrao.edit.html"
        };
        var candidatoPadraoEditMain = {name: "candidatoPadraoEdit.main",
            url: "",
            controller: 'candidatoPadraoEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/candidatoPadrao/candidatoPadrao.edit.main.html",
        };

        $stateProvider
            .state( candidatoPadraoSearch )
            .state( candidatoPadraoEdit )
            .state( candidatoPadraoEditMain )
             .state('candidatoPadraoEdit.candidatoCollection', {
                url: "/candidatoCollection",
                controller: 'candidatoChildController',
                controllerAs: 'vm',
                templateUrl: "modules/candidato/details/candidato.child.html"
            })
        ;
    }


})();
