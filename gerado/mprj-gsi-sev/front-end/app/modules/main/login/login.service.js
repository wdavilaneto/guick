(function() {
    'use strict';

    /**
     * @ngdoc service
     * @name sevApp.sevService
     * @description
     * # myService
     * Factory in the sevApp.
     */

    angular.module('sevApp.main')
        .service('loginService', ['$resource','authService', function ($resource, authService) {

            var self = this;

            var service = $resource('/sev/api/authentication', {'username':'@username','password':'@password'}, {
                'login': { method: 'POST', isArray: false, headers:{'Content-Type':'application/x-www-form-urlencoded'} ,ignoreAuthModule: 'ignoreAuthModule'},
                'logout': { method: 'GET', url:"/'sev/api/logout", isArray: false, ignoreAuthModule: 'ignoreAuthModule'},
                'authenticate': { method: 'GET', url:"/sev/api/authenticate", isArray: false, ignoreAuthModule: 'ignoreAuthModule'}
            })

            var currentUser = null;

            service.getCurrentUser = function (){
                return currentUser;
            };

            service.activateLogin = function (obj){
                authService.loginConfirmed(obj);
                currentUser = {isAuthenticated:'true', username:obj.username};
            };

            service.logoff = function (data) {
                authService.loginCancelled(data);
                currentUser = null;
            };

            this.hasRoles = function(roles) {
                var user = loginService.getCurrentUser();
                if(user) {
                    return self.usuarioLogado.roleList.some(function(e) {
                        return roles.indexOf(e) >= 0;
                    });
                }
                return false;
            };

            this.hasRole = function(role) {
                var user = loginService.getCurrentUser();
                if(user.permissions && user.permissions.length) {
                    return user && user.permissions.indexOf(role) >= 0;
                }
                return false;
            };
            return service;
        }
    ]);

})();