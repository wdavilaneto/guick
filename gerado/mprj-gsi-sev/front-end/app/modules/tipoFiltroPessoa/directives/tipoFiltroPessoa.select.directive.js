
(function() {
    'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     */

    angular.module('sevApp.tipoFiltroPessoa')
    .directive('tipofiltropessoaSelect', function(){
        return {
            restrict: 'EA',
            templateUrl: 'modules/tipoFiltroPessoa/directives/tipoFiltroPessoa.select.directive.html'
        };
    });

})();
