
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:parametroController
     * @description
     * # parametroController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('parametroChildController', parametroChildController);

    parametroChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'parametroService' , 'eleicaoParametroService', 'tipoParametroService'];

    function parametroChildController($scope, $stateParams , $modal, $location, jsog, parametroService , eleicaoParametroService, tipoParametroService ) {

        var vm = this;
        $scope.findParametro();
    }

})();
