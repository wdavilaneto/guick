(function() {
	'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:tipoEventoExtController
 * @description
 * # tipoEventoExtController
 * Extended Controller pr Master Detail and other specific operations of the sevApp
 */
    angular.module('sevApp')
        .controller('tipoEventoExtController', tipoEventoExtController);

    tipoEventoExtController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'tipoEventoService' , 'eventoService'];

    function tipoEventoExtController($scope, $stateParams , $modal, $location, jsog, tipoEventoService , eventoService) {

        $scope.masterDetail = null;

        $scope.create = function ( parent ) {
            $scope.selected = null;
            $scope.selected = {
                tipoEvento:parent
            };
        };

        // ******************************************************************************************
        // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
        // ******************************************************************************************
         $scope.selectedEvento = null;

        $scope.getEvento = function ( eventoId ) {
            eventoService.get( {'id':eventoId} , function (data) {
                $scope.selectedEvento = data;
            }, function (data){
                // Case error or not found ..back to search filter
                $location.path('/tipoEvento/'+ $stateParams.id);
            });
        };

        if ($stateParams.child == 'Evento' && $stateParams.eventoId) {
            $scope.masterDetail = $stateParams.child;
            if ($stateParams.eventoId) {
                $scope.getEvento( $stateParams.eventoId );
            }
        }

        $scope.gridEventoOptions = eventoService.createGridOptions(true);
        $scope.eventoPagination = $scope.gridEventoOptions.getPagination();

        $scope.findEvento = function() {
            console.log('Realizando busca de 1 x N( evento.tipoEvento.id):' + $stateParams.id);
            $scope.resultPage = null;
            eventoService.search( {'content':{ 'tipoEvento':{id:$stateParams.id }},'pagination':$scope.gridEventoOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.searchEvento = function() {
            // TODO Implement query search with fields
            console.log('Realizando busca de 1 x N( evento.tipoEvento.id):' + $scope.entity.id);
            $scope.resultPage = null;
            eventoService.search( {'content':{ 'tipoEvento':{id:$stateParams.id }},'pagination':$scope.gridEventoOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.removeEvento = function (obj){
            console.log('Deleting evento with id:' + obj.id);
            eventoService.remove( obj , function ( successResult) {
                console.log(successResult);
                $scope.entity = {};
            });
        };
        $scope.saveEvento = function (){
            console.log('Requesting save on ${evento}');
            $scope.$broadcast('show-errors-check-validity');
            // if any invalid validation
            if (!$scope.mainForm || $scope.mainForm.$invalid) {
                console.log('Validation Error');
                return;
            }

            $scope.searchSelects.toEntity($scope.selectedEvento);
            eventoService.save( $scope.selectedEvento , function (successResult) {
                console.log(successResult);
                $scope.entity = eventoService.create();
                if (!$scope.editMode) {
                    $scope.searchEvento();
                } else {
                    $location.path('/tipoEvento/'+ $stateParams.id + '/Evento');
                }
            });
        };
        $scope.editEvento = function (obj) {
            $scope.selectedEvento = obj
            $location.path('/tipoEvento/'+ $scope.entity.id +'/Evento/' + obj.id );
        };
        $scope.createEvento = function (obj) {
            $scope.selectedEvento = eventoService.create();
            $scope.selectedEvento.tipoEvento = $scope.entity;
            $location.path('/tipoEvento/'+ $scope.entity.id +'/Evento/');
        };

        $scope.eventoPaginationChanged = function(newPage) {
            $scope.eventoPagination.currentPage = newPage;
            $scope.findEvento();
        };

        $scope.isEventoActive = ($stateParams.child === 'Evento' && $stateParams.eventoId) ;


        $scope.back = function (){
            if ($stateParams.child) {
                console.log('Redirecting to /TipoEvento/'+ $stateParams.id + '/' + $stateParams.child);
                $location.path('/tipoEvento/'+ $stateParams.id + '/' + $stateParams.child);
            } else {
                $window.history.back();
            }
        };

        $scope.clear = function () {
            $scope.selected = null;
        };

    }

})();
