/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:situacaoEleicaoController
 * @description
 * # situacaoEleicaoController
 * Modal Controller for SituacaoEleicao of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalSituacaoEleicaoController', ModalSituacaoEleicaoController);

    ModalSituacaoEleicaoController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'situacaoEleicaoService', 'eleicaoService', 'data'];

    function ModalSituacaoEleicaoController($state, $location, $modalInstance, jsog, situacaoEleicaoService , eleicaoService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.situacaoEleicao;
        vm.searchSelects = situacaoEleicaoService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                situacaoEleicaoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting situacaoEleicao with id:' + vm.entity.id);
                situacaoEleicaoService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('situacaoEleicaoEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
