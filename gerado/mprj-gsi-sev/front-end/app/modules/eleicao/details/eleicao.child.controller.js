
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:eleicaoController
     * @description
     * # eleicaoController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('eleicaoChildController', eleicaoChildController);

    eleicaoChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'eleicaoService' , 'arquivoRelatorioService', 'candidatoService', 'situacaoEleicaoService', 'eleicaoParametroService', 'eleitorService', 'eventoService', 'integranteComissaoService', 'tipoFiltroPessoaService', 'urnaService'];

    function eleicaoChildController($scope, $stateParams , $modal, $location, jsog, eleicaoService , arquivoRelatorioService, candidatoService, situacaoEleicaoService, eleicaoParametroService, eleitorService, eventoService, integranteComissaoService, tipoFiltroPessoaService, urnaService ) {

        var vm = this;
        $scope.findEleicao();
    }

})();
