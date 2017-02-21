(function() {
	'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:eleicaoExtController
 * @description
 * # eleicaoExtController
 * Extended Controller pr Master Detail and other specific operations of the sevApp
 */
    angular.module('sevApp')
        .controller('eleicaoExtController', eleicaoExtController);

    eleicaoExtController.$inject = ['$scope', '$stateParams', '$modal', '$location', 'jsog', 'eleicaoService' , 'arquivoRelatorioService', 'candidatoService', 'situacaoEleicaoService', 'eleicaoParametroService', 'eleitorService', 'eventoService', 'integranteComissaoService', 'tipoFiltroPessoaService', 'urnaService'];

    function eleicaoExtController($scope, $stateParams , $modal, $location, jsog, eleicaoService , arquivoRelatorioService, candidatoService, situacaoEleicaoService, eleicaoParametroService, eleitorService, eventoService, integranteComissaoService, tipoFiltroPessoaService, urnaService) {

        $scope.masterDetail = null;

        $scope.create = function ( parent ) {
            $scope.selected = null;
            $scope.selected = {
                eleicao:parent
            };
        };

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

        $scope.gridArquivoRelatorioOptions = arquivoRelatorioService.createGridOptions(true);
        $scope.arquivoRelatorioPagination = $scope.gridArquivoRelatorioOptions.getPagination();

        $scope.findArquivoRelatorio = function() {
            console.log('Realizando busca de 1 x N( arquivoRelatorio.eleicao.id):' + $stateParams.id);
            $scope.resultPage = null;
            arquivoRelatorioService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridArquivoRelatorioOptions.getPageRequest()}  , function (data) {
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
            arquivoRelatorioService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridArquivoRelatorioOptions.getPageRequest()}  , function (data) {
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

            $scope.searchSelects.toEntity($scope.selectedArquivoRelatorio);
            arquivoRelatorioService.save( $scope.selectedArquivoRelatorio , function (successResult) {
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

        $scope.gridCandidatoOptions = candidatoService.createGridOptions(true);
        $scope.candidatoPagination = $scope.gridCandidatoOptions.getPagination();

        $scope.findCandidato = function() {
            console.log('Realizando busca de 1 x N( candidato.eleicao.id):' + $stateParams.id);
            $scope.resultPage = null;
            candidatoService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridCandidatoOptions.getPageRequest()}  , function (data) {
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
            candidatoService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridCandidatoOptions.getPageRequest()}  , function (data) {
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

            $scope.searchSelects.toEntity($scope.selectedCandidato);
            candidatoService.save( $scope.selectedCandidato , function (successResult) {
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

        $scope.gridEleicaoParametroOptions = eleicaoParametroService.createGridOptions(true);
        $scope.eleicaoParametroPagination = $scope.gridEleicaoParametroOptions.getPagination();

        $scope.findEleicaoParametro = function() {
            console.log('Realizando busca de 1 x N( eleicaoParametro.eleicao.id):' + $stateParams.id);
            $scope.resultPage = null;
            eleicaoParametroService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridEleicaoParametroOptions.getPageRequest()}  , function (data) {
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
            eleicaoParametroService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridEleicaoParametroOptions.getPageRequest()}  , function (data) {
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

            $scope.searchSelects.toEntity($scope.selectedEleicaoParametro);
            eleicaoParametroService.save( $scope.selectedEleicaoParametro , function (successResult) {
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

        $scope.gridEleitorOptions = eleitorService.createGridOptions(true);
        $scope.eleitorPagination = $scope.gridEleitorOptions.getPagination();

        $scope.findEleitor = function() {
            console.log('Realizando busca de 1 x N( eleitor.eleicao.id):' + $stateParams.id);
            $scope.resultPage = null;
            eleitorService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridEleitorOptions.getPageRequest()}  , function (data) {
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
            eleitorService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridEleitorOptions.getPageRequest()}  , function (data) {
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

            $scope.searchSelects.toEntity($scope.selectedEleitor);
            eleitorService.save( $scope.selectedEleitor , function (successResult) {
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

        $scope.gridEventoOptions = eventoService.createGridOptions(true);
        $scope.eventoPagination = $scope.gridEventoOptions.getPagination();

        $scope.findEvento = function() {
            console.log('Realizando busca de 1 x N( evento.eleicao.id):' + $stateParams.id);
            $scope.resultPage = null;
            eventoService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridEventoOptions.getPageRequest()}  , function (data) {
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
            eventoService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridEventoOptions.getPageRequest()}  , function (data) {
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

            $scope.searchSelects.toEntity($scope.selectedEvento);
            eventoService.save( $scope.selectedEvento , function (successResult) {
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

        $scope.gridIntegranteComissaoOptions = integranteComissaoService.createGridOptions(true);
        $scope.integranteComissaoPagination = $scope.gridIntegranteComissaoOptions.getPagination();

        $scope.findIntegranteComissao = function() {
            console.log('Realizando busca de 1 x N( integranteComissao.eleicao.id):' + $stateParams.id);
            $scope.resultPage = null;
            integranteComissaoService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridIntegranteComissaoOptions.getPageRequest()}  , function (data) {
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
            integranteComissaoService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridIntegranteComissaoOptions.getPageRequest()}  , function (data) {
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

            $scope.searchSelects.toEntity($scope.selectedIntegranteComissao);
            integranteComissaoService.save( $scope.selectedIntegranteComissao , function (successResult) {
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

        $scope.gridTipoFiltroPessoaOptions = tipoFiltroPessoaService.createGridOptions(true);
        $scope.tipoFiltroPessoaPagination = $scope.gridTipoFiltroPessoaOptions.getPagination();

        $scope.findTipoFiltroPessoa = function() {
            console.log('Realizando busca de 1 x N( tipoFiltroPessoa.eleicao.id):' + $stateParams.id);
            $scope.resultPage = null;
            tipoFiltroPessoaService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridTipoFiltroPessoaOptions.getPageRequest()}  , function (data) {
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
            tipoFiltroPessoaService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridTipoFiltroPessoaOptions.getPageRequest()}  , function (data) {
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

            $scope.searchSelects.toEntity($scope.selectedTipoFiltroPessoa);
            tipoFiltroPessoaService.save( $scope.selectedTipoFiltroPessoa , function (successResult) {
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

        $scope.gridUrnaOptions = urnaService.createGridOptions(true);
        $scope.urnaPagination = $scope.gridUrnaOptions.getPagination();

        $scope.findUrna = function() {
            console.log('Realizando busca de 1 x N( urna.eleicao.id):' + $stateParams.id);
            $scope.resultPage = null;
            urnaService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridUrnaOptions.getPageRequest()}  , function (data) {
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
            urnaService.search( {'content':{ 'eleicao':{id:$stateParams.id }},'pagination':$scope.gridUrnaOptions.getPageRequest()}  , function (data) {
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

            $scope.searchSelects.toEntity($scope.selectedUrna);
            urnaService.save( $scope.selectedUrna , function (successResult) {
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


        $scope.back = function (){
            if ($stateParams.child) {
                console.log('Redirecting to /Eleicao/'+ $stateParams.id + '/' + $stateParams.child);
                $location.path('/eleicao/'+ $stateParams.id + '/' + $stateParams.child);
            } else {
                $window.history.back();
            }
        };

        $scope.clear = function () {
            $scope.selected = null;
        };

    }

})();
