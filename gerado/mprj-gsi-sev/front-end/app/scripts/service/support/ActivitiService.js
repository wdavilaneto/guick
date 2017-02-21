'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:dashboardController
 * @description
 * # dashboardController
 * Controller of the sevApp
 */
var sevServices = angular.module('sevServices');

sevServices.factory('activitiService', ['$resource',
    function ($resource) {
        var service = $resource('/actdoc/api/repository/deployments/:id', {deploymentId:'@id'}, {
            'findAllDeployments': { method: 'GET', isArray: false},
            'findAllResources': { method: 'GET', url:"/actdoc/api/repository/deployments/:id/resources", isArray: true},
            'findResources': { method: 'GET', url:"/actdoc/api/repository/deployments/:id/resources", isArray: true},
            'findProccessDefinitions':{method: 'GET', url:"/actdoc/api/repository/process-definitions", isArray:false},
            'getProccessDefinition':{method: 'GET', url:"/actdoc/api/repository/process-definitions/:id", isArray:false},
            'getProccessDefinitionModel':{method: 'GET', url:"/actdoc/api/repository/process-definitions/:id/model", isArray:false},
            'getProccessDefinitionStarters':{method: 'GET', url:"/actdoc/api/repository/process-definitions/:id/identitylinks", isArray:true}
        });
        return service;
    }
]);

sevServices.factory('userService', function ($resource) {
    var data = $resource('/sev/api/identity/users/:user', {user: "@user"});
    return data;
});

sevServices.factory('groupService', function ($resource) {
    var data = $resource('/sev/api/identity/groups/:group', {group: "@group"});
    return data;
});
