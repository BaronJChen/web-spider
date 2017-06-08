/**
 * Created by Jason on 2017/6/4.
 */
define([
        'module/controller',
        'angular'
    ],
    function(controller) {
        return controller.controller('templateListController', [
            '$scope',
            function() {
                vm = this;
                return vm;
            }
        ]);
    }
);