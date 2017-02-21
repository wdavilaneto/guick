/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:tipoFiltroPessoaController
 * @description
 * # tipoFiltroPessoaController
 * Modal Controller for TipoFiltroPessoa of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalTipoFiltroPessoaController', ModalTipoFiltroPessoaController);

    ModalTipoFiltroPessoaController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'tipoFiltroPessoaService', 'eleicaoService', 'data'];

    function ModalTipoFiltroPessoaController($state, $location, $modalInstance, jsog, tipoFiltroPessoaService , eleicaoService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.tipoFiltroPessoa;
        vm.searchSelects = tipoFiltroPessoaService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                tipoFiltroPessoaService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting tipoFiltroPessoa with id:' + vm.entity.id);
                tipoFiltroPessoaService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('tipoFiltroPessoaEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
