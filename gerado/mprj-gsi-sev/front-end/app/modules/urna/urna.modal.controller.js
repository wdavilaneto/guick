/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:urnaController
 * @description
 * # urnaController
 * Modal Controller for Urna of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalUrnaController', ModalUrnaController);

    ModalUrnaController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'urnaService', 'cedulaService', 'eleicaoService', 'data'];

    function ModalUrnaController($state, $location, $modalInstance, jsog, urnaService , cedulaService, eleicaoService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.urna;
        vm.searchSelects = urnaService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                                            }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                urnaService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting urna with id:' + vm.entity.id);
                urnaService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('urnaEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
