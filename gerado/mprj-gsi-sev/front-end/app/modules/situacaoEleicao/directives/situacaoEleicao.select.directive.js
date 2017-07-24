
(function() {
    'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     */

    angular.module('sevApp.situacaoEleicao')
    .directive('situacaoeleicaoSelect', function(){
        return {
            restrict: 'EA',
            templateUrl: 'modules/situacaoEleicao/directives/situacaoEleicao.select.directive.html'
        };
    });

})();
