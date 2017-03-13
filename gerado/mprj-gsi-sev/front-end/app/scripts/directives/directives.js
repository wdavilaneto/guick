'use strict';

angular.module('sevApp')
    .directive('authenticatedApplication', [ '$rootScope', 'loginService', function($rootScope, loginService) {
        return {
            restrict: 'C', link: function(scope, elem, attrs) {
                //once Angular is started, remove class:

                var login = elem.find('#login-holder');
                var main = elem.find('#content');
                var menu = elem.find('#menu');
                var userArea = elem.find('#user-area');

                if ( loginService.getCurrentUser() === null || loginService.getCurrentUser().username === null || loginService.getCurrentUser().username === '') {
                    main.hide();
                    menu.hide();
                    userArea.hide();
                    login.hide();
                    loginService.authenticate( {} , function (data) {
                        main.show();
                        menu.show();
                        userArea.show();
                    }, function (data) {
                        login.show();
                    });
                } else {
                    login.hide();
                }

                elem.removeClass('waiting-for-angular');

                scope.$on('event:auth-loginRequired', function() {
                    login.slideDown('slow', function() {
                        main.hide();
                        menu.hide();
                        userArea.hide();
                    });
                });
                scope.$on('event:auth-loginConfirmed', function() {
                    main.show();
                    menu.show();
                    userArea.show();
                    login.slideUp();
                });
            }
        }
    }])
;
