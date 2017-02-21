
(function() {
    'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     */

    angular.module('sevApp.eleicao')
    .directive('eleicaoSelect', function(){
        return {
            restrict: 'EA',
            templateUrl: 'modules/eleicao/directives/eleicao.select.directive.html'
        };
    });

})();
