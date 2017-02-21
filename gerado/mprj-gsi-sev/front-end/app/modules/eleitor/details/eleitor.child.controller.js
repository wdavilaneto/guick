
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:eleitorController
     * @description
     * # eleitorController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('eleitorChildController', eleitorChildController);

    eleitorChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'eleitorService' , 'eleicaoService'];

    function eleitorChildController($scope, $stateParams , $modal, $location, jsog, eleitorService , eleicaoService ) {

        var vm = this;
        $scope.findEleitor();
    }

})();
