(function() {

	'use strict';

    angular.module('sevApp.eleitor').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var eleitorSearch = {
            name: "eleitorSearch",
            url: "/eleitor",
            controller: 'eleitorController',
            controllerAs: 'vm',
            templateUrl: "modules/eleitor/eleitor.html"
        };
        var eleitorEdit = {
            abstract: true,
            name: "eleitorEdit",
            url: "/eleitor/{id}",
            controller: 'eleitorEditController',
            controllerAs: 'vm',
            templateUrl: "modules/eleitor/eleitor.edit.html"
        };
        var eleitorEditMain = {name: "eleitorEdit.main",
            url: "",
            controller: 'eleitorEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/eleitor/eleitor.edit.main.html",
        };

        $stateProvider
            .state( eleitorSearch )
            .state( eleitorEdit )
            .state( eleitorEditMain )
            .state('eleitorEdit.eleicao', {
                url: "/eleicao",
                controller: 'eleitorExtController',
                controllerAs: 'vm',
                templateUrl: "modules/eleicao/details/eleicao.compound.html"
            })
        ;
    }


})();
