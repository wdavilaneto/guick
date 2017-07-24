
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:urnaController
     * @description
     * # urnaController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('urnaChildController', urnaChildController);

    urnaChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'urnaService' , 'cedulaService', 'eleicaoService'];

    function urnaChildController($scope, $stateParams , $modal, $location, jsog, urnaService , cedulaService, eleicaoService ) {

        var vm = this;
        $scope.findUrna();
    }

})();
