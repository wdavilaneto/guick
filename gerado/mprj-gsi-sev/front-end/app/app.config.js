/**
 * @ngdoc overview
 * @name sevApp
 * @description
 * # sevApp
 *
 * Main module of the application.
 */
(function () {
    'use strict';

    var app = angular.module('sevApp')

        /**
         * Config ScaAngular
         */
//        .config(['scangularConfig', function (scangularConfig) {
//            scangularConfig.applicationName = 'sev';
//        }])

        /**
         * Configs de i18n
         */
        .config(['$translateProvider', 'ENV_CONFIG', function ($translateProvider, ENV_CONFIG) {
            $translateProvider.useStaticFilesLoader({
                prefix: 'i18n/locale-',
                suffix: '.json'
            });
            $translateProvider.preferredLanguage(ENV_CONFIG.preferredLanguage);
        }])

        /**
         * Configs do Spinner BlockUI
         */
        .config(['blockUIConfig', function (blockUIConfig) {
            blockUIConfig.autoInjectBodyBlock = false;
            blockUIConfig.message = '';
            blockUIConfig.blockBrowserNavigation = true;
            blockUIConfig.delay = 100;
        }])

        /**
         * Configs Datepicker
         */
        .config(function (datepickerConfig, datepickerPopupConfig) {
            datepickerPopupConfig.closeOnDateSelection = true;
            datepickerConfig.showWeeks = false;
            datepickerConfig.readOnly = false;
        })

        /**
         * Configs do provider do componente modal
         */
        .config(['dialogsProvider', function (dialogsProvider) {
            dialogsProvider.useBackdrop('static');
            dialogsProvider.useEscClose(true);
            dialogsProvider.useCopy(false);
            dialogsProvider.setSize('sm');
        }])

        /**
         * Configs do provider do xeditable
         */
        .run(['editableThemes', function (editableThemes) {
            editableThemes['default'].submitTpl = '<button type="submit">Salvar</button>';
            editableThemes['default'].cancelTpl = '<button type="button" ng-click="$form.$cancel()">Cancelar</button>';
        }])

        /**
         * Tratamento global de excecoes
         */
        .config(['$httpProvider', function ($httpProvider) {

            var nomeApp = 'sev';

            $httpProvider.interceptors.push(
                ['$q', 'toaster', '$window', '$location', function ($q, toaster, $window, $location) {

                    return {
                        'response': function (response) {

                            var title = response.headers('title');
                            var message = response.headers('message');

                            switch (response.status) {
                                case 200:
                                    break;
                                default:
                                    toaster.pop('success', title, message);
                            }

                            return response;
                        },

                        'responseError': function (errorResponse) {

                            switch (errorResponse.status) {
                                case 0:
                                    toaster.pop('error', 'Desculpe', 'Nossos servidores encontram-se indisponíveis no momento.');
                                    break;
                                case 400:
                                    angular.forEach(errorResponse.data.violations, function (value) {
                                        toaster.pop('warning', nomeApp, value.message);
                                    });
                                    toaster.pop('warning', nomeApp, errorResponse.data.error);
                                    break;
                                case 403: // 403-Forbidden - Tem autenticacao, mas, o acesso é proibido para este usuario.
                                    toaster.pop('warning', nomeApp, 'Você não tem permissão para acessar este conteúdo, ou partes, do conteudo, que foi requisitado.');
                                    break;
                                case 404:
                                    toaster.pop('info', nomeApp, 'Nenhum registro encontrado');
                                    break;
                                case 409:
                                    angular.forEach(errorResponse.data.violations, function (value) {
                                        toaster.pop('warning', nomeApp, value);
                                    });
                                    break;
                                case 500:
                                    //Neste caso o melhor é
                                    toaster.pop('error', nomeApp, errorResponse.data.error +
                                        ' ticket[' + errorResponse.data.ticket + ']', 9000, '');
                                    break;
                            }

                            return $q.reject(errorResponse);
                        }
                    };
                }
                ]);
        }])
        .run(['$templateCache', function ($templateCache) {
            $templateCache.put('/dialogs/custom.html', '<div class="modal-header"><h4 class="modal-title"><span class="glyphicon glyphicon-star"></span> User\'s Name</h4></div><div class="modal-body"><ng-form name="nameDialog" novalidate role="form"><div class="form-group input-group-lg" ng-class="{true: \'has-error\'}[nameDialog.username.$dirty && nameDialog.username.$invalid]"><label class="control-label" for="course">Name:</label><input type="text" class="form-control" name="username" id="username" ng-model="user.name" ng-keyup="hitEnter($event)" required><span class="help-block">Enter your full name, first &amp; last.</span></div></ng-form></div><div class="modal-footer"><button type="button" class="btn btn-default" ng-click="cancel()">Cancel</button><button type="button" class="btn btn-primary" ng-click="save()" ng-disabled="(nameDialog.$dirty && nameDialog.$invalid) || nameDialog.$pristine">Save</button></div>');
            $templateCache.put('/dialogs/custom2.html', '<div class="modal-header"><h4 class="modal-title"><span class="glyphicon glyphicon-star"></span> Custom Dialog 2</h4></div><div class="modal-body"><label class="control-label" for="customValue">Custom Value:</label><input type="text" class="form-control" id="customValue" ng-model="data.val" ng-keyup="hitEnter($event)"><span class="help-block">Using "dialogsProvider.useCopy(false)" in your applications config function will allow data passed to a custom dialog to retain its two-way binding with the scope of the calling controller.</span></div><div class="modal-footer"><button type="button" class="btn btn-default" ng-click="done()">Done</button></div>');
            $templateCache.put('ng-joyride-title-tplv1.html', '<div id=\"ng-joyride-title-tplv1\"><div class=\"ng-joyride sharp-borders intro-banner\" style=\"\"><div class=\"popover-inner\"><h3 class=\"popover-title sharp-borders\">{{heading}}</h3><div class=\"popover-content container-fluid\"><div ng-bind-html=\"content\"></div><hr><div class=\"row\"><div class=\"col-md-4 skip-class\"><a class=\"skipBtn pull-left\" type=\"button\"><i class=\"mdi mdi-exit-to-app\"></i>&nbsp;Sair</a></div><div class=\"col-md-8\"><div class=\"pull-right\"><button disabled=\"disabled\" class=\"prevBtn btn\" type=\"button\"><i class=\"mdi mdi-arrow-left-bold\"></i>&nbsp;Anterior</button> <button class=\"nextBtn btn btn-primary\" type=\"button\">Próximo&nbsp;<i class=\"mdi mdi-arrow-right-bold\"></i></button></div></div></div></div></div></div></div>');
        }]);
    ;
    app.factory('jsog', function () {
            return JSOG;
        }
    );
})();

