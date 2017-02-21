/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:cedulaController
 * @description
 * # cedulaController
 * Modal Controller for Cedula of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalCedulaController', ModalCedulaController);

    ModalCedulaController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'cedulaService', 'urnaService', 'candidatoService', 'data'];

    function ModalCedulaController($state, $location, $modalInstance, jsog, cedulaService , urnaService, candidatoService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.cedula;
        vm.searchSelects = cedulaService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                     urnaService.findAll({page: 1, size: vm.maxSelectBoxSize}, function (data) {
                            vm.urnaList = jsog.decode(data.content);
                    });
                                                                }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                cedulaService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting cedula with id:' + vm.entity.id);
                cedulaService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('cedulaEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
