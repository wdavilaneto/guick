
(function() {
    'use strict';

    /**
     *  Guick Generate class: https://github.com/wdavilaneto/guick
     *  Author: service-wdavilaneto@redhat.com
     *  This source is free under The Apache Software License, Version 2.0
     *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
     */

    angular.module('sevApp.candidatoPadrao')
    .directive('candidatopadraoSelect', function(){
        return {
            restrict: 'EA',
            templateUrl: 'modules/candidatoPadrao/directives/candidatoPadrao.select.directive.html'
        };
    });

})();
