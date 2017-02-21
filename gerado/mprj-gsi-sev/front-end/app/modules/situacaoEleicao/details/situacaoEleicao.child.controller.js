
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:situacaoEleicaoController
     * @description
     * # situacaoEleicaoController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('situacaoEleicaoChildController', situacaoEleicaoChildController);

    situacaoEleicaoChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'situacaoEleicaoService' , 'eleicaoService'];

    function situacaoEleicaoChildController($scope, $stateParams , $modal, $location, jsog, situacaoEleicaoService , eleicaoService ) {

        var vm = this;
        $scope.findSituacaoEleicao();
    }

})();
