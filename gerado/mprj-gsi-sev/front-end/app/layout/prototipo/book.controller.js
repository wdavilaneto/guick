
(function () {

    'use strict';

    angular.module('sevApp').controller('BookController', BookController);


    function BookController() {


         //single book
            $('#mybook').booklet({
                width:'100%',
                height:'600px',
                tabs:  true,
                tabWidth:  180,
                tabHeight:  20,
                menu: '#custom-menu',
                pageSelector: true

            });

            //multiple books with ID's
            $('#mybook1, #mybook2').booklet();

            //multiple books with a class
            $('.mycustombooks').booklet();

    }

})();