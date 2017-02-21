(function() {
	'use strict';

/**
 * @ngdoc service
 * @name sevApp.sevService
 * @description
 * # myService
 * Factory in the sevApp.
 */
angular.module('sevApp').factory('integranteComissaoService', integranteComissaoService);

    integranteComissaoService.$inject = ['$resource', 'gridService', 'ENV_CONFIG'];

    function integranteComissaoService($resource, gridService, ENV_CONFIG) {
        var service = $resource('/sev/api/integranteComissao/:id', {}, {
            'findAll': { method: 'GET', url: '/sev/api/integranteComissao', isArray: false},
            'search': { method: 'POST', url: '/sev/api/integranteComissao/search', isArray: false},
            'searchText': { method: 'POST', url: '/sev/api/integranteComissao/searchText', isArray: false},
            'findAllOverdue':{ method: 'GET', url: '/sev/api/integranteComissao/overdue', isArray: false},
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
                //tipoIntegranteComissao:{}
            };
            return e;
        };

        service.factorySearchSelect = function (){
            // Factory SearchSelect Object for all "search text properties"
            var searchSelects = {
                eleicao:{},
                tipoIntegranteComissao:{}
            };
            // utility method to copy selected object to entity ( reference
            searchSelects.toEntity = function (ref) {
                console.log(ref);
                if (ref && searchSelects.eleicao.originalObject) {
                    ref.eleicao = searchSelects.eleicao.originalObject;
                } else {
                    ref.eleicao = null;
                }

            };
            searchSelects.fromEntity = function (ref) {
                console.log(ref);
                if (ref && ref.eleicao ) {
                    searchSelects.eleicao.originalObject = ref.eleicao;
                }
            };
            searchSelects.clear = function() {
                searchSelects.eleicao = {};
                searchSelects.tipoIntegranteComissao = {};
            };
            return searchSelects;
        };

        service.createGridOptions = function (entitySelected, isEditMode){
            var options = gridService.createGridOptions(isEditMode);
            //options.rowTemplate= '<div ng-dblclick="detalhar(row.entity)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>';
            //options.rowTemplate= '<div ng-dblclick="editintegranteComissao(row.entity)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>';
            options.columnDefs = [];

            options.columnDefs.push({field: 'cdmatricula', displayName: 'Códigomatricula', sortable: false});
            options.columnDefs.push({field: 'dataValidacao', displayName: 'Data Validacao', sortable: false});
            options.columnDefs.push({field: 'nome', displayName: 'Nome', sortable: false});
            options.columnDefs.push({field: 'email', displayName: 'Email', sortable: false});
            options.columnDefs.push({field: 'login', displayName: 'Login', sortable: false});
            options.columnDefs.push({field: 'eleicao.nome', displayName: 'Eleicao', sortable: false});
            options.columnDefs.push({field: 'tipoIntegranteComissao.nomeTipoIntegrante', displayName: 'Tipo Integrante Comissao', sortable: false});
            options.columnDefs.push({name:'btn_detail', displayName: '', enableColumnResizing: false, enableSorting: false, enableFiltering: false, cellTemplate: ENV_CONFIG.baseURL + 'modules/integranteComissao/components/integranteComissao.grid.cell.detalhar.html', width : '35', resizable: false});
            if (isEditMode) {
                options.columnDefs.push({name:'btn_delete', displayName: '', enableColumnResizing: false, enableSorting: false, enableFiltering: false,  cellTemplate: ENV_CONFIG.baseURL + 'modules/integranteComissao/components/integranteComissao.grid.cell.delete.html' , width : '35', resizable: false});
            }
            return options;
        };
        return service;
    }

})();
