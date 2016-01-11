'use strict';
angular.module('mgpeApp').controller('MenuController', ['$rootScope', '$scope', '$http', '$interval', 'jsog',
    function($rootScope, $scope, $http, $interval, jsog ) {

    var vm = this;

    vm.totalDocumentos = 0;
    vm.totalAvisosPendentes = 0;


  //  $interval( $scope.countDocumentos, 50000 );
  //  $interval( $scope.countAvisosPendentes(), 50000 );

}]);

angular.module('mgpeApp').controller('MenuControllerMobile',  ['$scope', function($scope) {
    $scope.boxOpen = true;
    $scope.toggle = function() {
        $scope.boxOpen = !$scope.boxOpen;
    };
    $scope.toothlet = function(){
        $(".left-side, .right-side, .left-bar, nav.navbar.navbar-default.navbar-fixed-top").toggleClass("stash");
      };
}]);

angular.module('mgpeApp').controller('MenuControllerSearchFilter',  ['$scope', function($scope) {
    $scope.searchOpen = true;
    $scope.toggle = function() {
        $scope.searchOpen = !$scope.searchOpen;
    };

}]);
