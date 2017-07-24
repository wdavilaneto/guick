(function() {
    'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     *
     * @ngdoc function
     * @name rhApp.controller:loginController
     * @description
     * # loginController
     * Controller of the rhApp
     */
    angular.module('sevApp')
        .controller('loginController', ['$scope', 'loginService' , 'authService' , '$document', '$animate', function ($scope, loginService , authService,$document, $animate) {

        $scope.submit = function() {

            var element = angular.element($document[0].querySelector('#login-holder'));
            $animate.removeClass(element, 'shake');

            loginService.login( {'username':$scope.username , 'password':$scope.password} , function (data) {
                loginService.activateLogin(data);
            }, function (data) {
                $animate.addClass(element, 'shake');
                loginService.logoff(data);
            });
        };

        $scope.logout = function() {
            loginService.logoff(data);
            loginService.logout( {'username':$scope.username , 'password':$scope.password} , function (data) {});
        };

    }]);

})();