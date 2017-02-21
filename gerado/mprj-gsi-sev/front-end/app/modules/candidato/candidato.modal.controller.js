/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:candidatoController
 * @description
 * # candidatoController
 * Modal Controller for Candidato of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalCandidatoController', ModalCandidatoController);

    ModalCandidatoController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'candidatoService', 'candidatoPadraoService', 'eleicaoService', 'resultadoApuracaoService', 'cedulaService', 'data'];

    function ModalCandidatoController($state, $location, $modalInstance, jsog, candidatoService , candidatoPadraoService, eleicaoService, resultadoApuracaoService, cedulaService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.candidato;
        vm.searchSelects = candidatoService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                     candidatoPadraoService.findAll({page: 1, size: vm.maxSelectBoxSize}, function (data) {
                            vm.candidatoPadraoList = jsog.decode(data.content);
                    });
                                                                                                                        }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                candidatoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting candidato with id:' + vm.entity.id);
                candidatoService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('candidatoEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
