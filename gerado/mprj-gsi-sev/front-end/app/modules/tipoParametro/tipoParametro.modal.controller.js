/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:tipoParametroController
 * @description
 * # tipoParametroController
 * Modal Controller for TipoParametro of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalTipoParametroController', ModalTipoParametroController);

    ModalTipoParametroController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'tipoParametroService', 'parametroService', 'data'];

    function ModalTipoParametroController($state, $location, $modalInstance, jsog, tipoParametroService , parametroService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.tipoParametro;
        vm.searchSelects = tipoParametroService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                tipoParametroService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting tipoParametro with id:' + vm.entity.id);
                tipoParametroService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('tipoParametroEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
