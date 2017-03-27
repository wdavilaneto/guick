(function () {

    'use strict';

    angular.module('sevApp').controller('PageController', PageController);


    function PageController() {
        var vm = this;
 vm.totalItems = 64;
  vm.currentPage = 4;
  vm.setPage = function (pageNo) {
    vm.currentPage = pageNo;
  };
  vm.maxSize = 5;
  vm.maxSize = 5;
  vm.bigTotalItems = 175;
  vm.bigCurrentPage = 1;
}

})();