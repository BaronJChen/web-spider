/**
 * Created by Jason on 2017/6/4.
 */
define([
    'module/controller',
    'angular',
    'angularTranslate',
    'angularTranslateLoader'],
    function (controller) {
        return controller.controller('indexController', [
            '$scope', 
            function () {
                vm = this;
                vm.data = 'this is high';
                return vm;
            }
        ]);
    }
);