(function() {
	'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:urnaExtController
 * @description
 * # urnaExtController
 * Extended Controller pr Master Detail and other specific operations of the sevApp
 */
    angular.module('sevApp')
        .controller('urnaExtController', urnaExtController);

    urnaExtController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'urnaService' , 'cedulaService', 'eleicaoService'];

    function urnaExtController($scope, $stateParams , $modal, $location, jsog, urnaService , cedulaService, eleicaoService) {

        $scope.masterDetail = null;

        $scope.create = function ( parent ) {
            $scope.selected = null;
            $scope.selected = {
                urna:parent
            };
        };

        // ******************************************************************************************
        // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
        // ******************************************************************************************
         $scope.selectedCedula = null;

        $scope.getCedula = function ( cedulaId ) {
            cedulaService.get( {'id':cedulaId} , function (data) {
                $scope.selectedCedula = data;
            }, function (data){
                // Case error or not found ..back to search filter
                $location.path('/urna/'+ $stateParams.id);
            });
        };

        if ($stateParams.child == 'Cedula' && $stateParams.cedulaId) {
            $scope.masterDetail = $stateParams.child;
            if ($stateParams.cedulaId) {
                $scope.getCedula( $stateParams.cedulaId );
            }
        }

        $scope.gridCedulaOptions = cedulaService.createGridOptions(true);
        $scope.cedulaPagination = $scope.gridCedulaOptions.getPagination();

        $scope.findCedula = function() {
            console.log('Realizando busca de 1 x N( cedula.urna.id):' + $stateParams.id);
            $scope.resultPage = null;
            cedulaService.search( {'content':{ 'urna':{id:$stateParams.id }},'pagination':$scope.gridCedulaOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.searchCedula = function() {
            // TODO Implement query search with fields
            console.log('Realizando busca de 1 x N( cedula.urna.id):' + $scope.entity.id);
            $scope.resultPage = null;
            cedulaService.search( {'content':{ 'urna':{id:$stateParams.id }},'pagination':$scope.gridCedulaOptions.getPageRequest()}  , function (data) {
                $scope.resultPage = data;
                try {
                    $scope.resultPage.content = jsog.decode(data.content);
                } catch (exception) {
                    console.log('Object with self rererence');
                    $scope.resultPage.content = data.content;
                }
            });
        };
        $scope.removeCedula = function (obj){
            console.log('Deleting cedula with id:' + obj.id);
            cedulaService.remove( obj , function ( successResult) {
                console.log(successResult);
                $scope.entity = {};
            });
        };
        $scope.saveCedula = function (){
            console.log('Requesting save on ${cedula}');
            $scope.$broadcast('show-errors-check-validity');
            // if any invalid validation
            if (!$scope.mainForm || $scope.mainForm.$invalid) {
                console.log('Validation Error');
                return;
            }

            $scope.searchSelects.toEntity($scope.selectedCedula);
            cedulaService.save( $scope.selectedCedula , function (successResult) {
                console.log(successResult);
                $scope.entity = cedulaService.create();
                if (!$scope.editMode) {
                    $scope.searchCedula();
                } else {
                    $location.path('/urna/'+ $stateParams.id + '/Cedula');
                }
            });
        };
        $scope.editCedula = function (obj) {
            $scope.selectedCedula = obj
            $location.path('/urna/'+ $scope.entity.id +'/Cedula/' + obj.id );
        };
        $scope.createCedula = function (obj) {
            $scope.selectedCedula = cedulaService.create();
            $scope.selectedCedula.urna = $scope.entity;
            $location.path('/urna/'+ $scope.entity.id +'/Cedula/');
        };

        $scope.cedulaPaginationChanged = function(newPage) {
            $scope.cedulaPagination.currentPage = newPage;
            $scope.findCedula();
        };

        $scope.isCedulaActive = ($stateParams.child === 'Cedula' && $stateParams.cedulaId) ;


        $scope.back = function (){
            if ($stateParams.child) {
                console.log('Redirecting to /Urna/'+ $stateParams.id + '/' + $stateParams.child);
                $location.path('/urna/'+ $stateParams.id + '/' + $stateParams.child);
            } else {
                $window.history.back();
            }
        };

        $scope.clear = function () {
            $scope.selected = null;
        };

    }

})();
