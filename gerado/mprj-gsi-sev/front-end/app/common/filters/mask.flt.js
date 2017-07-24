(function() {
    'use strict';

    angular.module('commons.filters')
    .filter('mask', [function() {
    	return function(number, mask) {
    		if(!number) {
	            return "";
			}
			var index = 0;
	        var masked = "";
	        for (var i = 0; i < mask.length; i++) {
	            var c = mask.charAt(i);
	            if (c == '#') {
	                masked += number.charAt(index);
	                index++;
	            } else if (c == 'x') {
	                masked += c;
	                index++;
	            } else {
	                masked += c;
	            }
	        }
	        return masked;
    	}
    }])
    
})();