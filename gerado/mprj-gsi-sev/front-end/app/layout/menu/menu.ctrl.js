(function() {
    'use strict';

    angular.module('sevApp').controller('MenuController', ['$rootScope', '$scope', '$http', '$interval', 'jsog',
        function($rootScope, $scope, $http, $interval, jsog ) {
            $scope.toothlet = function(){
                $(".content-header.content-header-processo, .left-side, .right-side, .left-bar, nav.navbar.navbar-default.navbar-fixed-top, .bt-group-controller, .panel-page-pdf-content, .mh-mid, .content, footer, .totalContentWrapper").toggleClass("stash");
                $(".content-document").toggleClass("stash-index");
            };
            $scope.openLogout = function(){
                $(".content-logout, .content-logout-page").toggleClass("stash");
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
            };
            $scope.openNotificationDetail = function(){
                $(".content-notification-detail").toggleClass("stash");
            };
    }]);
})();