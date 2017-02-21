(function() {
	'use strict';

/**
 * @ngdoc service
 * @name sevApp.sevService
 * @description
 * # myService
 * Factory in the sevApp.
 */
angular.module('sevApp').factory('resultadoApuracaoService', resultadoApuracaoService);

    resultadoApuracaoService.$inject = ['$resource', 'gridService', 'ENV_CONFIG'];

    function resultadoApuracaoService($resource, gridService, ENV_CONFIG) {
        var service = $resource('/sev/api/resultadoApuracao/:id', {}, {
            'findAll': { method: 'GET', url: '/sev/api/resultadoApuracao', isArray: false},
            'search': { method: 'POST', url: '/sev/api/resultadoApuracao/search', isArray: false},
            'searchText': { method: 'POST', url: '/sev/api/resultadoApuracao/searchText', isArray: false},
            'findAllOverdue':{ method: 'GET', url: '/sev/api/resultadoApuracao/overdue', isArray: false},
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
                //candidato:{}
            };
            return e;
        };

        service.factorySearchSelect = function (){
            // Factory SearchSelect Object for all "search text properties"
            var searchSelects = {
                candidato:{}
            };
            // utility method to copy selected object to entity ( reference
            searchSelects.toEntity = function (ref) {
                console.log(ref);
                if (ref && searchSelects.candidato.originalObject) {
                    ref.candidato = searchSelects.candidato.originalObject;
                } else {
                    ref.candidato = null;
                }

            };
            searchSelects.fromEntity = function (ref) {
                console.log(ref);
                if (ref && ref.candidato ) {
                    searchSelects.candidato.originalObject = ref.candidato;
                }
            };
            searchSelects.clear = function() {
                searchSelects.candidato = {};
            };
            return searchSelects;
        };

        service.createGridOptions = function (entitySelected, isEditMode){
            var options = gridService.createGridOptions(isEditMode);
            //options.rowTemplate= '<div ng-dblclick="detalhar(row.entity)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>';
            //options.rowTemplate= '<div ng-dblclick="editresultadoApuracao(row.entity)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>';
            options.columnDefs = [];

            options.columnDefs.push({field: 'qtVotos', displayName: 'Qt Votos', sortable: false});
            options.columnDefs.push({field: 'candidato.nome', displayName: 'Candidato', sortable: false});
            options.columnDefs.push({name:'btn_detail', displayName: '', enableColumnResizing: false, enableSorting: false, enableFiltering: false, cellTemplate: ENV_CONFIG.baseURL + 'modules/resultadoApuracao/components/resultadoApuracao.grid.cell.detalhar.html', width : '35', resizable: false});
            if (isEditMode) {
                options.columnDefs.push({name:'btn_delete', displayName: '', enableColumnResizing: false, enableSorting: false, enableFiltering: false,  cellTemplate: ENV_CONFIG.baseURL + 'modules/resultadoApuracao/components/resultadoApuracao.grid.cell.delete.html' , width : '35', resizable: false});
            }
            return options;
        };
        return service;
    }

})();
