(function() {
	'use strict';

/**
 * @ngdoc service
 * @name sevApp.sevService
 * @description
 * # myService
 * Factory in the sevApp.
 */
angular.module('sevApp').factory('candidatoService', candidatoService);

    candidatoService.$inject = ['$resource', 'gridService', 'ENV_CONFIG'];

    function candidatoService($resource, gridService, ENV_CONFIG) {
        var service = $resource('/sev/api/candidato/:id', {}, {
            'findAll': { method: 'GET', url: '/sev/api/candidato', isArray: false},
            'search': { method: 'POST', url: '/sev/api/candidato/search', isArray: false},
            'searchText': { method: 'POST', url: '/sev/api/candidato/searchText', isArray: false},
            'findAllOverdue':{ method: 'GET', url: '/sev/api/candidato/overdue', isArray: false},
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
                //candidatoPadrao:{},
                //eleicao:{}
            };
            return e;
        };

        service.factorySearchSelect = function (){
            // Factory SearchSelect Object for all "search text properties"
            var searchSelects = {
                candidatoPadrao:{},
                eleicao:{}
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
                searchSelects.candidatoPadrao = {};
                searchSelects.eleicao = {};
            };
            return searchSelects;
        };

        service.createGridOptions = function (entitySelected, isEditMode){
            var options = gridService.createGridOptions(isEditMode);
            //options.rowTemplate= '<div ng-dblclick="detalhar(row.entity)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>';
            //options.rowTemplate= '<div ng-dblclick="editcandidato(row.entity)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>';
            options.columnDefs = [];

            //options.columnDefs.push({field: 'cdmatricula', displayName: 'Códigomatricula', sortable: false});
            options.columnDefs.push({field: 'nome', displayName: 'Nome', sortable: false});
            //options.columnDefs.push({field: 'foto', displayName: 'Foto', sortable: false} );
            //options.columnDefs.push({field: 'dataNascimento', displayName: 'Data Nascimento', sortable: false});
            options.columnDefs.push({field: 'email', displayName: 'Email', sortable: false});
            options.columnDefs.push({field: 'nomeCompleto', displayName: 'Nome Completo', sortable: false});
            options.columnDefs.push({field: 'criterioDesempate', displayName: 'Criterio Desempate', sortable: false});
            options.columnDefs.push({field: 'login', displayName: 'Login', sortable: false});
            options.columnDefs.push({field: 'candidatoPadrao.descricao', displayName: 'Candidato Padrao', sortable: false});
            options.columnDefs.push({field: 'eleicao.nome', displayName: 'Eleicao', sortable: false});
            options.columnDefs.push({name:'btn_detail', displayName: '', enableColumnResizing: false, enableSorting: false, enableFiltering: false, cellTemplate: ENV_CONFIG.baseURL + 'modules/candidato/components/candidato.grid.cell.detalhar.html', width : '35', resizable: false});
            if (isEditMode) {
                options.columnDefs.push({name:'btn_delete', displayName: '', enableColumnResizing: false, enableSorting: false, enableFiltering: false,  cellTemplate: ENV_CONFIG.baseURL + 'modules/candidato/components/candidato.grid.cell.delete.html' , width : '35', resizable: false});
            }
            return options;
        };
        return service;
    }

})();
