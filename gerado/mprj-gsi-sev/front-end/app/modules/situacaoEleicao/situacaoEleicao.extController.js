(function() {
	'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:situacaoEleicaoExtController
 * @description
 * # situacaoEleicaoExtController
 * Extended Controller pr Master Detail and other specific operations of the sevApp
 */
    angular.module('sevApp')
        .controller('situacaoEleicaoExtController', situacaoEleicaoExtController);

    situacaoEleicaoExtController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'situacaoEleicaoService' , 'eleicaoService'];

    function situacaoEleicaoExtController($scope, $stateParams , $modal, $location, jsog, situacaoEleicaoService , eleicaoService) {

        $scope.masterDetail = null;

        $scope.create = function ( parent ) {
            $scope.selected = null;
            $scope.selected = {
                situacaoEleicao:parent
            };
        };

        // ******************************************************************************************
        // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
        // ******************************************************************************************
         $scope.selectedEleicao = null;

        $scope.getEleicao = function ( eleicaoId ) {
            eleicaoService.get( {'id':eleicaoId} , function (data) {
                $scope.selectedEleicao = data;
            }, function (data){
                // Case error or not found ..back to search filter
                $location.path('/situacaoEleicao/'+ $stateParams.id);
            });
        };

        if ($stateParams.child == 'Eleicao' && $stateParams.eleicaoId) {
            $scope.masterDetail = $stateParams.child;
            if ($stateParams.eleicaoId) {
                $scope.getEleicao( $stateParams.eleicaoId );
            }
        }

        $scope.gridEleicaoOptions = eleicaoService.createGridOptions(true);
        $scope.eleicaoPagination = $scope.gridEleicaoOptions.getPagination();

        $scope.findEleicao = function() {
            console.log('Realizando busca de 1 x N( eleicao.situacaoEleicao.id):' + $stateParams.id);
            $scope.resultPage = null;
            eleicaoService.search( {'content':{ 'situacaoEleicao':{id:$stateParams.id }},'pagination':$scope.gridEleicaoOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.searchEleicao = function() {
            // TODO Implement query search with fields
            console.log('Realizando busca de 1 x N( eleicao.situacaoEleicao.id):' + $scope.entity.id);
            $scope.resultPage = null;
            eleicaoService.search( {'content':{ 'situacaoEleicao':{id:$stateParams.id }},'pagination':$scope.gridEleicaoOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.removeEleicao = function (obj){
            console.log('Deleting eleicao with id:' + obj.id);
            eleicaoService.remove( obj , function ( successResult) {
                console.log(successResult);
                $scope.entity = {};
            });
        };
        $scope.saveEleicao = function (){
            console.log('Requesting save on ${eleicao}');
            $scope.$broadcast('show-errors-check-validity');
            // if any invalid validation
            if (!$scope.mainForm || $scope.mainForm.$invalid) {
                console.log('Validation Error');
                return;
            }

            $scope.searchSelects.toEntity($scope.selectedEleicao);
            eleicaoService.save( $scope.selectedEleicao , function (successResult) {
                console.log(successResult);
                $scope.entity = eleicaoService.create();
                if (!$scope.editMode) {
                    $scope.searchEleicao();
                } else {
                    $location.path('/situacaoEleicao/'+ $stateParams.id + '/Eleicao');
                }
            });
        };
        $scope.editEleicao = function (obj) {
            $scope.selectedEleicao = obj
            $location.path('/situacaoEleicao/'+ $scope.entity.id +'/Eleicao/' + obj.id );
        };
        $scope.createEleicao = function (obj) {
            $scope.selectedEleicao = eleicaoService.create();
            $scope.selectedEleicao.situacaoEleicao = $scope.entity;
            $location.path('/situacaoEleicao/'+ $scope.entity.id +'/Eleicao/');
        };

        $scope.eleicaoPaginationChanged = function(newPage) {
            $scope.eleicaoPagination.currentPage = newPage;
            $scope.findEleicao();
        };

        $scope.isEleicaoActive = ($stateParams.child === 'Eleicao' && $stateParams.eleicaoId) ;


        $scope.back = function (){
            if ($stateParams.child) {
                console.log('Redirecting to /SituacaoEleicao/'+ $stateParams.id + '/' + $stateParams.child);
                $location.path('/situacaoEleicao/'+ $stateParams.id + '/' + $stateParams.child);
            } else {
                $window.history.back();
            }
        };

        $scope.clear = function () {
            $scope.selected = null;
        };

    }

})();
