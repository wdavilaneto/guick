(function() {
	'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:tipoFiltroPessoaExtController
 * @description
 * # tipoFiltroPessoaExtController
 * Extended Controller pr Master Detail and other specific operations of the sevApp
 */
    angular.module('sevApp')
        .controller('tipoFiltroPessoaExtController', tipoFiltroPessoaExtController);

    tipoFiltroPessoaExtController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'tipoFiltroPessoaService' , 'eleicaoService'];

    function tipoFiltroPessoaExtController($scope, $stateParams , $modal, $location, jsog, tipoFiltroPessoaService , eleicaoService) {

        $scope.masterDetail = null;

        $scope.create = function ( parent ) {
            $scope.selected = null;
            $scope.selected = {
                tipoFiltroPessoa:parent
            };
        };

        // ******************************************************************************************
        // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
        // ******************************************************************************************

        $scope.back = function (){
            if ($stateParams.child) {
                console.log('Redirecting to /TipoFiltroPessoa/'+ $stateParams.id + '/' + $stateParams.child);
                $location.path('/tipoFiltroPessoa/'+ $stateParams.id + '/' + $stateParams.child);
            } else {
                $window.history.back();
            }
        };

        $scope.clear = function () {
            $scope.selected = null;
        };

    }

})();
