
(function () {

    'use strict';

    angular.module('sevApp').controller('CardsController', CardsController);


    function CardsController() {

        var vm = this;



        vm.cards = [
            {processo: '123456789', nome: 'RAIMUNDO FAGNER', procuradoria: '10� PROCURADORIA DA CASA CIVEL', status: 'PENDENTE'},
            {processo: '984756783', nome: 'RAIMUNDO FAGNER', procuradoria: '8� PROCURADORIA DA CASA CIVEL', status: 'CONCLUIDO'},
            {processo: '125489763', nome: 'RAIMUNDO FAGNER', procuradoria: '5� PROCURADORIA DA CASA CIVEL', status: 'CANCELADO'}
        ];

        vm.cssClassByStatus = function(status) {
            var cssClasses = {PENDENTE: 'bkg-amethyst', CONCLUIDO: 'bkg-river', CANCELADO: 'bkg-silver'};
            return cssClasses[status];
        }



    }

})();