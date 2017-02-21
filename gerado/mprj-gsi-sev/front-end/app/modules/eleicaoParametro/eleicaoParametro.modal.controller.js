/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:eleicaoParametroController
 * @description
 * # eleicaoParametroController
 * Modal Controller for EleicaoParametro of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalEleicaoParametroController', ModalEleicaoParametroController);

    ModalEleicaoParametroController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'eleicaoParametroService', 'eleicaoService', 'parametroService', 'data'];

    function ModalEleicaoParametroController($state, $location, $modalInstance, jsog, eleicaoParametroService , eleicaoService, parametroService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.eleicaoParametro;
        vm.searchSelects = eleicaoParametroService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                                                 parametroService.findAll({page: 1, size: vm.maxSelectBoxSize}, function (data) {
                            vm.parametroList = jsog.decode(data.content);
                    });
                                    }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                eleicaoParametroService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting eleicaoParametro with id:' + vm.entity.id);
                eleicaoParametroService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('eleicaoParametroEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
