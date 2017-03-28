(function() {
   'use strict';

	angular.module('sevApp').config(Config);

    function Config($translateProvider, $urlRouterProvider, $stateProvider) {

        $stateProvider
            .state('main', {
                url: "/",
                    controller: 'dashboardController',
                    controllerAs: 'dashboardController',
                    templateUrl: "views/dashboards/dashboard.html"
            })
            .state('dashboard', {
                url: "/dashboard",
                controller: 'dashboardController',
                controllerAs: 'dashboardController',
                templateUrl: "views/dashboards/dashboard.html"
            })
            .state('layout', {
                url: "/cards",
                controller: 'CardsController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/cards.html"
            })
            .state('book', {
                url: "/book",
                controller: 'BookController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/book.html"
            })
             .state('slick', {
                url: "/slick",
                controller: 'SlickController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/slick.html"
            })
            .state('pagination', {
                url: "/pagination",
                controller: 'PageController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/pagination.html"
            })
             .state('progressbar', {
                url: "/progressbar",
                controller: 'ProgressivebarController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/progressbar.html"
            })
             .state('alertas', {
                url: "/alertas",
                templateUrl: "layout/prototipo/alertas.html"
            })
             .state('botoes', {
                            url: "/botoes",
                            controller: 'BotoesController',
                            controllerAs: 'vm',
                            templateUrl: "layout/prototipo/botoes.html"
                        })
        ;

        

    }

})();