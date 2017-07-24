(function() {
	'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:candidatoExtController
 * @description
 * # candidatoExtController
 * Extended Controller pr Master Detail and other specific operations of the sevApp
 */
    angular.module('sevApp')
        .controller('candidatoExtController', candidatoExtController);

    candidatoExtController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'candidatoService' , 'candidatoPadraoService', 'eleicaoService', 'resultadoApuracaoService', 'cedulaService'];

    function candidatoExtController($scope, $stateParams , $modal, $location, jsog, candidatoService , candidatoPadraoService, eleicaoService, resultadoApuracaoService, cedulaService) {

        $scope.masterDetail = null;

        $scope.create = function ( parent ) {
            $scope.selected = null;
            $scope.selected = {
                candidato:parent
            };
        };

        // ******************************************************************************************
        // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
        // ******************************************************************************************
         $scope.selectedResultadoApuracao = null;

        $scope.getResultadoApuracao = function ( resultadoApuracaoId ) {
            resultadoApuracaoService.get( {'id':resultadoApuracaoId} , function (data) {
                $scope.selectedResultadoApuracao = data;
            }, function (data){
                // Case error or not found ..back to search filter
                $location.path('/candidato/'+ $stateParams.id);
            });
        };

        if ($stateParams.child == 'ResultadoApuracao' && $stateParams.resultadoApuracaoId) {
            $scope.masterDetail = $stateParams.child;
            if ($stateParams.resultadoApuracaoId) {
                $scope.getResultadoApuracao( $stateParams.resultadoApuracaoId );
            }
        }

        $scope.gridResultadoApuracaoOptions = resultadoApuracaoService.createGridOptions(true);
        $scope.resultadoApuracaoPagination = $scope.gridResultadoApuracaoOptions.getPagination();

        $scope.findResultadoApuracao = function() {
            console.log('Realizando busca de 1 x N( resultadoApuracao.candidato.id):' + $stateParams.id);
            $scope.resultPage = null;
            resultadoApuracaoService.search( {'content':{ 'candidato':{id:$stateParams.id }},'pagination':$scope.gridResultadoApuracaoOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.searchResultadoApuracao = function() {
            // TODO Implement query search with fields
            console.log('Realizando busca de 1 x N( resultadoApuracao.candidato.id):' + $scope.entity.id);
            $scope.resultPage = null;
            resultadoApuracaoService.search( {'content':{ 'candidato':{id:$stateParams.id }},'pagination':$scope.gridResultadoApuracaoOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.removeResultadoApuracao = function (obj){
            console.log('Deleting resultadoApuracao with id:' + obj.id);
            resultadoApuracaoService.remove( obj , function ( successResult) {
                console.log(successResult);
                $scope.entity = {};
            });
        };
        $scope.saveResultadoApuracao = function (){
            console.log('Requesting save on ${resultadoApuracao}');
            $scope.$broadcast('show-errors-check-validity');
            // if any invalid validation
            if (!$scope.mainForm || $scope.mainForm.$invalid) {
                console.log('Validation Error');
                return;
            }

            $scope.searchSelects.toEntity($scope.selectedResultadoApuracao);
            resultadoApuracaoService.save( $scope.selectedResultadoApuracao , function (successResult) {
                console.log(successResult);
                $scope.entity = resultadoApuracaoService.create();
                if (!$scope.editMode) {
                    $scope.searchResultadoApuracao();
                } else {
                    $location.path('/candidato/'+ $stateParams.id + '/ResultadoApuracao');
                }
            });
        };
        $scope.editResultadoApuracao = function (obj) {
            $scope.selectedResultadoApuracao = obj
            $location.path('/candidato/'+ $scope.entity.id +'/ResultadoApuracao/' + obj.id );
        };
        $scope.createResultadoApuracao = function (obj) {
            $scope.selectedResultadoApuracao = resultadoApuracaoService.create();
            $scope.selectedResultadoApuracao.candidato = $scope.entity;
            $location.path('/candidato/'+ $scope.entity.id +'/ResultadoApuracao/');
        };

        $scope.resultadoApuracaoPaginationChanged = function(newPage) {
            $scope.resultadoApuracaoPagination.currentPage = newPage;
            $scope.findResultadoApuracao();
        };

        $scope.isResultadoApuracaoActive = ($stateParams.child === 'ResultadoApuracao' && $stateParams.resultadoApuracaoId) ;


        $scope.back = function (){
            if ($stateParams.child) {
                console.log('Redirecting to /Candidato/'+ $stateParams.id + '/' + $stateParams.child);
                $location.path('/candidato/'+ $stateParams.id + '/' + $stateParams.child);
            } else {
                $window.history.back();
            }
        };

        $scope.clear = function () {
            $scope.selected = null;
        };

    }

})();
