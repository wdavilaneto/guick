/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:parametroController
 * @description
 * # parametroController
 * Modal Controller for Parametro of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalParametroController', ModalParametroController);

    ModalParametroController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'parametroService', 'eleicaoParametroService', 'tipoParametroService', 'data'];

    function ModalParametroController($state, $location, $modalInstance, jsog, parametroService , eleicaoParametroService, tipoParametroService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.parametro;
        vm.searchSelects = parametroService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                                                 tipoParametroService.findAll({page: 1, size: vm.maxSelectBoxSize}, function (data) {
                            vm.tipoParametroList = jsog.decode(data.content);
                    });
                                    }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                parametroService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting parametro with id:' + vm.entity.id);
                parametroService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('parametroEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
