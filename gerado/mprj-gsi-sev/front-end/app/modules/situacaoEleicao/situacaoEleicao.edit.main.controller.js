
(function() {
	'use strict';

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
 * Controller of the sevApp
 */
angular.module('sevApp')
    .controller('situacaoEleicaoEditMainController', situacaoEleicaoEditMainController);

    situacaoEleicaoEditMainController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location', 'jsog', 'situacaoEleicaoService', 'eleicaoService'];

    function situacaoEleicaoEditMainController($scope, $state, $stateParams , $modal, $location, jsog, situacaoEleicaoService , eleicaoService ) {

        var vm = this;

        vm.searchSelects = situacaoEleicaoService.factorySearchSelect(); // Text Search Select utility Object

        // ***************************
        // Model Initializations
        // ***************************
            vm.maxSelectBoxSize = 300;

        vm.save = function () {
            //$scope.$broadcast('show-errors-check-validity');
            //if any invalid validation
            //if (!$scope.mainForm || $scope.mainForm.$invalid) {
            console.log('TODO: Validate Errors');
            //    return;
            //}

            console.log('Requesting save on situacaoEleicao');
                vm.searchSelects.toEntity(vm.entity);
                situacaoEleicaoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = situacaoEleicaoService.create();
                    $state.go('situacaoEleicaoSearch', {}, {reload: true});
            });
        };

        vm.saveContinue = function () {
            //$scope.$broadcast('show-errors-check-validity');
            //if any invalid validation
            //if (!$scope.mainForm || $scope.mainForm.$invalid) {
            console.log('TODO: Validate Errors');
            //    return;
            //}

            console.log('Requesting save on situacaoEleicao');
                vm.searchSelects.toEntity(vm.entity);
                situacaoEleicaoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    $state.go('situacaoEleicaoEdit.main', {id: successResult.id}, {reload: true});

            });
        };

        // ******************************************************************************************
        // Modals methods definitions
        // ******************************************************************************************
        // Create SituacaoEleicao Modal Window
            vm.open = function (size) {
                vm.searchSelects.toEntity(vm.entity);
            var modalInstance = $modal.open({
                templateUrl: 'createSituacaoEleicaoModal.html', controller: 'ModalSituacaoEleicaoController', size: size,
                resolve: {
                    data: function () {
                        return vm.entity;
                    }
                }
            });
            modalInstance.result.then(function (selectedItem) {
                    vm.selected = selectedItem;
                // Refreshing Result List
                    vm.entity = situacaoEleicaoService.create();
                    vm.search();
            }, function () {
                //dismiss
            });
        };

        // Create SituacaoEleicao Modal Window
            vm.view = function (size, entityName) {
                vm.searchSelects.toEntity(vm.entity);
            var modalInstance = $modal.open({
                templateUrl: ('view' + entityName + 'Modal.html'),
                controller: 'Modal' + entityName + 'Controller',
                size: size,
                resolve: {
                    data: function () {
                        return vm.entity;
                    }
                }
            });
        };

        vm.remove = function (obj) {
            var modalInstance = $modal.open({
                templateUrl: 'confirmRemoveSituacaoEleicaoModal.html',
                controller: 'ModalSituacaoEleicaoController',
                size: 'lg',
                resolve: {
                    data: function () {
                        return vm.entity;
                    },
                    readonly: function () {
                        return true;
                    }
                }
            });
            modalInstance.result.then(function (selectedItem) {
                //vm.search();
                    $state.go('situacaoEleicaoSearch', {}, {reload: true});
            }, function () {
                //dismiss
            });
        };

        // Inline Update Modal
            vm.editSituacaoEleicao = function (obj) {
            var modalInstance = $modal.open({
                templateUrl: 'createSituacaoEleicaoModal.html', controller: 'ModalSituacaoEleicaoController', size: 'lg',
                resolve: {
                    data: function () {
                        return obj;
                    },
                    readonly: function () {
                        return false;
                    }
                }
            });
            modalInstance.result.then(function (selectedItem) {
                    vm.selected = selectedItem;
                // Refreshing Result List
                    vm.scope.search();
            }, function () {
                //dismiss
            });
        };

        // ****************************************
        // Controller init (on load) service calls
        // ****************************************
        // if in edit mode get the wanted situacaoEleicao
        if ($stateParams.id) {
            var id = $stateParams.id;

            console.log('retrieving rename.id = ' + id  );

            situacaoEleicaoService.get( {'id': id} , function (data) {
                vm.entity = data;                           // set managed entity from service
                vm.original = vm.entity;                // backup original values;
                vm.searchSelects.fromEntity(vm.entity); // update search selects values;
            }, function (error){
                console.log(error);                             // TODO:
            });
        }

    }

})();
