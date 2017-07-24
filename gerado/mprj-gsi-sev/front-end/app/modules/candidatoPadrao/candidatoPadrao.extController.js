(function() {
	'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:candidatoPadraoExtController
 * @description
 * # candidatoPadraoExtController
 * Extended Controller pr Master Detail and other specific operations of the sevApp
 */
    angular.module('sevApp')
        .controller('candidatoPadraoExtController', candidatoPadraoExtController);

    candidatoPadraoExtController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'candidatoPadraoService' , 'candidatoService'];

    function candidatoPadraoExtController($scope, $stateParams , $modal, $location, jsog, candidatoPadraoService , candidatoService) {

        $scope.masterDetail = null;

        $scope.create = function ( parent ) {
            $scope.selected = null;
            $scope.selected = {
                candidatoPadrao:parent
            };
        };

        // ******************************************************************************************
        // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
        // ******************************************************************************************
         $scope.selectedCandidato = null;

        $scope.getCandidato = function ( candidatoId ) {
            candidatoService.get( {'id':candidatoId} , function (data) {
                $scope.selectedCandidato = data;
            }, function (data){
                // Case error or not found ..back to search filter
                $location.path('/candidatoPadrao/'+ $stateParams.id);
            });
        };

        if ($stateParams.child == 'Candidato' && $stateParams.candidatoId) {
            $scope.masterDetail = $stateParams.child;
            if ($stateParams.candidatoId) {
                $scope.getCandidato( $stateParams.candidatoId );
            }
        }

        $scope.gridCandidatoOptions = candidatoService.createGridOptions(true);
        $scope.candidatoPagination = $scope.gridCandidatoOptions.getPagination();

        $scope.findCandidato = function() {
            console.log('Realizando busca de 1 x N( candidato.candidatoPadrao.id):' + $stateParams.id);
            $scope.resultPage = null;
            candidatoService.search( {'content':{ 'candidatoPadrao':{id:$stateParams.id }},'pagination':$scope.gridCandidatoOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.searchCandidato = function() {
            // TODO Implement query search with fields
            console.log('Realizando busca de 1 x N( candidato.candidatoPadrao.id):' + $scope.entity.id);
            $scope.resultPage = null;
            candidatoService.search( {'content':{ 'candidatoPadrao':{id:$stateParams.id }},'pagination':$scope.gridCandidatoOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.removeCandidato = function (obj){
            console.log('Deleting candidato with id:' + obj.id);
            candidatoService.remove( obj , function ( successResult) {
                console.log(successResult);
                $scope.entity = {};
            });
        };
        $scope.saveCandidato = function (){
            console.log('Requesting save on ${candidato}');
            $scope.$broadcast('show-errors-check-validity');
            // if any invalid validation
            if (!$scope.mainForm || $scope.mainForm.$invalid) {
                console.log('Validation Error');
                return;
            }

            $scope.searchSelects.toEntity($scope.selectedCandidato);
            candidatoService.save( $scope.selectedCandidato , function (successResult) {
                console.log(successResult);
                $scope.entity = candidatoService.create();
                if (!$scope.editMode) {
                    $scope.searchCandidato();
                } else {
                    $location.path('/candidatoPadrao/'+ $stateParams.id + '/Candidato');
                }
            });
        };
        $scope.editCandidato = function (obj) {
            $scope.selectedCandidato = obj
            $location.path('/candidatoPadrao/'+ $scope.entity.id +'/Candidato/' + obj.id );
        };
        $scope.createCandidato = function (obj) {
            $scope.selectedCandidato = candidatoService.create();
            $scope.selectedCandidato.candidatoPadrao = $scope.entity;
            $location.path('/candidatoPadrao/'+ $scope.entity.id +'/Candidato/');
        };

        $scope.candidatoPaginationChanged = function(newPage) {
            $scope.candidatoPagination.currentPage = newPage;
            $scope.findCandidato();
        };

        $scope.isCandidatoActive = ($stateParams.child === 'Candidato' && $stateParams.candidatoId) ;


        $scope.back = function (){
            if ($stateParams.child) {
                console.log('Redirecting to /CandidatoPadrao/'+ $stateParams.id + '/' + $stateParams.child);
                $location.path('/candidatoPadrao/'+ $stateParams.id + '/' + $stateParams.child);
            } else {
                $window.history.back();
            }
        };

        $scope.clear = function () {
            $scope.selected = null;
        };

    }

})();
