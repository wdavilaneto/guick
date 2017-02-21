(function () {
    'use strict';

    angular.module('sevApp').controller('DefaultDatepickerController', function ($scope) {

        $scope.today = function() {
            $scope.dt = new Date();
        };

        $scope.today();

        $scope.clear = function () {
            $scope.dt = null;
        };

        // Disable weekend selection
        $scope.disabled = function(date, mode) {
            return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
        };

        $scope.toggleMin = function() {
            $scope.minDate = $scope.minDate ? null : new Date();
        };
        $scope.toggleMin();

        $scope.open = function($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
        };

        $scope.dateOptions = {
            formatYear: 'dd/MM/yyyy',
            startingDay: 1
        };

        $scope.formats = ['dd/MM/yyyy','dd-MMMM-yyyy', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = $scope.formats[0];
    });

})();
