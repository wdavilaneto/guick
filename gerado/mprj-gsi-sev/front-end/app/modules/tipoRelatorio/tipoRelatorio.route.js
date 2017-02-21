(function() {

	'use strict';

    angular.module('sevApp.tipoRelatorio').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var tipoRelatorioSearch = {
            name: "tipoRelatorioSearch",
            url: "/tipoRelatorio",
            controller: 'tipoRelatorioController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoRelatorio/tipoRelatorio.html"
        };
        var tipoRelatorioEdit = {
            abstract: true,
            name: "tipoRelatorioEdit",
            url: "/tipoRelatorio/{id}",
            controller: 'tipoRelatorioEditController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoRelatorio/tipoRelatorio.edit.html"
        };
        var tipoRelatorioEditMain = {name: "tipoRelatorioEdit.main",
            url: "",
            controller: 'tipoRelatorioEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoRelatorio/tipoRelatorio.edit.main.html",
        };

        $stateProvider
            .state( tipoRelatorioSearch )
            .state( tipoRelatorioEdit )
            .state( tipoRelatorioEditMain )
             .state('tipoRelatorioEdit.arquivoRelatorioCollection', {
                url: "/arquivoRelatorioCollection",
                controller: 'arquivoRelatorioChildController',
                controllerAs: 'vm',
                templateUrl: "modules/arquivoRelatorio/details/arquivoRelatorio.child.html"
            })
        ;
    }


})();
