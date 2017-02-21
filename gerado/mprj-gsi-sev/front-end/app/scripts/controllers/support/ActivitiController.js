'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:activitiController
 * @description
 * # activitiController
 * Controller of the sevApp
 */
angular.module('sevApp').controller('activitiController', ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'activitiService',   function ($scope, $$stateParams , $modal, $location, jsog, activitiService  ) {

    // For select boxes on screen TODO ...
    $scope.entity = {};

    activitiService.findAllDeployments( {} , function (data) {
        $scope.deployments = data.data;
    });

    // Search de todos os elementos incial ( quando entra na tela )
    activitiService.findAllResources( {id:1}, function (data) {
        $scope.processes = data;
    });

    activitiService.findProccessDefinitions( {} , function (data) {
        $scope.definitions = data;
    });

    $scope.findResources = function() {
        activitiService.findAllResources( {id:1} , function (data) {
            $scope.processes = data
        });
    };

    $scope.getStarters = function (id) {
        activitiService.getProccessDefinitionStarters( id , function (data) {
            $scope.starters = data;
        });
    };

    $scope.formatResourceUrl = function (url) {
        var splitted = url.split('/');
        return splitted[splitted.length-1];
    };

    $scope.$watch('entitySelected', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            $scope.findResources();
        }
    }, true);

}]);
