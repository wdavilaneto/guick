'use strict';

/**
 * @ngdoc service
 * @name sevApp.sevService
 * @description
 * # myService
 * Factory in the sevApp.
 */

var sevServices = angular.module('sevApp');

sevServices.factory('dashboardService', ['$resource',
    function ($resource) {
        var basicOptions = {
            chart: {
                type: 'discreteBarChart',
                title: {
                    enable: true,
                    text: "Graficos cfrequencia"
                },
                height: 400,
                margin : {
                    top: 20,
                    right: 20,
                    bottom: 65,
                    left: 80
                },
                x: function(d){return d.label;},
                y: function(d){return parseInt(d.value);},
                showValues: true,
                transitionDuration: 500,
                xAxis: {
                    rotateLabels: -25
                },
                yAxis: {
                    axisLabelDistance: 10
                }
            }
        };

        var service = $resource('/sev/api/reports/dashboard/', {}, {
            'getResultadoApuracaoCollectionWithSumCandidatoQtVotos': { method: 'GET', url: '/sev/api/reports/dashboard/getResultadoApuracaoCollectionWithSumCandidatoQtVotos', isArray: true},
            'getCandidatoCollectionWithSumEleicaoCriterioDesempate': { method: 'GET', url: '/sev/api/reports/dashboard/getCandidatoCollectionWithSumEleicaoCriterioDesempate', isArray: true},
            'getEleitorCollectionWithSumEleicaoVersao': { method: 'GET', url: '/sev/api/reports/dashboard/getEleitorCollectionWithSumEleicaoVersao', isArray: true},
            'getUrnaCollectionWithSumEleicaoVersao': { method: 'GET', url: '/sev/api/reports/dashboard/getUrnaCollectionWithSumEleicaoVersao', isArray: true},
            'allCount': { method: 'GET', url: '/sev/api/reports/dashboard/allCount', isArray: true}

        });

        service.getBasicOptions = function (){
            return basicOptions;
        };

        return service;
    }
]);
