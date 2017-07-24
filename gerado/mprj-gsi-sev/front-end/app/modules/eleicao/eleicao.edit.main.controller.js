
(function() {
	'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:eleicaoController
 * @description
 * # eleicaoController
 * Controller of the sevApp
 */
angular.module('sevApp')
    .controller('eleicaoEditMainController', eleicaoEditMainController);

    eleicaoEditMainController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location', 'jsog', 'eleicaoService', 'arquivoRelatorioService', 'candidatoService', 'situacaoEleicaoService', 'eleicaoParametroService', 'eleitorService', 'eventoService', 'integranteComissaoService', 'tipoFiltroPessoaService', 'urnaService'];

    function eleicaoEditMainController($scope, $state, $stateParams , $modal, $location, jsog, eleicaoService , arquivoRelatorioService, candidatoService, situacaoEleicaoService, eleicaoParametroService, eleitorService, eventoService, integranteComissaoService, tipoFiltroPessoaService, urnaService ) {

        var vm = this;

        vm.searchSelects = eleicaoService.factorySearchSelect(); // Text Search Select utility Object

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

            console.log('Requesting save on eleicao');
                vm.searchSelects.toEntity(vm.entity);
                eleicaoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    vm.entity = eleicaoService.create();
                    $state.go('eleicaoSearch', {}, {reload: true});
            });
        };

        vm.saveContinue = function () {
            //$scope.$broadcast('show-errors-check-validity');
            //if any invalid validation
            //if (!$scope.mainForm || $scope.mainForm.$invalid) {
            console.log('TODO: Validate Errors');
            //    return;
            //}

            console.log('Requesting save on eleicao');
                vm.searchSelects.toEntity(vm.entity);
                eleicaoService.save(vm.entity, function (successResult) {
                console.log(successResult);
                    $state.go('eleicaoEdit.main', {id: successResult.id}, {reload: true});

            });
        };

        // ******************************************************************************************
        // Modals methods definitions
        // ******************************************************************************************
        // Create Eleicao Modal Window
            vm.open = function (size) {
                vm.searchSelects.toEntity(vm.entity);
            var modalInstance = $modal.open({
                templateUrl: 'createEleicaoModal.html', controller: 'ModalEleicaoController', size: size,
                resolve: {
                    data: function () {
                        return vm.entity;
                    }
                }
            });
            modalInstance.result.then(function (selectedItem) {
                    vm.selected = selectedItem;
                // Refreshing Result List
                    vm.entity = eleicaoService.create();
                    vm.search();
            }, function () {
                //dismiss
            });
        };

        // Create Eleicao Modal Window
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
                templateUrl: 'confirmRemoveEleicaoModal.html',
                controller: 'ModalEleicaoController',
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
                    $state.go('eleicaoSearch', {}, {reload: true});
            }, function () {
                //dismiss
            });
        };

        // Inline Update Modal
            vm.editEleicao = function (obj) {
            var modalInstance = $modal.open({
                templateUrl: 'createEleicaoModal.html', controller: 'ModalEleicaoController', size: 'lg',
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
        // if in edit mode get the wanted eleicao
        if ($stateParams.id) {
            var id = $stateParams.id;

            console.log('retrieving rename.id = ' + id  );

            eleicaoService.get( {'id': id} , function (data) {
                vm.entity = data;                           // set managed entity from service
                vm.original = vm.entity;                // backup original values;
                vm.searchSelects.fromEntity(vm.entity); // update search selects values;
            }, function (error){
                console.log(error);                             // TODO:
            });
        }

    }

})();
