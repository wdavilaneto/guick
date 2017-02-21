
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:tipoEventoController
     * @description
     * # tipoEventoController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('tipoEventoChildController', tipoEventoChildController);

    tipoEventoChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'tipoEventoService' , 'eventoService'];

    function tipoEventoChildController($scope, $stateParams , $modal, $location, jsog, tipoEventoService , eventoService ) {

        var vm = this;
        $scope.findTipoEvento();
    }

})();
