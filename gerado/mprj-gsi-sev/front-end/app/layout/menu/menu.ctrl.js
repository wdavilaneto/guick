    (function() {
        'use strict';

        angular.module('sevApp').controller('MenuController', ['$rootScope', '$scope', '$http', '$interval', 'jsog',
            function($rootScope, $scope, $http, $interval, jsog ) {
                $scope.toothlet = function(){
                    $(".content-header.content-header-processo, .left-side, .right-side, .left-bar, nav.navbar.navbar-default.navbar-fixed-top, .bt-group-controller, .panel-page-pdf-content, .mh-mid, .content, footer, .totalContentWrapper").toggleClass("stash");
                    $(".content-document").toggleClass("stash-index");
                };
                $scope.openLogout = function(){
                    $(".content-logout, .content-logout-page, footer nav.navbar").toggleClass("stash");
                };
        }]);
    })();