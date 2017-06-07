/**
 * Created by Jason on 2017/6/4.
 */
define([
    'angular',
    'module/controller',
    'module/service',
    'controller/index',
    'angularTranslate',
    'angularTranslateLoader'
], function (angular) {
    var mainModule = angular.module('mainModule', ['controller', 'service', 'pascalprecht.translate'], angular.noop);

    mainModule.config([
        '$translateProvider',
        '$locationProvider',
        function ($translateProvider, $locationProvider) {
            $locationProvider.html5Mode(true);
            $translateProvider.useStaticFilesLoader({
                files: [{
                    prefix: '/i18n/',
                    suffix: '/index.json'
                }]
            });
            $translateProvider.preferredLanguage('en-US');
        }
    ]);

    return mainModule;
});