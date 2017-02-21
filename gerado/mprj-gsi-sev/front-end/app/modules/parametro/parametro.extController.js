(function() {
	'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:parametroExtController
 * @description
 * # parametroExtController
 * Extended Controller pr Master Detail and other specific operations of the sevApp
 */
    angular.module('sevApp')
        .controller('parametroExtController', parametroExtController);

    parametroExtController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'parametroService' , 'eleicaoParametroService', 'tipoParametroService'];

    function parametroExtController($scope, $stateParams , $modal, $location, jsog, parametroService , eleicaoParametroService, tipoParametroService) {

        $scope.masterDetail = null;

        $scope.create = function ( parent ) {
            $scope.selected = null;
            $scope.selected = {
                parametro:parent
            };
        };

        // ******************************************************************************************
        // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
        // ******************************************************************************************
         $scope.selectedEleicaoParametro = null;

        $scope.getEleicaoParametro = function ( eleicaoParametroId ) {
            eleicaoParametroService.get( {'id':eleicaoParametroId} , function (data) {
                $scope.selectedEleicaoParametro = data;
            }, function (data){
                // Case error or not found ..back to search filter
                $location.path('/parametro/'+ $stateParams.id);
            });
        };

        if ($stateParams.child == 'EleicaoParametro' && $stateParams.eleicaoParametroId) {
            $scope.masterDetail = $stateParams.child;
            if ($stateParams.eleicaoParametroId) {
                $scope.getEleicaoParametro( $stateParams.eleicaoParametroId );
            }
        }

        $scope.gridEleicaoParametroOptions = eleicaoParametroService.createGridOptions(true);
        $scope.eleicaoParametroPagination = $scope.gridEleicaoParametroOptions.getPagination();

        $scope.findEleicaoParametro = function() {
            console.log('Realizando busca de 1 x N( eleicaoParametro.parametro.id):' + $stateParams.id);
            $scope.resultPage = null;
            eleicaoParametroService.search( {'content':{ 'parametro':{id:$stateParams.id }},'pagination':$scope.gridEleicaoParametroOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.searchEleicaoParametro = function() {
            // TODO Implement query search with fields
            console.log('Realizando busca de 1 x N( eleicaoParametro.parametro.id):' + $scope.entity.id);
            $scope.resultPage = null;
            eleicaoParametroService.search( {'content':{ 'parametro':{id:$stateParams.id }},'pagination':$scope.gridEleicaoParametroOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.removeEleicaoParametro = function (obj){
            console.log('Deleting eleicaoParametro with id:' + obj.id);
            eleicaoParametroService.remove( obj , function ( successResult) {
                console.log(successResult);
                $scope.entity = {};
            });
        };
        $scope.saveEleicaoParametro = function (){
            console.log('Requesting save on ${eleicaoParametro}');
            $scope.$broadcast('show-errors-check-validity');
            // if any invalid validation
            if (!$scope.mainForm || $scope.mainForm.$invalid) {
                console.log('Validation Error');
                return;
            }

            $scope.searchSelects.toEntity($scope.selectedEleicaoParametro);
            eleicaoParametroService.save( $scope.selectedEleicaoParametro , function (successResult) {
                console.log(successResult);
                $scope.entity = eleicaoParametroService.create();
                if (!$scope.editMode) {
                    $scope.searchEleicaoParametro();
                } else {
                    $location.path('/parametro/'+ $stateParams.id + '/EleicaoParametro');
                }
            });
        };
        $scope.editEleicaoParametro = function (obj) {
            $scope.selectedEleicaoParametro = obj
            $location.path('/parametro/'+ $scope.entity.id +'/EleicaoParametro/' + obj.id );
        };
        $scope.createEleicaoParametro = function (obj) {
            $scope.selectedEleicaoParametro = eleicaoParametroService.create();
            $scope.selectedEleicaoParametro.parametro = $scope.entity;
            $location.path('/parametro/'+ $scope.entity.id +'/EleicaoParametro/');
        };

        $scope.eleicaoParametroPaginationChanged = function(newPage) {
            $scope.eleicaoParametroPagination.currentPage = newPage;
            $scope.findEleicaoParametro();
        };

        $scope.isEleicaoParametroActive = ($stateParams.child === 'EleicaoParametro' && $stateParams.eleicaoParametroId) ;


        $scope.back = function (){
            if ($stateParams.child) {
                console.log('Redirecting to /Parametro/'+ $stateParams.id + '/' + $stateParams.child);
                $location.path('/parametro/'+ $stateParams.id + '/' + $stateParams.child);
            } else {
                $window.history.back();
            }
        };

        $scope.clear = function () {
            $scope.selected = null;
        };

    }

})();
