(function() {

	'use strict';

    angular.module('sevApp.urna').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var urnaSearch = {
            name: "urnaSearch",
            url: "/urna",
            controller: 'urnaController',
            controllerAs: 'vm',
            templateUrl: "modules/urna/urna.html"
        };
        var urnaEdit = {
            abstract: true,
            name: "urnaEdit",
            url: "/urna/{id}",
            controller: 'urnaEditController',
            controllerAs: 'vm',
            templateUrl: "modules/urna/urna.edit.html"
        };
        var urnaEditMain = {name: "urnaEdit.main",
            url: "",
            controller: 'urnaEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/urna/urna.edit.main.html",
        };

        $stateProvider
            .state( urnaSearch )
            .state( urnaEdit )
            .state( urnaEditMain )
            .state('urnaEdit.eleicao', {
                url: "/eleicao",
                controller: 'urnaExtController',
                controllerAs: 'vm',
                templateUrl: "modules/eleicao/details/eleicao.compound.html"
            })
             .state('urnaEdit.cedulaCollection', {
                url: "/cedulaCollection",
                controller: 'cedulaChildController',
                controllerAs: 'vm',
                templateUrl: "modules/cedula/details/cedula.child.html"
            })
        ;
    }


})();
