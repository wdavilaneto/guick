(function() {
    'use strict';

    angular.module('sevApp').controller('MenuController', ['$rootScope', '$scope', '$http', '$interval', 'jsog',
        function($rootScope, $scope, $http, $interval, jsog ) {
            $scope.toothlet = function(){
                $(".content-header.content-header-processo, .left-side, .right-side, .left-bar, nav.navbar.navbar-default.navbar-fixed-top, .bt-group-controller, .panel-page-pdf-content, .mh-mid, .content, footer, .totalContentWrapper").toggleClass("stash");
                $(".content-document").toggleClass("stash-index");
                $(".content-notification-detail, .content-notification, .content-notification-page").removeClass("stash");
            };
            $scope.openLogout = function(){
                $(".content-logout, .content-logout-page").toggleClass("stash");
                $(".change-profile").removeClass("stash");
            };
            $scope.changeColection = function(){
                $(".card-collection, .table-collection, .bt-card-list").toggleClass("stash");
            };
            $scope.openContentExpand = function(){
                $(".content-expand, .bt-expand .mdi.mdi-chevron-down").toggleClass("stash");
            };
            $scope.openCloseFilter = function(){
                $(".search-filter-control, .bt-oc-filter .mdi.mdi-chevron-down").toggleClass("stash");
            };
            $scope.openChangeProfile = function(){
                $(".change-profile").toggleClass("stash");
            };
            $scope.openNotification = function(){
                $(".content-notification").toggleClass("stash");
                $(".content-notification-detail").removeClass("stash");
                $(".content-notification-page").removeClass("stash") ;
            };
            $scope.openNotificationDetail = function(){
                $(".content-notification-detail").toggleClass("stash");
                $(".content-notification-page").removeClass("stash");
                $(".content-notification").removeClass("stash");
            };
            $scope.openNotificationPage = function(){
                $(".content-notification-page").toggleClass("stash");
                $(".content-notification-detail").removeClass("stash");
                $(".content-notification").removeClass("stash");
            };
            $scope.openHeaderSubmenu = function(){
                $("nav.navbar-mprj ul li ul.submenu, nav.navbar-mprj ul li a.brand").toggleClass("stash");
            };
    }]);
})();