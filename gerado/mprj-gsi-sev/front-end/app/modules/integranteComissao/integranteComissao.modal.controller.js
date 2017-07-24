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
 * Modal Controller for IntegranteComissao of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalIntegranteComissaoController', ModalIntegranteComissaoController);

    ModalIntegranteComissaoController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'integranteComissaoService', 'eleicaoService', 'tipoIntegranteComissaoService', 'data'];

    function ModalIntegranteComissaoController($state, $location, $modalInstance, jsog, integranteComissaoService , eleicaoService, tipoIntegranteComissaoService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.integranteComissao;
        vm.searchSelects = integranteComissaoService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                                                 tipoIntegranteComissaoService.findAll({page: 1, size: vm.maxSelectBoxSize}, function (data) {
                            vm.tipoIntegranteComissaoList = jsog.decode(data.content);
                    });
                                    }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                integranteComissaoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting integranteComissao with id:' + vm.entity.id);
                integranteComissaoService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('integranteComissaoEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
