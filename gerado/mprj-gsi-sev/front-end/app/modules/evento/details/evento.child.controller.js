
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:eventoController
     * @description
     * # eventoController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('eventoChildController', eventoChildController);

    eventoChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'eventoService' , 'arquivoRelatorioService', 'eleicaoService', 'tipoEventoService'];

    function eventoChildController($scope, $stateParams , $modal, $location, jsog, eventoService , arquivoRelatorioService, eleicaoService, tipoEventoService ) {

        var vm = this;
        $scope.findEvento();
    }

})();
