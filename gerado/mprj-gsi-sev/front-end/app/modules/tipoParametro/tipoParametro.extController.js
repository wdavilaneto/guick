(function() {
	'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:tipoParametroExtController
 * @description
 * # tipoParametroExtController
 * Extended Controller pr Master Detail and other specific operations of the sevApp
 */
    angular.module('sevApp')
        .controller('tipoParametroExtController', tipoParametroExtController);

    tipoParametroExtController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'tipoParametroService' , 'parametroService'];

    function tipoParametroExtController($scope, $stateParams , $modal, $location, jsog, tipoParametroService , parametroService) {

        $scope.masterDetail = null;

        $scope.create = function ( parent ) {
            $scope.selected = null;
            $scope.selected = {
                tipoParametro:parent
            };
        };

        // ******************************************************************************************
        // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
        // ******************************************************************************************
         $scope.selectedParametro = null;

        $scope.getParametro = function ( parametroId ) {
            parametroService.get( {'id':parametroId} , function (data) {
                $scope.selectedParametro = data;
            }, function (data){
                // Case error or not found ..back to search filter
                $location.path('/tipoParametro/'+ $stateParams.id);
            });
        };

        if ($stateParams.child == 'Parametro' && $stateParams.parametroId) {
            $scope.masterDetail = $stateParams.child;
            if ($stateParams.parametroId) {
                $scope.getParametro( $stateParams.parametroId );
            }
        }

        $scope.gridParametroOptions = parametroService.createGridOptions(true);
        $scope.parametroPagination = $scope.gridParametroOptions.getPagination();

        $scope.findParametro = function() {
            console.log('Realizando busca de 1 x N( parametro.tipoParametro.id):' + $stateParams.id);
            $scope.resultPage = null;
            parametroService.search( {'content':{ 'tipoParametro':{id:$stateParams.id }},'pagination':$scope.gridParametroOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.searchParametro = function() {
            // TODO Implement query search with fields
            console.log('Realizando busca de 1 x N( parametro.tipoParametro.id):' + $scope.entity.id);
            $scope.resultPage = null;
            parametroService.search( {'content':{ 'tipoParametro':{id:$stateParams.id }},'pagination':$scope.gridParametroOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.removeParametro = function (obj){
            console.log('Deleting parametro with id:' + obj.id);
            parametroService.remove( obj , function ( successResult) {
                console.log(successResult);
                $scope.entity = {};
            });
        };
        $scope.saveParametro = function (){
            console.log('Requesting save on ${parametro}');
            $scope.$broadcast('show-errors-check-validity');
            // if any invalid validation
            if (!$scope.mainForm || $scope.mainForm.$invalid) {
                console.log('Validation Error');
                return;
            }

            $scope.searchSelects.toEntity($scope.selectedParametro);
            parametroService.save( $scope.selectedParametro , function (successResult) {
                console.log(successResult);
                $scope.entity = parametroService.create();
                if (!$scope.editMode) {
                    $scope.searchParametro();
                } else {
                    $location.path('/tipoParametro/'+ $stateParams.id + '/Parametro');
                }
            });
        };
        $scope.editParametro = function (obj) {
            $scope.selectedParametro = obj
            $location.path('/tipoParametro/'+ $scope.entity.id +'/Parametro/' + obj.id );
        };
        $scope.createParametro = function (obj) {
            $scope.selectedParametro = parametroService.create();
            $scope.selectedParametro.tipoParametro = $scope.entity;
            $location.path('/tipoParametro/'+ $scope.entity.id +'/Parametro/');
        };

        $scope.parametroPaginationChanged = function(newPage) {
            $scope.parametroPagination.currentPage = newPage;
            $scope.findParametro();
        };

        $scope.isParametroActive = ($stateParams.child === 'Parametro' && $stateParams.parametroId) ;


        $scope.back = function (){
            if ($stateParams.child) {
                console.log('Redirecting to /TipoParametro/'+ $stateParams.id + '/' + $stateParams.child);
                $location.path('/tipoParametro/'+ $stateParams.id + '/' + $stateParams.child);
            } else {
                $window.history.back();
            }
        };

        $scope.clear = function () {
            $scope.selected = null;
        };

    }

})();
