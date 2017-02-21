
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:arquivoRelatorioController
     * @description
     * # arquivoRelatorioController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('arquivoRelatorioChildController', arquivoRelatorioChildController);

    arquivoRelatorioChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'arquivoRelatorioService' , 'eleicaoService', 'eventoService', 'tipoRelatorioService'];

    function arquivoRelatorioChildController($scope, $stateParams , $modal, $location, jsog, arquivoRelatorioService , eleicaoService, eventoService, tipoRelatorioService ) {

        var vm = this;
        $scope.findArquivoRelatorio();
    }

})();
