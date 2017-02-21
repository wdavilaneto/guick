(function() {

	'use strict';

    angular.module('sevApp.tipoEvento').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var tipoEventoSearch = {
            name: "tipoEventoSearch",
            url: "/tipoEvento",
            controller: 'tipoEventoController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoEvento/tipoEvento.html"
        };
        var tipoEventoEdit = {
            abstract: true,
            name: "tipoEventoEdit",
            url: "/tipoEvento/{id}",
            controller: 'tipoEventoEditController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoEvento/tipoEvento.edit.html"
        };
        var tipoEventoEditMain = {name: "tipoEventoEdit.main",
            url: "",
            controller: 'tipoEventoEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoEvento/tipoEvento.edit.main.html",
        };

        $stateProvider
            .state( tipoEventoSearch )
            .state( tipoEventoEdit )
            .state( tipoEventoEditMain )
             .state('tipoEventoEdit.eventoCollection', {
                url: "/eventoCollection",
                controller: 'eventoChildController',
                controllerAs: 'vm',
                templateUrl: "modules/evento/details/evento.child.html"
            })
        ;
    }


})();
