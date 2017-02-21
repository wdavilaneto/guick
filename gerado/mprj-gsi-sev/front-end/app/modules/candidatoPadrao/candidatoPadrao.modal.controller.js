/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:candidatoPadraoController
 * @description
 * # candidatoPadraoController
 * Modal Controller for CandidatoPadrao of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalCandidatoPadraoController', ModalCandidatoPadraoController);

    ModalCandidatoPadraoController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'candidatoPadraoService', 'candidatoService', 'data'];

    function ModalCandidatoPadraoController($state, $location, $modalInstance, jsog, candidatoPadraoService , candidatoService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.candidatoPadrao;
        vm.searchSelects = candidatoPadraoService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                candidatoPadraoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting candidatoPadrao with id:' + vm.entity.id);
                candidatoPadraoService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('candidatoPadraoEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
