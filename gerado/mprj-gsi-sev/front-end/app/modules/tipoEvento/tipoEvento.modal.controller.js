/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:tipoEventoController
 * @description
 * # tipoEventoController
 * Modal Controller for TipoEvento of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalTipoEventoController', ModalTipoEventoController);

    ModalTipoEventoController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'tipoEventoService', 'eventoService', 'data'];

    function ModalTipoEventoController($state, $location, $modalInstance, jsog, tipoEventoService , eventoService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.tipoEvento;
        vm.searchSelects = tipoEventoService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                tipoEventoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting tipoEvento with id:' + vm.entity.id);
                tipoEventoService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('tipoEventoEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
