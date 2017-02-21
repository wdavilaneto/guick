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
    .directive('fileread', [function () {
        return {
            scope: {
                fileread: '=',
                filetype: '='
            },
            link: function (scope, element, attributes) {
                element.bind('change', function (changeEvent) {
                    var reader = new FileReader();
                    reader.onload = function (loadEvent) {
                        scope.$apply(function () {
                            var info = loadEvent.target.result;
                            //console.log(info);
                            // now we get ony the base64 info
                            scope.fileread = info.split(',');//.shift().join();
                            scope.filetype = scope.fileread[0]; // get filetype before remove ot from base64
                            scope.fileread.shift();
                            scope.fileread = scope.fileread.join();
                        });
                    }
                    reader.readAsDataURL(changeEvent.target.files[0]);
                });
            }
        }
    }])
;
