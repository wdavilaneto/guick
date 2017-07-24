(function() {
    'use strict';

	angular.module('commons.directives')
	.directive('fileread', [function () {
        return {
            scope: {
                fileread: '=',
                filetype: '='
            },
            link: function (scope, element, attributes) {
                element.bind('change', function (changeEvent) {
                    var reader = new FileReader();
                    reader.onload = function (loadEvent) {
                        scope.$apply(function () {
                            var info = loadEvent.target.result;
                            //console.log(info);
                            // now we get ony the base64 info
                            scope.fileread = info.split(',');//.shift().join();
                            scope.filetype = scope.fileread[0]; // get filetype before remove ot from base64
                            scope.fileread.shift();
                            scope.fileread = scope.fileread.join();
                        });
                    }
                    reader.readAsDataURL(changeEvent.target.files[0]);
                });
            }
        };
    }])
})();