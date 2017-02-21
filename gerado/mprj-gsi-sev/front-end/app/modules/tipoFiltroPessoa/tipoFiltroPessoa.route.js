(function() {

	'use strict';

    angular.module('sevApp.tipoFiltroPessoa').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        var tipoFiltroPessoaSearch = {
            name: "tipoFiltroPessoaSearch",
            url: "/tipoFiltroPessoa",
            controller: 'tipoFiltroPessoaController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoFiltroPessoa/tipoFiltroPessoa.html"
        };
        var tipoFiltroPessoaEdit = {
            abstract: true,
            name: "tipoFiltroPessoaEdit",
            url: "/tipoFiltroPessoa/{id}",
            controller: 'tipoFiltroPessoaEditController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoFiltroPessoa/tipoFiltroPessoa.edit.html"
        };
        var tipoFiltroPessoaEditMain = {name: "tipoFiltroPessoaEdit.main",
            url: "",
            controller: 'tipoFiltroPessoaEditMainController',
            controllerAs: 'vm',
            templateUrl: "modules/tipoFiltroPessoa/tipoFiltroPessoa.edit.main.html",
        };

        $stateProvider
            .state( tipoFiltroPessoaSearch )
            .state( tipoFiltroPessoaEdit )
            .state( tipoFiltroPessoaEditMain )
            .state('tipoFiltroPessoaEdit.eleicao', {
                url: "/eleicao",
                controller: 'tipoFiltroPessoaExtController',
                controllerAs: 'vm',
                templateUrl: "modules/eleicao/details/eleicao.compound.html"
            })
        ;
    }


})();
