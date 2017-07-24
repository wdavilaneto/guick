(function() {
	'use strict';

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
 * Controller of the sevApp
 */
angular.module('sevApp')
    .controller('cedulaController', cedulaController);

    cedulaController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'cedulaService' , 'urnaService', 'candidatoService'];

    function cedulaController($scope, $stateParams , $modal, $location, jsog, cedulaService , urnaService, candidatoService ) {

        var vm = this;

        // ***************************
        // Model Initializations
        // ***************************
        vm.entity = cedulaService.create();
        vm.options = { language: 'en', allowedContent: true, entities: false };
        vm.editMode = false;
        vm.maxSelectBoxSize = 300;
        vm.textSearchActive = true;
        vm.searchField = {text: ''};
        vm.searchSelects = cedulaService.factorySearchSelect(); // Text Search Select utility Object

        // Elementos usados pelo ui-grid DEVEM estar no $scope
        $scope.gridOptions = cedulaService.createGridOptions( true);
        $scope.pagination = $scope.gridOptions.getPagination(); // just for ease use/access
        $scope.resultPage = {}; // Paged result for search filter

        // Search By Example Method
        vm.search = search;

        vm.newSearch = newSearch;

        vm.clearForm = clearForm;

        // ******************************************************************************************
        // Modals methods definitions
        // ******************************************************************************************
        // Create Cedula Modal Window
        vm.open = open;

        // Create Cedula Modal Window
        vm.view = view;

        vm.setTextSearchActive = setTextSearchActive;

        vm.pageChanged = pageChanged;


        function search(page) {
            console.log('Realizando busca');
            // prepare page request
            if (vm.textSearchActive) {
                    cedulaService.searchText( {'content': vm.searchField.text ,'pagination':page} , function (data) {
                    $scope.resultPage = data;
                    $scope.resultPage.content = jsog.decode(data.content);
                    $scope.gridOptions.totalItems = $scope.resultPage.totalElements;
                });
            } else {
                // prepare search(d) text object
                vm.searchSelects.toEntity(vm.entity);
                cedulaService.search( {'content':vm.entity,'pagination':$scope.pagination.getPageRequest()} , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        $scope.resultPage.content = data.content;
                    }
                });
            }
        }

        function newSearch() {
            $scope.pagination.currentPage = 1;
            vm.search();
        }

        function clearForm() {
            console.log('Limpando form filter');
            vm.entity = cedulaService.create();
            vm.searchField.text = '';
            vm.searchSelects.clear();
        }

        function open(size) {
            vm.searchSelects.toEntity(vm.entity);
            var modalInstance = $modal.open({
                templateUrl: 'createCedulaModal.html', controller: 'ModalCedulaController as vm', size: size,
                resolve: {
                    data: function () {return vm.entity;}
                }
            });
            modalInstance.result.then(function (selectedItem) {
                vm.selected = selectedItem;
                // Refreshing Result List
                vm.entity = cedulaService.create();
                vm.search();
            }, function () {
                //dismiss
            });
        }

        function view(size, entityName) {
            vm.searchSelects.toEntity(vm.entity);
            var modalInstance = $modal.open({
                templateUrl: ('view' +entityName+ 'Modal.html'), controller: 'Modal' + entityName + 'Controller', size: size,
                resolve: {
                    data: function () {
                        return vm.entity;
                    }
                }
            });
        }

        function setTextSearchActive(value) {
            vm.textSearchActive = value;
        }

        function pageChanged(newPage) {
            $scope.pagination.currentPage = newPage;
        }

        // Search de todos os elementos inicial ( quando entra na tela )
        if (!vm.editMode) {
            cedulaService.findAll( $scope.pagination.getPageRequest() , function (data) {
                $scope.resultPage = data;
                $scope.resultPage.content = jsog.decode(data.content);
                $scope.gridOptions.totalItems = $scope.resultPage.totalElements;
            });
        }

        // Selects to Fullfill select boxes
        // Begin block
        urnaService.findAll( {page:1 , size: vm.maxSelectBoxSize}  , function (data) {
             vm.urnaList = jsog.decode(data.content);
        });
        // end block

        // ************************************
        // watches
        // ************************************
        $scope.$watch('pagination', function (newVal, oldVal) {
            if ((newVal.pageSize !== oldVal.pageSize) || (newVal.currentPage !== oldVal.currentPage)) {
                vm.search();
            }
        }, true);

        var onRegisterApi = function(gridApi) {
                $scope.gridApi = gridApi;
            gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
                var pageable = {page: newPage, size: pageSize};
                vm.search(pageable);
            });
        }

        vm.cssClassByStatus = cssClassByStatus;

        function cssClassByStatus(status) {
            var cssClasses = {PENDENTE: 'bkg-amethyst', CONCLUIDO: 'bkg-river', CANCELADO: 'bkg-silver'};
            try {
                return cssClasses[status];
            } catch (ex){
                return 'bkg-amethyst';
            }
        }

        $scope.gridOptions.onRegisterApi = onRegisterApi;
    }

})();
