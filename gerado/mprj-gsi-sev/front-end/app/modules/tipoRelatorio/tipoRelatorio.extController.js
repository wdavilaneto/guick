(function() {
	'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:tipoRelatorioExtController
 * @description
 * # tipoRelatorioExtController
 * Extended Controller pr Master Detail and other specific operations of the sevApp
 */
    angular.module('sevApp')
        .controller('tipoRelatorioExtController', tipoRelatorioExtController);

    tipoRelatorioExtController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'tipoRelatorioService' , 'arquivoRelatorioService'];

    function tipoRelatorioExtController($scope, $stateParams , $modal, $location, jsog, tipoRelatorioService , arquivoRelatorioService) {

        $scope.masterDetail = null;

        $scope.create = function ( parent ) {
            $scope.selected = null;
            $scope.selected = {
                tipoRelatorio:parent
            };
        };

        // ******************************************************************************************
        // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
        // ******************************************************************************************
         $scope.selectedArquivoRelatorio = null;

        $scope.getArquivoRelatorio = function ( arquivoRelatorioId ) {
            arquivoRelatorioService.get( {'id':arquivoRelatorioId} , function (data) {
                $scope.selectedArquivoRelatorio = data;
            }, function (data){
                // Case error or not found ..back to search filter
                $location.path('/tipoRelatorio/'+ $stateParams.id);
            });
        };

        if ($stateParams.child == 'ArquivoRelatorio' && $stateParams.arquivoRelatorioId) {
            $scope.masterDetail = $stateParams.child;
            if ($stateParams.arquivoRelatorioId) {
                $scope.getArquivoRelatorio( $stateParams.arquivoRelatorioId );
            }
        }

        $scope.gridArquivoRelatorioOptions = arquivoRelatorioService.createGridOptions(true);
        $scope.arquivoRelatorioPagination = $scope.gridArquivoRelatorioOptions.getPagination();

        $scope.findArquivoRelatorio = function() {
            console.log('Realizando busca de 1 x N( arquivoRelatorio.tipoRelatorio.id):' + $stateParams.id);
            $scope.resultPage = null;
            arquivoRelatorioService.search( {'content':{ 'tipoRelatorio':{id:$stateParams.id }},'pagination':$scope.gridArquivoRelatorioOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.searchArquivoRelatorio = function() {
            // TODO Implement query search with fields
            console.log('Realizando busca de 1 x N( arquivoRelatorio.tipoRelatorio.id):' + $scope.entity.id);
            $scope.resultPage = null;
            arquivoRelatorioService.search( {'content':{ 'tipoRelatorio':{id:$stateParams.id }},'pagination':$scope.gridArquivoRelatorioOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.removeArquivoRelatorio = function (obj){
            console.log('Deleting arquivoRelatorio with id:' + obj.id);
            arquivoRelatorioService.remove( obj , function ( successResult) {
                console.log(successResult);
                $scope.entity = {};
            });
        };
        $scope.saveArquivoRelatorio = function (){
            console.log('Requesting save on ${arquivoRelatorio}');
            $scope.$broadcast('show-errors-check-validity');
            // if any invalid validation
            if (!$scope.mainForm || $scope.mainForm.$invalid) {
                console.log('Validation Error');
                return;
            }

            $scope.searchSelects.toEntity($scope.selectedArquivoRelatorio);
            arquivoRelatorioService.save( $scope.selectedArquivoRelatorio , function (successResult) {
                console.log(successResult);
                $scope.entity = arquivoRelatorioService.create();
                if (!$scope.editMode) {
                    $scope.searchArquivoRelatorio();
                } else {
                    $location.path('/tipoRelatorio/'+ $stateParams.id + '/ArquivoRelatorio');
                }
            });
        };
        $scope.editArquivoRelatorio = function (obj) {
            $scope.selectedArquivoRelatorio = obj
            $location.path('/tipoRelatorio/'+ $scope.entity.id +'/ArquivoRelatorio/' + obj.id );
        };
        $scope.createArquivoRelatorio = function (obj) {
            $scope.selectedArquivoRelatorio = arquivoRelatorioService.create();
            $scope.selectedArquivoRelatorio.tipoRelatorio = $scope.entity;
            $location.path('/tipoRelatorio/'+ $scope.entity.id +'/ArquivoRelatorio/');
        };

        $scope.arquivoRelatorioPaginationChanged = function(newPage) {
            $scope.arquivoRelatorioPagination.currentPage = newPage;
            $scope.findArquivoRelatorio();
        };

        $scope.isArquivoRelatorioActive = ($stateParams.child === 'ArquivoRelatorio' && $stateParams.arquivoRelatorioId) ;


        $scope.back = function (){
            if ($stateParams.child) {
                console.log('Redirecting to /TipoRelatorio/'+ $stateParams.id + '/' + $stateParams.child);
                $location.path('/tipoRelatorio/'+ $stateParams.id + '/' + $stateParams.child);
            } else {
                $window.history.back();
            }
        };

        $scope.clear = function () {
            $scope.selected = null;
        };

    }

})();
