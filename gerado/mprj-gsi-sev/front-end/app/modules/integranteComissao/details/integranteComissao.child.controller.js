
(function() {
	'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name sevApp.controller:integranteComissaoController
     * @description
     * # integranteComissaoController
     * Controller of the sevApp
     */
    angular.module('sevApp').controller('integranteComissaoChildController', integranteComissaoChildController);

    integranteComissaoChildController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'integranteComissaoService' , 'eleicaoService', 'tipoIntegranteComissaoService'];

    function integranteComissaoChildController($scope, $stateParams , $modal, $location, jsog, integranteComissaoService , eleicaoService, tipoIntegranteComissaoService ) {

        var vm = this;
        $scope.findIntegranteComissao();
    }

})();
