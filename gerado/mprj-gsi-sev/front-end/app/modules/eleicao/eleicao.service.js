(function() {
	'use strict';

/**
 * @ngdoc service
 * @name sevApp.sevService
 * @description
 * # myService
 * Factory in the sevApp.
 */
angular.module('sevApp').factory('eleicaoService', eleicaoService);

    eleicaoService.$inject = ['$resource', 'gridService', 'ENV_CONFIG'];

    function eleicaoService($resource, gridService, ENV_CONFIG) {
        var service = $resource('/sev/api/eleicao/:id', {}, {
            'findAll': { method: 'GET', url: '/sev/api/eleicao', isArray: false},
            'search': { method: 'POST', url: '/sev/api/eleicao/search', isArray: false},
            'searchText': { method: 'POST', url: '/sev/api/eleicao/searchText', isArray: false},
            'findAllOverdue':{ method: 'GET', url: '/sev/api/eleicao/overdue', isArray: false},
            'get': { method: 'GET'},
            'save': { method: 'POST'},
            'remove': { method: 'DELETE'}
        });

        var entity = {};

        service.getCurrentEntity = function (){
            return entity;
        };

        service.setCurrentEntity = function (obj){
            entity = obj;
        };

        service.create = function (){
            var e = {
                //situacaoEleicao:{}
            };
            return e;
        };

        service.factorySearchSelect = function (){
            // Factory SearchSelect Object for all "search text properties"
            var searchSelects = {
                situacaoEleicao:{}
            };
            // utility method to copy selected object to entity ( reference
            searchSelects.toEntity = function (ref) {
                console.log(ref);

            };
            searchSelects.fromEntity = function (ref) {
                console.log(ref);
            };
            searchSelects.clear = function() {
                searchSelects.situacaoEleicao = {};
            };
            return searchSelects;
        };

        service.createGridOptions = function (entitySelected, isEditMode){
            var options = gridService.createGridOptions(isEditMode);
            //options.rowTemplate= '<div ng-dblclick="detalhar(row.entity)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>';
            //options.rowTemplate= '<div ng-dblclick="editeleicao(row.entity)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>';
            options.columnDefs = [];

            options.columnDefs.push({field: 'nome', displayName: 'Nome', sortable: false});
            //options.columnDefs.push({field: 'dataInicio', displayName: 'Data Início', sortable: false});
            //options.columnDefs.push({field: 'dataFim', displayName: 'Data Fim', sortable: false});
            options.columnDefs.push({field: 'qtdeMaxVotos', displayName: 'Qtde Max Votos', sortable: false});
            options.columnDefs.push({field: 'descricao', displayName: 'Descrição', sortable: false});
            options.columnDefs.push({field: 'descricaoOrientacao', displayName: 'Descrição Orientacao', sortable: false});
            //options.columnDefs.push({field: 'dataInicioApuracao', displayName: 'Data Início Apuracao', sortable: false});
            //options.columnDefs.push({field: 'dataFimApuracao', displayName: 'Data Fim Apuracao', sortable: false});
            //options.columnDefs.push({field: 'natureza', displayName: 'Natureza', sortable: false});
            //options.columnDefs.push({field: 'criterioDesempate', displayName: 'Criterio Desempate', sortable: false});
            //options.columnDefs.push({field: 'dataInicioPrev', displayName: 'Data Início Prev', sortable: false});
            //options.columnDefs.push({field: 'dataFimPrev', displayName: 'Data Fim Prev', sortable: false});
            //options.columnDefs.push({field: 'textoEmailConvite', displayName: 'Email Convite', sortable: false});
            //options.columnDefs.push({field: 'versao', displayName: 'Versao', sortable: false});
            //options.columnDefs.push({field: 'statusApuracao', displayName: 'Status Apuracao', sortable: false});
            options.columnDefs.push({field: 'situacaoEleicao.descricaoSituacao', displayName: 'Situacao Eleicao', sortable: false});
            options.columnDefs.push({name:'btn_detail', displayName: '', enableColumnResizing: false, enableSorting: false, enableFiltering: false, cellTemplate: ENV_CONFIG.baseURL + 'modules/eleicao/components/eleicao.grid.cell.detalhar.html', width : '35', resizable: false});
            if (isEditMode) {
                options.columnDefs.push({name:'btn_delete', displayName: '', enableColumnResizing: false, enableSorting: false, enableFiltering: false,  cellTemplate: ENV_CONFIG.baseURL + 'modules/eleicao/components/eleicao.grid.cell.delete.html' , width : '35', resizable: false});
            }
            return options;
        };
        return service;
    }

})();
