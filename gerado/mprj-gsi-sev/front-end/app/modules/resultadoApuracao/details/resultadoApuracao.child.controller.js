
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:resultadoApuracaoController
     * @description
     * # resultadoApuracaoController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('resultadoApuracaoChildController', resultadoApuracaoChildController);

    resultadoApuracaoChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'resultadoApuracaoService' , 'candidatoService'];

    function resultadoApuracaoChildController($scope, $stateParams , $modal, $location, jsog, resultadoApuracaoService , candidatoService ) {

        var vm = this;
        $scope.findResultadoApuracao();
    }

})();
