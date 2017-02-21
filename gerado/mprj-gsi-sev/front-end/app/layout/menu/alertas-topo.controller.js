(function() {
    'use strict';

    angular.module('sevApp.avisos').controller('AlertasTopoController', AlertasTopoController);

    // TODO: Implementar as chamadas de back-end para trazer dados reais. Esperar a refatoracao para o modelo de dominio para evitar retrabalho nas consultas.
    function AlertasTopoController($scope, $location, $filter, AvisoService) {

        var vm = this;

        /*init - public*/

        vm.gridIntimacoesNaoVisualizadas = {
            columnDefs: [
                {
                    field: 'numeroProcesso',
                    displayName: 'aviso.numeroProcesso',
                    headerCellFilter: 'translate',
                    cellFilter: 'mask:"                    width: 170
                },
                {
                    field: 'dataRecebimento',
                    displayName: 'aviso.dataRecebimento',
                    headerCellFilter: 'translate',
                    width: 120
                }
            ]
        };

        vm.gridIntimacoesComVencimentoProximo = {
            columnDefs: [
                {
                    field: 'numeroProcesso',
                    displayName: 'aviso.numeroProcesso',
                    headerCellFilter: 'translate',
                    cellFilter: 'mask:"                    width: 170
                },
                {
                    field: 'dataRecebimento',
                    displayName: 'aviso.dataRecebimento',
                    headerCellFilter: 'translate',
                    width: 120
                }
            ]
        };

        vm.gridIntimacoesVencidas = {
            columnDefs: [
                {
                    field: 'numeroProcesso',
                    displayName: 'aviso.numeroProcesso',
                    headerCellFilter: 'translate',
                    cellFilter: 'mask:"                    width: 170
                },
                {
                    field: 'dataRecebimento',
                    displayName: 'aviso.dataRecebimento',
                    headerCellFilter: 'translate',
                    width: 120
                }
            ]
        };

        /*end - public*/


        /*init - load on start*/

        init();

        /*end - load on start*/


        /*init - private*/

        function init() {
            atualizarListaIntimacoesNaoVisualizadas();
            atualizarListaIntimacoesComVencimentoProximo();
            atualizarListaIntimacoesVencidas();
        }

        /**
         * Carrega a lista de intimacoes nao visualizadas
         */
        function atualizarListaIntimacoesNaoVisualizadas() {
            var intimacoesNaoVisualizadas = [
                {
                    idAviso: '0',
                    numeroProcesso: '00671746720138190001',
                    dataRecebimento: '01/12/2015'
                },
                {
                    idAviso: '1',
                    numeroProcesso: '00067510220138190209',
                    dataRecebimento: '01/12/2015'
                }
            ];
            vm.gridIntimacoesNaoVisualizadas.data = intimacoesNaoVisualizadas;
        }

        /**
         * Carrega a lista de intimacoes com vencimento proximo
         */
        function atualizarListaIntimacoesComVencimentoProximo() {
            var intimacoesComVencimentoProximo = [
                {
                    idAviso: '0',
                    numeroProcesso: '00671746720138190001',
                    dataRecebimento: '10/12/2015'
                },
                {
                    idAviso: '1',
                    numeroProcesso: '00067510220138190209',
                    dataRecebimento: '09/12/2015'
                }
            ];
            vm.gridIntimacoesComVencimentoProximo.data = intimacoesComVencimentoProximo;
        }

        /**
         * Carrega a lista de intimacoes vencidas
         */
        function atualizarListaIntimacoesVencidas() {
            var intimacoesVencidas = [
                {
                    idAviso: '0',
                    numeroProcesso: '00671746720138190001',
                    dataRecebimento: '13/11/2015'
                },
                {
                    idAviso: '1',
                    numeroProcesso: '00067510220138190209',
                    dataRecebimento: '06/11/2015'
                }
            ];
            vm.gridIntimacoesVencidas.data = intimacoesVencidas;
        }

        /*end - private*/
    }
})();