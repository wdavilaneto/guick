(function() {
    'use strict';

	angular.module('commons.directives')
	.directive('validaPeriodo', ['$filter', function($filter){
        return {
            require: 'ngModel',
            link: function (scope, elm, attrs, ctrl) {
                var verificaPeriodo = function (inputValue) {
                    var de = $filter('date')(inputValue, 'dd/MM/yyyy');
                    var ate = attrs.validaPeriodo;

                    var dataAteFormatada, dataDeFormatada;
                    var estaValido = false;

                    if(!de || !ate){
                        estaValido = true;
                        ctrl.$setValidity('validaPeriodo', estaValido);
                    }
                    if(ate){
                        dataAteFormatada = getDataFormatada(ate);
                    }
                    if(de){
                        dataDeFormatada = getDataFormatada(de);
                    }

                    if(Date.parse(dataAteFormatada) >= Date.parse(dataDeFormatada)){
                        estaValido = true;
                    }
                    ctrl.$setValidity('validaPeriodo', estaValido);

                    return inputValue;
                };

                attrs.$observe('validaPeriodo', function () {
                    verificaPeriodo(ctrl.$viewValue);
                });
            }
        };
    }]);

    var getDataFormatada = function (data) {
        var elmData = data.split("/");
        return new Date(elmData[2], elmData[1] -1, elmData[0]);
    };

})();