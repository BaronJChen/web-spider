/**
 * Created by Jason on 2017/6/4.
 */
define(['angular', 'restangular'], function (angular) {
    var service = angular.module('service', ['restangular'], angular.noop);

    service.config([
        'RestangularProvider',
        function (RestangularProvider) {
            RestangularProvider.setBaseUrl('/api');
            RestangularProvider.setErrorInterceptor(function (response, deferred, responseHandler) {
                switch (response.status) {
                    case 403:
                        console.log(403);
                    case 500:
                        console.log(500);
                    case 404:
                        console.log(404);
                    default:
                        console.log('unknown error: ' + response);
                }
            });
        }
    ]);

    return service;
});