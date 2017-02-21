
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:tipoIntegranteComissaoController
     * @description
     * # tipoIntegranteComissaoController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('tipoIntegranteComissaoChildController', tipoIntegranteComissaoChildController);

    tipoIntegranteComissaoChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'tipoIntegranteComissaoService' , 'integranteComissaoService'];

    function tipoIntegranteComissaoChildController($scope, $stateParams , $modal, $location, jsog, tipoIntegranteComissaoService , integranteComissaoService ) {

        var vm = this;
        $scope.findTipoIntegranteComissao();
    }

})();
