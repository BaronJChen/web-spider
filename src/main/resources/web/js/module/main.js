/**
 * Created by Jason on 2017/6/4.
 */
define([
    'angular',
    'module/controller',
    'module/service',
    'controller/index',
    'controller/create-task',
    'controller/crontab',
    'controller/home',
    'controller/setup',
    'controller/task-list',
    'controller/template-list',
    'angularCookies',
    'angularTranslate',
    'angularRoute',
    'angularTranslateLoader'
], function(angular) {
    var mainModule = angular.module('mainModule', ['controller', 'service', 'pascalprecht.translate', 'ngCookies', 'ui.router'], angular.noop);

    mainModule.config([
        '$translateProvider',
        '$locationProvider',
        '$stateProvider',
        function($translateProvider, $locationProvider, $stateProvider) {
            $locationProvider.html5Mode(true);
            $translateProvider.useStaticFilesLoader({
                prefix: '/i18n/',
                suffix: '/index.json'
            });
            $translateProvider.useSanitizeValueStrategy('escaped');
            $translateProvider.preferredLanguage('en-US');

            $stateProvider.state('task-list', {
                'templateUrl': '/html/task-list.html',
                'controller': 'taskListController'
            }).state('template-list', {
                'templateUrl': '/html/template-list.html',
                'controller': 'templateListController'
            }).state('crontab', {
                'templateUrl': '/html/crontab.html',
                'controller': 'crontabController'
            }).state('setup', {
                'templateUrl': '/html/setup.html',
                'controller': 'setupController'
            }).state('home', {
                'templateUrl': '/html/home.html',
                'controller': 'homeController'
            }).state('create-task', {
                'templateUrl': '/html/create-task.html',
                'controller': 'createTaskController'
            });
        }
    ]);

    mainModule.run([
        '$cookies',
        '$translate',
        '$state',
        function($cookies, $translate, $state) {
            var languange = $cookies.get('language');

            if (languange) {
                $translate.preferredLanguage(languange);
            } // if

            $state.go('task-list');
        }
    ]);

    return mainModule;
});