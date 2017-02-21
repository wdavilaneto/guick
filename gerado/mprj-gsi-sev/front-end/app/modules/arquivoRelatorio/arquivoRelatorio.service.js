(function() {
	'use strict';

/**
 * @ngdoc service
 * @name sevApp.sevService
 * @description
 * # myService
 * Factory in the sevApp.
 */
angular.module('sevApp').factory('arquivoRelatorioService', arquivoRelatorioService);

    arquivoRelatorioService.$inject = ['$resource', 'gridService', 'ENV_CONFIG'];

    function arquivoRelatorioService($resource, gridService, ENV_CONFIG) {
        var service = $resource('/sev/api/arquivoRelatorio/:id', {}, {
            'findAll': { method: 'GET', url: '/sev/api/arquivoRelatorio', isArray: false},
            'search': { method: 'POST', url: '/sev/api/arquivoRelatorio/search', isArray: false},
            'searchText': { method: 'POST', url: '/sev/api/arquivoRelatorio/searchText', isArray: false},
            'findAllOverdue':{ method: 'GET', url: '/sev/api/arquivoRelatorio/overdue', isArray: false},
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
                //eleicao:{},
                //evento:{},
                //tipoRelatorio:{}
            };
            return e;
        };

        service.factorySearchSelect = function (){
            // Factory SearchSelect Object for all "search text properties"
            var searchSelects = {
                eleicao:{},
                evento:{},
                tipoRelatorio:{}
            };
            // utility method to copy selected object to entity ( reference
            searchSelects.toEntity = function (ref) {
                console.log(ref);
                if (ref && searchSelects.eleicao.originalObject) {
                    ref.eleicao = searchSelects.eleicao.originalObject;
                } else {
                    ref.eleicao = null;
                }
                if (ref && searchSelects.evento.originalObject) {
                    ref.evento = searchSelects.evento.originalObject;
                } else {
                    ref.evento = null;
                }

            };
            searchSelects.fromEntity = function (ref) {
                console.log(ref);
                if (ref && ref.eleicao ) {
                    searchSelects.eleicao.originalObject = ref.eleicao;
                }
                if (ref && ref.evento ) {
                    searchSelects.evento.originalObject = ref.evento;
                }
            };
            searchSelects.clear = function() {
                searchSelects.eleicao = {};
                searchSelects.evento = {};
                searchSelects.tipoRelatorio = {};
            };
            return searchSelects;
        };

        service.createGridOptions = function (entitySelected, isEditMode){
            var options = gridService.createGridOptions(isEditMode);
            //options.rowTemplate= '<div ng-dblclick="detalhar(row.entity)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>';
            //options.rowTemplate= '<div ng-dblclick="editarquivoRelatorio(row.entity)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>';
            options.columnDefs = [];

            options.columnDefs.push({field: 'dataCriacao', displayName: 'Data Criacao', sortable: false});
            //options.columnDefs.push({field: 'arquivo', displayName: 'Arquivo', sortable: false} );
            options.columnDefs.push({field: 'eleicao.nome', displayName: 'Eleicao', sortable: false});
            options.columnDefs.push({field: 'evento.ocorrencia', displayName: 'Evento', sortable: false});
            //options.columnDefs.push({field: 'tipoRelatorio.descricao', displayName: 'Tipo Relatorio', sortable: false});
            options.columnDefs.push({name:'btn_detail', displayName: '', enableColumnResizing: false, enableSorting: false, enableFiltering: false, cellTemplate: ENV_CONFIG.baseURL + 'modules/arquivoRelatorio/components/arquivoRelatorio.grid.cell.detalhar.html', width : '35', resizable: false});
            if (isEditMode) {
                options.columnDefs.push({name:'btn_delete', displayName: '', enableColumnResizing: false, enableSorting: false, enableFiltering: false,  cellTemplate: ENV_CONFIG.baseURL + 'modules/arquivoRelatorio/components/arquivoRelatorio.grid.cell.delete.html' , width : '35', resizable: false});
            }
            return options;
        };
        return service;
    }

})();
