'use strict';

/* Services */
angular.module('sevApp').factory('MetricsService',function ($http) {
        return {
        get: function() {
            var promise = $http.get('/sev/api/metrics/metrics').then(function(response){
                return response.data;
            });
            return promise;
        }
    };
});

angular.module('sevApp').factory('ThreadDumpService', function ($http) {
    return {
        dump: function() {
            var promise = $http.get('/sev/api/dump').then(function(response){
                return response.data;
            });
            return promise;
        }
    };
});

angular.module('sevApp').factory('HealthCheckService', function ($rootScope, $http) {
    return {
        check: function() {
            var promise = $http.get('/sev/api/health').then(function(response){
                return response.data;
            });
            return promise;
        }
    };
});