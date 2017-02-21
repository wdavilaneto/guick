(function() {
	'use strict';

/**
 * @ngdoc service
 * @name sevApp.sevService
 * @description
 * # myService
 * Factory in the sevApp.
 */
angular.module('sevApp').factory('parametroService', parametroService);

    parametroService.$inject = ['$resource', 'gridService', 'ENV_CONFIG'];

    function parametroService($resource, gridService, ENV_CONFIG) {
        var service = $resource('/sev/api/parametro/:id', {}, {
            'findAll': { method: 'GET', url: '/sev/api/parametro', isArray: false},
            'search': { method: 'POST', url: '/sev/api/parametro/search', isArray: false},
            'searchText': { method: 'POST', url: '/sev/api/parametro/searchText', isArray: false},
            'findAllOverdue':{ method: 'GET', url: '/sev/api/parametro/overdue', isArray: false},
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
                //tipoParametro:{}
            };
            return e;
        };

        service.factorySearchSelect = function (){
            // Factory SearchSelect Object for all "search text properties"
            var searchSelects = {
                tipoParametro:{}
            };
            // utility method to copy selected object to entity ( reference
            searchSelects.toEntity = function (ref) {
                console.log(ref);

            };
            searchSelects.fromEntity = function (ref) {
                console.log(ref);
            };
            searchSelects.clear = function() {
                searchSelects.tipoParametro = {};
            };
            return searchSelects;
        };

        service.createGridOptions = function (entitySelected, isEditMode){
            var options = gridService.createGridOptions(isEditMode);
            //options.rowTemplate= '<div ng-dblclick="detalhar(row.entity)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>';
            //options.rowTemplate= '<div ng-dblclick="editparametro(row.entity)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>';
            options.columnDefs = [];

            options.columnDefs.push({field: 'descricao', displayName: 'Descrição', sortable: false});
            options.columnDefs.push({field: 'tipoParametro.descricao', displayName: 'Tipo Parametro', sortable: false});
            options.columnDefs.push({name:'btn_detail', displayName: '', enableColumnResizing: false, enableSorting: false, enableFiltering: false, cellTemplate: ENV_CONFIG.baseURL + 'modules/parametro/components/parametro.grid.cell.detalhar.html', width : '35', resizable: false});
            if (isEditMode) {
                options.columnDefs.push({name:'btn_delete', displayName: '', enableColumnResizing: false, enableSorting: false, enableFiltering: false,  cellTemplate: ENV_CONFIG.baseURL + 'modules/parametro/components/parametro.grid.cell.delete.html' , width : '35', resizable: false});
            }
            return options;
        };
        return service;
    }

})();
