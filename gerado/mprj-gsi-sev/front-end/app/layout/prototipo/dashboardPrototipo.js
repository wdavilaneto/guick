(function () {

    'use strict';

    angular.module('sevApp').controller('DashboardPrototipoController', DashboardPrototipoController);


    function DashboardPrototipoController() {
        var vm = this;

  vm.labels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
  vm.series = ['Series A', 'Series B'];
  vm.color = ['#ededed', '#fafafa'];

  vm.data = [
    [65, 59, 80, 81, 56, 55, 40],
    [28, 48, 40, 19, 86, 27, 90]
  ];

}
 vm.labelsDoughnut = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
  vm.data = [300, 500, 100];
})();