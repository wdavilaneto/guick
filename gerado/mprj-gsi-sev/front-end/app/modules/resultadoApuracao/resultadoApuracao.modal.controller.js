/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:resultadoApuracaoController
 * @description
 * # resultadoApuracaoController
 * Modal Controller for ResultadoApuracao of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalResultadoApuracaoController', ModalResultadoApuracaoController);

    ModalResultadoApuracaoController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'resultadoApuracaoService', 'candidatoService', 'data'];

    function ModalResultadoApuracaoController($state, $location, $modalInstance, jsog, resultadoApuracaoService , candidatoService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.resultadoApuracao;
        vm.searchSelects = resultadoApuracaoService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                resultadoApuracaoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting resultadoApuracao with id:' + vm.entity.id);
                resultadoApuracaoService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('resultadoApuracaoEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
