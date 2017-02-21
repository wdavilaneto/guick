(function() {

	'use strict';

    angular.module('sevApp.situacaoEleicao').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var situacaoEleicaoSearch = {
            name: "situacaoEleicaoSearch",
            url: "/situacaoEleicao",
            controller: 'situacaoEleicaoController',
            controllerAs: 'vm',
            templateUrl: "modules/situacaoEleicao/situacaoEleicao.html"
        };
        var situacaoEleicaoEdit = {
            abstract: true,
            name: "situacaoEleicaoEdit",
            url: "/situacaoEleicao/{id}",
            controller: 'situacaoEleicaoEditController',
            controllerAs: 'vm',
            templateUrl: "modules/situacaoEleicao/situacaoEleicao.edit.html"
        };
        var situacaoEleicaoEditMain = {name: "situacaoEleicaoEdit.main",
            url: "",
            controller: 'situacaoEleicaoEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/situacaoEleicao/situacaoEleicao.edit.main.html",
        };

        $stateProvider
            .state( situacaoEleicaoSearch )
            .state( situacaoEleicaoEdit )
            .state( situacaoEleicaoEditMain )
             .state('situacaoEleicaoEdit.eleicaoCollection', {
                url: "/eleicaoCollection",
                controller: 'eleicaoChildController',
                controllerAs: 'vm',
                templateUrl: "modules/eleicao/details/eleicao.child.html"
            })
        ;
    }


})();
