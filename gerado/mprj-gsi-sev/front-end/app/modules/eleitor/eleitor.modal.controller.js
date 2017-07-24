/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:eleitorController
 * @description
 * # eleitorController
 * Modal Controller for Eleitor of the sevApp
 */
(function() {

	'use strict';

    angular.module('sevApp')
        .controller('ModalEleitorController', ModalEleitorController);

    ModalEleitorController.$inject = ['$state', '$location', '$modalInstance', 'jsog', 'eleitorService', 'eleicaoService', 'data'];

    function ModalEleitorController($state, $location, $modalInstance, jsog, eleitorService , eleicaoService , data, readonly) {

        var vm = this;
        vm.entity = data;  //.eleitor;
        vm.searchSelects = eleitorService.factorySearchSelect();
        vm.searchSelects.fromEntity(vm.entity);

        if (!readonly) {
            // Selects to Fullfill select boxes
            // Begin block
                                                }
        // end block

            vm.save = function () {
            console.log('Saving Modal');
                vm.searchSelects.toEntity(vm.entity);
                eleitorService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = {};
                    $modalInstance.close(vm.entity);
            });
        };

        vm.remove = function () {
            console.log('Deleting eleitor with id:' + vm.entity.id);
                eleitorService.remove({id: vm.entity.id}, function (successResult) {
                console.log(successResult);
                    $modalInstance.close(vm.entity);
                    $state.go('eleitorEdit.main', {}, {reload: true});
            });
        };

            vm.cancel = function () {
                $modalInstance.dismiss('cancel');
        };

    }

})();
