
(function () {

    'use strict';

    angular.module('sevApp').controller('ModalPrototipoController', ModalPrototipoController);


    function ModalPrototipoController() {
        var vm = this;
        vm.openModalSimples = function(){
            $(".modal-simples, .bkg-modal").toggleClass("stash");
        };
        vm.openModalRotate = function(){
            $(".modal-rotate, .bkg-modal").toggleClass("stash");
        };
        vm.openModalBottom = function(){
            $(".modal-bottom, .bkg-modal").toggleClass("stash");
        };
        vm.openModalRight = function(){
            $(".modal-right, .bkg-modal").toggleClass("stash");
        };
        vm.openModalTop = function(){
            $(".modal-top, .bkg-modal").toggleClass("stash");
        };
        vm.btClose = function(){
            $(".modal-simples, .modal-rotate, .modal-bottom, .modal-right, .modal-top, .bkg-modal").removeClass("stash");
        };
    }

})();