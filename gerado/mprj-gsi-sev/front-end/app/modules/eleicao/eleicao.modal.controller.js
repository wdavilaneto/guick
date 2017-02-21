/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:eleicaoController
 * @description
 * # eleicaoController
 * Modal Controller for Eleicao of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalEleicaoController', ModalEleicaoController);

    ModalEleicaoController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'eleicaoService', 'arquivoRelatorioService', 'candidatoService', 'situacaoEleicaoService', 'eleicaoParametroService', 'eleitorService', 'eventoService', 'integranteComissaoService', 'tipoFiltroPessoaService', 'urnaService', 'data'];

    function ModalEleicaoController($state, $location, $modalInstance, jsog, eleicaoService , arquivoRelatorioService, candidatoService, situacaoEleicaoService, eleicaoParametroService, eleitorService, eventoService, integranteComissaoService, tipoFiltroPessoaService, urnaService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.eleicao;
        vm.searchSelects = eleicaoService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                                                                             situacaoEleicaoService.findAll({page: 1, size: vm.maxSelectBoxSize}, function (data) {
                            vm.situacaoEleicaoList = jsog.decode(data.content);
                    });
                                                                                                                                                                                                            }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                eleicaoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting eleicao with id:' + vm.entity.id);
                eleicaoService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('eleicaoEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
