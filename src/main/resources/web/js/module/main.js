/**
 * Created by Jason on 2017/6/4.
 */
define(['angular', 'controller/index'], function(angular) {
    var mainModule;
    mainModule = angular.module('mainModule', ['indexController'], angular.noop);
    return mainModule;
})