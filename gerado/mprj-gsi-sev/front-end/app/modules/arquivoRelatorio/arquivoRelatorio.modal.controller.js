/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:arquivoRelatorioController
 * @description
 * # arquivoRelatorioController
 * Modal Controller for ArquivoRelatorio of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalArquivoRelatorioController', ModalArquivoRelatorioController);

    ModalArquivoRelatorioController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'arquivoRelatorioService', 'eleicaoService', 'eventoService', 'tipoRelatorioService', 'data'];

    function ModalArquivoRelatorioController($state, $location, $modalInstance, jsog, arquivoRelatorioService , eleicaoService, eventoService, tipoRelatorioService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.arquivoRelatorio;
        vm.searchSelects = arquivoRelatorioService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                                                                             tipoRelatorioService.findAll({page: 1, size: vm.maxSelectBoxSize}, function (data) {
                            vm.tipoRelatorioList = jsog.decode(data.content);
                    });
                                    }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                arquivoRelatorioService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting arquivoRelatorio with id:' + vm.entity.id);
                arquivoRelatorioService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('arquivoRelatorioEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
