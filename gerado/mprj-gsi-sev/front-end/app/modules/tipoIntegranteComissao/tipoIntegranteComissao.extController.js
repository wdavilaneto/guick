(function() {
	'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:tipoIntegranteComissaoExtController
 * @description
 * # tipoIntegranteComissaoExtController
 * Extended Controller pr Master Detail and other specific operations of the sevApp
 */
    angular.module('sevApp')
        .controller('tipoIntegranteComissaoExtController', tipoIntegranteComissaoExtController);

    tipoIntegranteComissaoExtController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'tipoIntegranteComissaoService' , 'integranteComissaoService'];

    function tipoIntegranteComissaoExtController($scope, $stateParams , $modal, $location, jsog, tipoIntegranteComissaoService , integranteComissaoService) {

        $scope.masterDetail = null;

        $scope.create = function ( parent ) {
            $scope.selected = null;
            $scope.selected = {
                tipoIntegranteComissao:parent
            };
        };

        // ******************************************************************************************
        // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
        // ******************************************************************************************
         $scope.selectedIntegranteComissao = null;

        $scope.getIntegranteComissao = function ( integranteComissaoId ) {
            integranteComissaoService.get( {'id':integranteComissaoId} , function (data) {
                $scope.selectedIntegranteComissao = data;
            }, function (data){
                // Case error or not found ..back to search filter
                $location.path('/tipoIntegranteComissao/'+ $stateParams.id);
            });
        };

        if ($stateParams.child == 'IntegranteComissao' && $stateParams.integranteComissaoId) {
            $scope.masterDetail = $stateParams.child;
            if ($stateParams.integranteComissaoId) {
                $scope.getIntegranteComissao( $stateParams.integranteComissaoId );
            }
        }

        $scope.gridIntegranteComissaoOptions = integranteComissaoService.createGridOptions(true);
        $scope.integranteComissaoPagination = $scope.gridIntegranteComissaoOptions.getPagination();

        $scope.findIntegranteComissao = function() {
            console.log('Realizando busca de 1 x N( integranteComissao.tipoIntegranteComissao.id):' + $stateParams.id);
            $scope.resultPage = null;
            integranteComissaoService.search( {'content':{ 'tipoIntegranteComissao':{id:$stateParams.id }},'pagination':$scope.gridIntegranteComissaoOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.searchIntegranteComissao = function() {
            // TODO Implement query search with fields
            console.log('Realizando busca de 1 x N( integranteComissao.tipoIntegranteComissao.id):' + $scope.entity.id);
            $scope.resultPage = null;
            integranteComissaoService.search( {'content':{ 'tipoIntegranteComissao':{id:$stateParams.id }},'pagination':$scope.gridIntegranteComissaoOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.removeIntegranteComissao = function (obj){
            console.log('Deleting integranteComissao with id:' + obj.id);
            integranteComissaoService.remove( obj , function ( successResult) {
                console.log(successResult);
                $scope.entity = {};
            });
        };
        $scope.saveIntegranteComissao = function (){
            console.log('Requesting save on ${integranteComissao}');
            $scope.$broadcast('show-errors-check-validity');
            // if any invalid validation
            if (!$scope.mainForm || $scope.mainForm.$invalid) {
                console.log('Validation Error');
                return;
            }

            $scope.searchSelects.toEntity($scope.selectedIntegranteComissao);
            integranteComissaoService.save( $scope.selectedIntegranteComissao , function (successResult) {
                console.log(successResult);
                $scope.entity = integranteComissaoService.create();
                if (!$scope.editMode) {
                    $scope.searchIntegranteComissao();
                } else {
                    $location.path('/tipoIntegranteComissao/'+ $stateParams.id + '/IntegranteComissao');
                }
            });
        };
        $scope.editIntegranteComissao = function (obj) {
            $scope.selectedIntegranteComissao = obj
            $location.path('/tipoIntegranteComissao/'+ $scope.entity.id +'/IntegranteComissao/' + obj.id );
        };
        $scope.createIntegranteComissao = function (obj) {
            $scope.selectedIntegranteComissao = integranteComissaoService.create();
            $scope.selectedIntegranteComissao.tipoIntegranteComissao = $scope.entity;
            $location.path('/tipoIntegranteComissao/'+ $scope.entity.id +'/IntegranteComissao/');
        };

        $scope.integranteComissaoPaginationChanged = function(newPage) {
            $scope.integranteComissaoPagination.currentPage = newPage;
            $scope.findIntegranteComissao();
        };

        $scope.isIntegranteComissaoActive = ($stateParams.child === 'IntegranteComissao' && $stateParams.integranteComissaoId) ;


        $scope.back = function (){
            if ($stateParams.child) {
                console.log('Redirecting to /TipoIntegranteComissao/'+ $stateParams.id + '/' + $stateParams.child);
                $location.path('/tipoIntegranteComissao/'+ $stateParams.id + '/' + $stateParams.child);
            } else {
                $window.history.back();
            }
        };

        $scope.clear = function () {
            $scope.selected = null;
        };

    }

})();
