/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:eventoController
 * @description
 * # eventoController
 * Modal Controller for Evento of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalEventoController', ModalEventoController);

    ModalEventoController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'eventoService', 'arquivoRelatorioService', 'eleicaoService', 'tipoEventoService', 'data'];

    function ModalEventoController($state, $location, $modalInstance, jsog, eventoService , arquivoRelatorioService, eleicaoService, tipoEventoService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.evento;
        vm.searchSelects = eventoService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                                                                             tipoEventoService.findAll({page: 1, size: vm.maxSelectBoxSize}, function (data) {
                            vm.tipoEventoList = jsog.decode(data.content);
                    });
                                    }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                eventoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting evento with id:' + vm.entity.id);
                eventoService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('eventoEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
