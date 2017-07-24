
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:tipoParametroController
     * @description
     * # tipoParametroController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('tipoParametroChildController', tipoParametroChildController);

    tipoParametroChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'tipoParametroService' , 'parametroService'];

    function tipoParametroChildController($scope, $stateParams , $modal, $location, jsog, tipoParametroService , parametroService ) {

        var vm = this;
        $scope.findTipoParametro();
    }

})();
