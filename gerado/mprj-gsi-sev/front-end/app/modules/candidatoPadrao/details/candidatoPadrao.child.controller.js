
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:candidatoPadraoController
     * @description
     * # candidatoPadraoController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('candidatoPadraoChildController', candidatoPadraoChildController);

    candidatoPadraoChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'candidatoPadraoService' , 'candidatoService'];

    function candidatoPadraoChildController($scope, $stateParams , $modal, $location, jsog, candidatoPadraoService , candidatoService ) {

        var vm = this;
        $scope.findCandidatoPadrao();
    }

})();
