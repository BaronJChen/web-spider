/**
 * Created by Jason on 2017/6/4.
 */
require.config({
    baseUrl: '/js',
    paths: {
        jQuery: 'lib/jquery/jquery-3.2.1.min',
        angular: 'lib/angular/angular.min',
        angularCookies: 'lib/angular/angular-cookies.min',
        angularTranslate: 'lib/angular/angular-translate.min'
    },
    shim: {
        jQuery: {
          exports: 'jQuery'
        },
        angular: {
            deps: ['jQuery'],
            exports: 'angular'
        },
        angularCookies: {
            deps: ['angular'],
            exports: 'angular'
        },
        angularTranslate: {
            deps: ['angular'],
            exports: 'angular'
        }
    }
});

define(['jQuery', 'angular', 'module/main'], function($, angular){
    return angular.bootstrap(document, ['mainModule'])
});