
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:candidatoController
     * @description
     * # candidatoController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('candidatoChildController', candidatoChildController);

    candidatoChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'candidatoService' , 'candidatoPadraoService', 'eleicaoService', 'resultadoApuracaoService', 'cedulaService'];

    function candidatoChildController($scope, $stateParams , $modal, $location, jsog, candidatoService , candidatoPadraoService, eleicaoService, resultadoApuracaoService, cedulaService ) {

        var vm = this;
        $scope.findCandidato();
    }

})();
