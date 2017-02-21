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
 * Modal Controller for TipoIntegranteComissao of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalTipoIntegranteComissaoController', ModalTipoIntegranteComissaoController);

    ModalTipoIntegranteComissaoController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'tipoIntegranteComissaoService', 'integranteComissaoService', 'data'];

    function ModalTipoIntegranteComissaoController($state, $location, $modalInstance, jsog, tipoIntegranteComissaoService , integranteComissaoService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.tipoIntegranteComissao;
        vm.searchSelects = tipoIntegranteComissaoService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                tipoIntegranteComissaoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting tipoIntegranteComissao with id:' + vm.entity.id);
                tipoIntegranteComissaoService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('tipoIntegranteComissaoEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
