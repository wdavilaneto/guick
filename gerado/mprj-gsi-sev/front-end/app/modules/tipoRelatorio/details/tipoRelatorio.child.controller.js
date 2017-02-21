
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:tipoRelatorioController
     * @description
     * # tipoRelatorioController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('tipoRelatorioChildController', tipoRelatorioChildController);

    tipoRelatorioChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'tipoRelatorioService' , 'arquivoRelatorioService'];

    function tipoRelatorioChildController($scope, $stateParams , $modal, $location, jsog, tipoRelatorioService , arquivoRelatorioService ) {

        var vm = this;
        $scope.findTipoRelatorio();
    }

})();
