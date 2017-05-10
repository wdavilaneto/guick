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
            .state('treeview', {
                url: "/treeview",
                controller: 'TreeviewController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/treeview.html"
            })
            .state('modal', {
                url: "/modal",
                controller: 'ModalPrototipoController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/modal.html"
            })
            .state('graficos', {
                url: "/graficos",
                controller: 'GraficosController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/graficos.html"
            })
            .state('mailbox', {
                url: "/mailbox",
                controller: 'MailBoxController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/mailbox.html"
            })
            .state('widgets', {
                url: "/widgets",
                controller: 'WidgetsController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/widgets.html"
            })
            .state('form', {
                url: "/form",
                templateUrl: "layout/prototipo/form.html"
            })
             .state('wizard', {
                url: "/wizard",
                controller: 'WizardController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/wizard.html"
            })
             .state('boxes', {
                url: "/boxes",
                controller: 'botoesController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/boxes.html"
                        })
            .state('labels', {
                url: "/labels",
                controller: 'botoesController',
                controllerAs: 'vm',
                templateUrl: "layout/prototipo/labels.html"
                })

        ;
    }
})();