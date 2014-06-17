;(function(namespace) {

  'use strict';

  CLIENT.modules = _.extend(namespace, {
    main: angular.module('module.main', [])
  });

  CLIENT.modules.main.config(function($stateProvider, $urlRouterProvider) {
   
    // routing
    // -------
    $stateProvider       
      .state('main', {
        url        : '/',
        templateUrl: 'static/js/modules/main/views/main.tpl.html',
        controller : 'MainCtrl'
      });
  });
})(CLIENT.modules);