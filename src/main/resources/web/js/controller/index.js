/**
 * Created by Jason on 2017/6/4.
 */
define(['module/controller'], function(controller) {
    return controller.controller(
        'indexController',[
            'angular',
            'jQuery',
            function (angular, $) {
                vm = this;
                vm.data = 'this is high';
                return vm;
            }
        ]);
});