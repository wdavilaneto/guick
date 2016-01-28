(function() {
    'use strict';

	angular.module('commons.directives')
	.directive('newScope', [function() {
        return {
            scope: true,
            priority: 450,
        };
    }])

})();