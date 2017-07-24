'use strict';

/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * @ngdoc function
 * @name sevApp.controller:dashboardController
 * @description
 * # dashboardController
 * Controller of the sevApp
 */
angular.module('sevApp').controller('dashboardController', ['$scope', '$stateParams', '$modal', '$location', 'dashboardService', function ($scope, $stateParams , $modal, $location, dashboardService ) {

    $scope.allCount = [];
    $scope.options = dashboardService.getBasicOptions();

    dashboardService.allCount( {}, function (data) {
        $scope.allCount = [{ key: 'Quantidade por Entitdade',values: data }];
    });

    $scope.optionsResultadoApuracaoCollectionWithSumCandidatoQtVotos = dashboardService.getBasicOptions();
    $scope.optionsResultadoApuracaoCollectionWithSumCandidatoQtVotos.chart.xAxis.axisLabel = "Candidato";
    $scope.optionsResultadoApuracaoCollectionWithSumCandidatoQtVotos.chart.yAxis.axisLabel = "Candidato QtVotos";
    dashboardService.getResultadoApuracaoCollectionWithSumCandidatoQtVotos( {}, function (data) {
        $scope.getResultadoApuracaoCollectionWithSumCandidatoQtVotos = [{ key: 'ResultadoApuracaoCollection With Sum of Candidato QtVotos',values: data }];
    });


    $scope.optionsCandidatoCollectionWithSumEleicaoCriterioDesempate = dashboardService.getBasicOptions();
    $scope.optionsCandidatoCollectionWithSumEleicaoCriterioDesempate.chart.xAxis.axisLabel = "Eleicao";
    $scope.optionsCandidatoCollectionWithSumEleicaoCriterioDesempate.chart.yAxis.axisLabel = "Eleicao CriterioDesempate";
    dashboardService.getCandidatoCollectionWithSumEleicaoCriterioDesempate( {}, function (data) {
        $scope.getCandidatoCollectionWithSumEleicaoCriterioDesempate = [{ key: 'CandidatoCollection With Sum of Eleicao CriterioDesempate',values: data }];
    });


    $scope.optionsEleitorCollectionWithSumEleicaoVersao = dashboardService.getBasicOptions();
    $scope.optionsEleitorCollectionWithSumEleicaoVersao.chart.xAxis.axisLabel = "Eleicao";
    $scope.optionsEleitorCollectionWithSumEleicaoVersao.chart.yAxis.axisLabel = "Eleicao Versao";
    dashboardService.getEleitorCollectionWithSumEleicaoVersao( {}, function (data) {
        $scope.getEleitorCollectionWithSumEleicaoVersao = [{ key: 'EleitorCollection With Sum of Eleicao Versao',values: data }];
    });




    $scope.optionsUrnaCollectionWithSumEleicaoVersao = dashboardService.getBasicOptions();
    $scope.optionsUrnaCollectionWithSumEleicaoVersao.chart.xAxis.axisLabel = "Eleicao";
    $scope.optionsUrnaCollectionWithSumEleicaoVersao.chart.yAxis.axisLabel = "Eleicao Versao";
    dashboardService.getUrnaCollectionWithSumEleicaoVersao( {}, function (data) {
        $scope.getUrnaCollectionWithSumEleicaoVersao = [{ key: 'UrnaCollection With Sum of Eleicao Versao',values: data }];
    });



}]);

