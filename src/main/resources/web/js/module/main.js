/**
 * Created by Jason on 2017/6/4.
 */
define(['angular', 'module/controller', 'module/service', 'controller/index'], function(angular) {
    var mainModule = angular.module('mainModule', ['controller', 'service'], angular.noop);
    return mainModule;
})