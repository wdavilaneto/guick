
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:cedulaController
     * @description
     * # cedulaController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('cedulaChildController', cedulaChildController);

    cedulaChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'cedulaService' , 'urnaService', 'candidatoService'];

    function cedulaChildController($scope, $stateParams , $modal, $location, jsog, cedulaService , urnaService, candidatoService ) {

        var vm = this;
        $scope.findCedula();
    }

})();
