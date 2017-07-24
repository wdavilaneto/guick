
(function () {

    'use strict';

    angular.module('sevApp.main').controller('MainController', MainController);
    function MainController($scope, $location, loginService, ENV_CONFIG, toaster, $filter, $translate) {

        var self = this;

        // Public Metods




        $scope.$on('event:userLogout', function () {
            $scope.hasPermission = {};
            $location.path('/');
        });

        $scope.$on('event:userDetailsPrepared', function () {
            self.hasPermission = loginService.getCurrentUser().permissions;
        });

        this.envConfig = ENV_CONFIG;
    }

})();
