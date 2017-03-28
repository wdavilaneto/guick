(function () {

    'use strict';

    angular.module('sevApp').controller('BotoesController', BotoesController);


    function BotoesController() {
        var vm = this;
        vm.singleModel = 1;

        vm.radioModel = 'Middle';

        vm.checkModel = {
        left: false,
        middle: true,
        right: false
        };
    }
})();