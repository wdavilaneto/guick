
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:eleicaoParametroController
     * @description
     * # eleicaoParametroController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('eleicaoParametroChildController', eleicaoParametroChildController);

    eleicaoParametroChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'eleicaoParametroService' , 'eleicaoService', 'parametroService'];

    function eleicaoParametroChildController($scope, $stateParams , $modal, $location, jsog, eleicaoParametroService , eleicaoService, parametroService ) {

        var vm = this;
        $scope.findEleicaoParametro();
    }

})();
