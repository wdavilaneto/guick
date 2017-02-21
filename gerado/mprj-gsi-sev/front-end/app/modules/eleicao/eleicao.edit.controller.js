(function() {

	'use strict';

    /**
     * @ngdoc function
     * @name sevApp.controller:eleicaoController
     * @description
     * # eleicaoController
     * Controller of the sevApp
     */
    angular.module('sevApp')
        .controller('eleicaoEditController', eleicaoEditController);

        eleicaoEditController.$inject = ['$scope', '$state', '$stateParams', '$modal', '$location',
            'jsog', 'eleicaoService', 'arquivoRelatorioService', 'candidatoService', 'situacaoEleicaoService', 'eleicaoParametroService', 'eleitorService', 'eventoService', 'integranteComissaoService', 'tipoFiltroPessoaService', 'urnaService'];

        function eleicaoEditController($scope, $state, $stateParams , $modal, $location, jsog, eleicaoService , arquivoRelatorioService, candidatoService, situacaoEleicaoService, eleicaoParametroService, eleitorService, eventoService, integranteComissaoService, tipoFiltroPessoaService, urnaService ) {

            var vm = this;

            // ***************************
            // Model Initializations
            // ***************************
            vm.master = 'eleicao';
            vm.maxSelectBoxSize = 300;
            vm.original = {};
            vm.entity = eleicaoService.create();
            vm.searchSelects = eleicaoService.factorySearchSelect(); // Text Search Select utility Object
            vm.searchSelects.fromEntity(vm.entity);

            // ***************************************eleicao
            // Controller Methods definitions
            // ***************************************
            vm.get = function( id ) {
                if (!id) {
                    id = $stateParams.id;                           // get id parameter from ui-router
                }
                console.log('retrieving rename.id = ' + id  );
                eleicaoService.get( {'id':id} , function (data) {
                    vm.entity = data;                           // set managed entity from service
                    vm.original = angular.copy(vm.entity);                // backup original values;
                    vm.searchSelects.fromEntity($scope.entity); // update search selects values;
                }, function (error){
                    console.log(error);                             // TODO:
                });
            };

            vm.clearForm = function () {
                vm.entity = angular.copy(vm.original);
            };

            // ************************************
            // watches
            // ************************************
            vm.changeTab = function (tab) {
                $state.go(tab);
                vm.tabSelected = tab;
            }

            // ****************************************
            // Controller init (on load) service calls
            // ****************************************
            vm.tabs = [];
            vm.tabs.push({heading: "Principal", route: "eleicaoEdit.main", icon: "mdi-home", visible: "true"});
             vm.tabs.push({heading: "Arquivo Relatorio ", route: "eleicaoEdit.arquivoRelatorioCollection", icon: "mdi-label", visible: $stateParams.id});
             vm.tabs.push({heading: "Candidato ", route: "eleicaoEdit.candidatoCollection", icon: "mdi-label", visible: $stateParams.id});
             vm.tabs.push({heading: "Eleicao Parametro ", route: "eleicaoEdit.eleicaoParametroCollection", icon: "mdi-label", visible: $stateParams.id});
             vm.tabs.push({heading: "Eleitor ", route: "eleicaoEdit.eleitorCollection", icon: "mdi-label", visible: $stateParams.id});
             vm.tabs.push({heading: "Evento ", route: "eleicaoEdit.eventoCollection", icon: "mdi-label", visible: $stateParams.id});
             vm.tabs.push({heading: "Integrante Comissao ", route: "eleicaoEdit.integranteComissaoCollection", icon: "mdi-label", visible: $stateParams.id});
             vm.tabs.push({heading: "Tipo Filtro Pessoa ", route: "eleicaoEdit.tipoFiltroPessoaCollection", icon: "mdi-label", visible: $stateParams.id});
             vm.tabs.push({heading: "Urna ", route: "eleicaoEdit.urnaCollection", icon: "mdi-label", visible: $stateParams.id});

            vm.tabSelected = 'eleicaoEdit.main';

            // ******************************************************************************************
            // Master Details: Operations for Search and  editing of OneToMany/ManyToMany
            // ******************************************************************************************
             $scope.selectedArquivoRelatorio = null;

            $scope.getArquivoRelatorio = function ( arquivoRelatorioId ) {
                arquivoRelatorioService.get( {'id':arquivoRelatorioId} , function (data) {
                    $scope.selectedArquivoRelatorio = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/eleicao/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'ArquivoRelatorio' && $stateParams.arquivoRelatorioId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.arquivoRelatorioId) {
                    $scope.getArquivoRelatorio( $stateParams.arquivoRelatorioId );
                }
            }

            $scope.findArquivoRelatorio = function() {
                console.log('Realizando busca de 1 x N( arquivoRelatorio.eleicao.id):' + $stateParams.id);

                $scope.gridOptions = arquivoRelatorioService.createGridOptions(true);
                $scope.arquivoRelatorioPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                arquivoRelatorioService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchArquivoRelatorio = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( arquivoRelatorio.eleicao.id):' + $scope.entity.id);
                $scope.resultPage = null;
                arquivoRelatorioService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeArquivoRelatorio = function (obj){
                console.log('Deleting arquivoRelatorio with id:' + obj.id);
                arquivoRelatorioService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveArquivoRelatorio = function (){
                console.log('Requesting save on ${arquivoRelatorio}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                arquivoRelatorioService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = arquivoRelatorioService.create();
                    if (!$scope.editMode) {
                        $scope.searchArquivoRelatorio();
                    } else {
                        $location.path('/eleicao/'+ $stateParams.id + '/ArquivoRelatorio');
                    }
                });
            };
            $scope.editArquivoRelatorio = function (obj) {
                $scope.selectedArquivoRelatorio = obj
                $location.path('/eleicao/'+ $scope.entity.id +'/ArquivoRelatorio/' + obj.id );
            };
            $scope.createArquivoRelatorio = function (obj) {
                $scope.selectedArquivoRelatorio = arquivoRelatorioService.create();
                $scope.selectedArquivoRelatorio.eleicao = $scope.entity;
                $location.path('/eleicao/'+ $scope.entity.id +'/ArquivoRelatorio/');
            };

            $scope.arquivoRelatorioPaginationChanged = function(newPage) {
                $scope.arquivoRelatorioPagination.currentPage = newPage;
                $scope.findArquivoRelatorio();
            };

            $scope.isArquivoRelatorioActive = ($stateParams.child === 'ArquivoRelatorio' && $stateParams.arquivoRelatorioId) ;
             $scope.selectedCandidato = null;

            $scope.getCandidato = function ( candidatoId ) {
                candidatoService.get( {'id':candidatoId} , function (data) {
                    $scope.selectedCandidato = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/eleicao/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'Candidato' && $stateParams.candidatoId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.candidatoId) {
                    $scope.getCandidato( $stateParams.candidatoId );
                }
            }

            $scope.findCandidato = function() {
                console.log('Realizando busca de 1 x N( candidato.eleicao.id):' + $stateParams.id);

                $scope.gridOptions = candidatoService.createGridOptions(true);
                $scope.candidatoPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                candidatoService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchCandidato = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( candidato.eleicao.id):' + $scope.entity.id);
                $scope.resultPage = null;
                candidatoService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeCandidato = function (obj){
                console.log('Deleting candidato with id:' + obj.id);
                candidatoService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveCandidato = function (){
                console.log('Requesting save on ${candidato}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                candidatoService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = candidatoService.create();
                    if (!$scope.editMode) {
                        $scope.searchCandidato();
                    } else {
                        $location.path('/eleicao/'+ $stateParams.id + '/Candidato');
                    }
                });
            };
            $scope.editCandidato = function (obj) {
                $scope.selectedCandidato = obj
                $location.path('/eleicao/'+ $scope.entity.id +'/Candidato/' + obj.id );
            };
            $scope.createCandidato = function (obj) {
                $scope.selectedCandidato = candidatoService.create();
                $scope.selectedCandidato.eleicao = $scope.entity;
                $location.path('/eleicao/'+ $scope.entity.id +'/Candidato/');
            };

            $scope.candidatoPaginationChanged = function(newPage) {
                $scope.candidatoPagination.currentPage = newPage;
                $scope.findCandidato();
            };

            $scope.isCandidatoActive = ($stateParams.child === 'Candidato' && $stateParams.candidatoId) ;
             $scope.selectedEleicaoParametro = null;

            $scope.getEleicaoParametro = function ( eleicaoParametroId ) {
                eleicaoParametroService.get( {'id':eleicaoParametroId} , function (data) {
                    $scope.selectedEleicaoParametro = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/eleicao/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'EleicaoParametro' && $stateParams.eleicaoParametroId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.eleicaoParametroId) {
                    $scope.getEleicaoParametro( $stateParams.eleicaoParametroId );
                }
            }

            $scope.findEleicaoParametro = function() {
                console.log('Realizando busca de 1 x N( eleicaoParametro.eleicao.id):' + $stateParams.id);

                $scope.gridOptions = eleicaoParametroService.createGridOptions(true);
                $scope.eleicaoParametroPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                eleicaoParametroService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchEleicaoParametro = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( eleicaoParametro.eleicao.id):' + $scope.entity.id);
                $scope.resultPage = null;
                eleicaoParametroService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeEleicaoParametro = function (obj){
                console.log('Deleting eleicaoParametro with id:' + obj.id);
                eleicaoParametroService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveEleicaoParametro = function (){
                console.log('Requesting save on ${eleicaoParametro}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                eleicaoParametroService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = eleicaoParametroService.create();
                    if (!$scope.editMode) {
                        $scope.searchEleicaoParametro();
                    } else {
                        $location.path('/eleicao/'+ $stateParams.id + '/EleicaoParametro');
                    }
                });
            };
            $scope.editEleicaoParametro = function (obj) {
                $scope.selectedEleicaoParametro = obj
                $location.path('/eleicao/'+ $scope.entity.id +'/EleicaoParametro/' + obj.id );
            };
            $scope.createEleicaoParametro = function (obj) {
                $scope.selectedEleicaoParametro = eleicaoParametroService.create();
                $scope.selectedEleicaoParametro.eleicao = $scope.entity;
                $location.path('/eleicao/'+ $scope.entity.id +'/EleicaoParametro/');
            };

            $scope.eleicaoParametroPaginationChanged = function(newPage) {
                $scope.eleicaoParametroPagination.currentPage = newPage;
                $scope.findEleicaoParametro();
            };

            $scope.isEleicaoParametroActive = ($stateParams.child === 'EleicaoParametro' && $stateParams.eleicaoParametroId) ;
             $scope.selectedEleitor = null;

            $scope.getEleitor = function ( eleitorId ) {
                eleitorService.get( {'id':eleitorId} , function (data) {
                    $scope.selectedEleitor = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/eleicao/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'Eleitor' && $stateParams.eleitorId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.eleitorId) {
                    $scope.getEleitor( $stateParams.eleitorId );
                }
            }

            $scope.findEleitor = function() {
                console.log('Realizando busca de 1 x N( eleitor.eleicao.id):' + $stateParams.id);

                $scope.gridOptions = eleitorService.createGridOptions(true);
                $scope.eleitorPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                eleitorService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchEleitor = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( eleitor.eleicao.id):' + $scope.entity.id);
                $scope.resultPage = null;
                eleitorService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeEleitor = function (obj){
                console.log('Deleting eleitor with id:' + obj.id);
                eleitorService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveEleitor = function (){
                console.log('Requesting save on ${eleitor}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                eleitorService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = eleitorService.create();
                    if (!$scope.editMode) {
                        $scope.searchEleitor();
                    } else {
                        $location.path('/eleicao/'+ $stateParams.id + '/Eleitor');
                    }
                });
            };
            $scope.editEleitor = function (obj) {
                $scope.selectedEleitor = obj
                $location.path('/eleicao/'+ $scope.entity.id +'/Eleitor/' + obj.id );
            };
            $scope.createEleitor = function (obj) {
                $scope.selectedEleitor = eleitorService.create();
                $scope.selectedEleitor.eleicao = $scope.entity;
                $location.path('/eleicao/'+ $scope.entity.id +'/Eleitor/');
            };

            $scope.eleitorPaginationChanged = function(newPage) {
                $scope.eleitorPagination.currentPage = newPage;
                $scope.findEleitor();
            };

            $scope.isEleitorActive = ($stateParams.child === 'Eleitor' && $stateParams.eleitorId) ;
             $scope.selectedEvento = null;

            $scope.getEvento = function ( eventoId ) {
                eventoService.get( {'id':eventoId} , function (data) {
                    $scope.selectedEvento = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/eleicao/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'Evento' && $stateParams.eventoId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.eventoId) {
                    $scope.getEvento( $stateParams.eventoId );
                }
            }

            $scope.findEvento = function() {
                console.log('Realizando busca de 1 x N( evento.eleicao.id):' + $stateParams.id);

                $scope.gridOptions = eventoService.createGridOptions(true);
                $scope.eventoPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                eventoService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchEvento = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( evento.eleicao.id):' + $scope.entity.id);
                $scope.resultPage = null;
                eventoService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeEvento = function (obj){
                console.log('Deleting evento with id:' + obj.id);
                eventoService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveEvento = function (){
                console.log('Requesting save on ${evento}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                eventoService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = eventoService.create();
                    if (!$scope.editMode) {
                        $scope.searchEvento();
                    } else {
                        $location.path('/eleicao/'+ $stateParams.id + '/Evento');
                    }
                });
            };
            $scope.editEvento = function (obj) {
                $scope.selectedEvento = obj
                $location.path('/eleicao/'+ $scope.entity.id +'/Evento/' + obj.id );
            };
            $scope.createEvento = function (obj) {
                $scope.selectedEvento = eventoService.create();
                $scope.selectedEvento.eleicao = $scope.entity;
                $location.path('/eleicao/'+ $scope.entity.id +'/Evento/');
            };

            $scope.eventoPaginationChanged = function(newPage) {
                $scope.eventoPagination.currentPage = newPage;
                $scope.findEvento();
            };

            $scope.isEventoActive = ($stateParams.child === 'Evento' && $stateParams.eventoId) ;
             $scope.selectedIntegranteComissao = null;

            $scope.getIntegranteComissao = function ( integranteComissaoId ) {
                integranteComissaoService.get( {'id':integranteComissaoId} , function (data) {
                    $scope.selectedIntegranteComissao = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/eleicao/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'IntegranteComissao' && $stateParams.integranteComissaoId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.integranteComissaoId) {
                    $scope.getIntegranteComissao( $stateParams.integranteComissaoId );
                }
            }

            $scope.findIntegranteComissao = function() {
                console.log('Realizando busca de 1 x N( integranteComissao.eleicao.id):' + $stateParams.id);

                $scope.gridOptions = integranteComissaoService.createGridOptions(true);
                $scope.integranteComissaoPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                integranteComissaoService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchIntegranteComissao = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( integranteComissao.eleicao.id):' + $scope.entity.id);
                $scope.resultPage = null;
                integranteComissaoService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeIntegranteComissao = function (obj){
                console.log('Deleting integranteComissao with id:' + obj.id);
                integranteComissaoService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveIntegranteComissao = function (){
                console.log('Requesting save on ${integranteComissao}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                integranteComissaoService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = integranteComissaoService.create();
                    if (!$scope.editMode) {
                        $scope.searchIntegranteComissao();
                    } else {
                        $location.path('/eleicao/'+ $stateParams.id + '/IntegranteComissao');
                    }
                });
            };
            $scope.editIntegranteComissao = function (obj) {
                $scope.selectedIntegranteComissao = obj
                $location.path('/eleicao/'+ $scope.entity.id +'/IntegranteComissao/' + obj.id );
            };
            $scope.createIntegranteComissao = function (obj) {
                $scope.selectedIntegranteComissao = integranteComissaoService.create();
                $scope.selectedIntegranteComissao.eleicao = $scope.entity;
                $location.path('/eleicao/'+ $scope.entity.id +'/IntegranteComissao/');
            };

            $scope.integranteComissaoPaginationChanged = function(newPage) {
                $scope.integranteComissaoPagination.currentPage = newPage;
                $scope.findIntegranteComissao();
            };

            $scope.isIntegranteComissaoActive = ($stateParams.child === 'IntegranteComissao' && $stateParams.integranteComissaoId) ;
             $scope.selectedTipoFiltroPessoa = null;

            $scope.getTipoFiltroPessoa = function ( tipoFiltroPessoaId ) {
                tipoFiltroPessoaService.get( {'id':tipoFiltroPessoaId} , function (data) {
                    $scope.selectedTipoFiltroPessoa = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/eleicao/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'TipoFiltroPessoa' && $stateParams.tipoFiltroPessoaId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.tipoFiltroPessoaId) {
                    $scope.getTipoFiltroPessoa( $stateParams.tipoFiltroPessoaId );
                }
            }

            $scope.findTipoFiltroPessoa = function() {
                console.log('Realizando busca de 1 x N( tipoFiltroPessoa.eleicao.id):' + $stateParams.id);

                $scope.gridOptions = tipoFiltroPessoaService.createGridOptions(true);
                $scope.tipoFiltroPessoaPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                tipoFiltroPessoaService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchTipoFiltroPessoa = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( tipoFiltroPessoa.eleicao.id):' + $scope.entity.id);
                $scope.resultPage = null;
                tipoFiltroPessoaService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeTipoFiltroPessoa = function (obj){
                console.log('Deleting tipoFiltroPessoa with id:' + obj.id);
                tipoFiltroPessoaService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveTipoFiltroPessoa = function (){
                console.log('Requesting save on ${tipoFiltroPessoa}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                tipoFiltroPessoaService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = tipoFiltroPessoaService.create();
                    if (!$scope.editMode) {
                        $scope.searchTipoFiltroPessoa();
                    } else {
                        $location.path('/eleicao/'+ $stateParams.id + '/TipoFiltroPessoa');
                    }
                });
            };
            $scope.editTipoFiltroPessoa = function (obj) {
                $scope.selectedTipoFiltroPessoa = obj
                $location.path('/eleicao/'+ $scope.entity.id +'/TipoFiltroPessoa/' + obj.id );
            };
            $scope.createTipoFiltroPessoa = function (obj) {
                $scope.selectedTipoFiltroPessoa = tipoFiltroPessoaService.create();
                $scope.selectedTipoFiltroPessoa.eleicao = $scope.entity;
                $location.path('/eleicao/'+ $scope.entity.id +'/TipoFiltroPessoa/');
            };

            $scope.tipoFiltroPessoaPaginationChanged = function(newPage) {
                $scope.tipoFiltroPessoaPagination.currentPage = newPage;
                $scope.findTipoFiltroPessoa();
            };

            $scope.isTipoFiltroPessoaActive = ($stateParams.child === 'TipoFiltroPessoa' && $stateParams.tipoFiltroPessoaId) ;
             $scope.selectedUrna = null;

            $scope.getUrna = function ( urnaId ) {
                urnaService.get( {'id':urnaId} , function (data) {
                    $scope.selectedUrna = data;
                }, function (data){
                    // Case error or not found ..back to search filter
                    $location.path('/eleicao/'+ $stateParams.id);
                });
            };

            if ($stateParams.child == 'Urna' && $stateParams.urnaId) {
                $scope.masterDetail = $stateParams.child;
                if ($stateParams.urnaId) {
                    $scope.getUrna( $stateParams.urnaId );
                }
            }

            $scope.findUrna = function() {
                console.log('Realizando busca de 1 x N( urna.eleicao.id):' + $stateParams.id);

                $scope.gridOptions = urnaService.createGridOptions(true);
                $scope.urnaPagination = $scope.gridOptions.getPagination();

                $scope.resultPage = null;
                urnaService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.searchUrna = function() {
                // TODO Implement query search with fields
                console.log('Realizando busca de 1 x N( urna.eleicao.id):' + $scope.entity.id);
                $scope.resultPage = null;
                urnaService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridOptions.getPageRequest()}  , function (data) {
                    $scope.resultPage = data;
                    try {
                        $scope.resultPage.content = jsog.decode(data.content);
                    } catch (exception) {
                        console.log('Object with self rererence');
                        $scope.resultPage.content = data.content;
                    }
                });
            };
            $scope.removeUrna = function (obj){
                console.log('Deleting urna with id:' + obj.id);
                urnaService.remove( obj , function ( successResult) {
                    console.log(successResult);
                    $scope.entity = {};
                });
            };
            $scope.saveUrna = function (){
                console.log('Requesting save on ${urna}');
                $scope.$broadcast('show-errors-check-validity');
                // if any invalid validation
                if (!$scope.mainForm || $scope.mainForm.$invalid) {
                    console.log('Validation Error');
                    return;
                }

                $scope.searchSelects.toEntity($scope.selected);
                urnaService.save( $scope.selected , function (successResult) {
                    console.log(successResult);
                    $scope.entity = urnaService.create();
                    if (!$scope.editMode) {
                        $scope.searchUrna();
                    } else {
                        $location.path('/eleicao/'+ $stateParams.id + '/Urna');
                    }
                });
            };
            $scope.editUrna = function (obj) {
                $scope.selectedUrna = obj
                $location.path('/eleicao/'+ $scope.entity.id +'/Urna/' + obj.id );
            };
            $scope.createUrna = function (obj) {
                $scope.selectedUrna = urnaService.create();
                $scope.selectedUrna.eleicao = $scope.entity;
                $location.path('/eleicao/'+ $scope.entity.id +'/Urna/');
            };

            $scope.urnaPaginationChanged = function(newPage) {
                $scope.urnaPagination.currentPage = newPage;
                $scope.findUrna();
            };

            $scope.isUrnaActive = ($stateParams.child === 'Urna' && $stateParams.urnaId) ;


            // Selects to Fullfill select boxes
            // Begin block
            situacaoEleicaoService.findAll( {page:1 , size:vm.maxSelectBoxSize}, function (data) {
                vm.situacaoEleicaoList = jsog.decode(data.content);
            });

        }

})();
