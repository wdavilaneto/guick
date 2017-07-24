
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:tipoFiltroPessoaController
     * @description
     * # tipoFiltroPessoaController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('tipoFiltroPessoaChildController', tipoFiltroPessoaChildController);

    tipoFiltroPessoaChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'tipoFiltroPessoaService' , 'eleicaoService'];

    function tipoFiltroPessoaChildController($scope, $stateParams , $modal, $location, jsog, tipoFiltroPessoaService , eleicaoService ) {

        var vm = this;
        $scope.findTipoFiltroPessoa();
    }

})();
