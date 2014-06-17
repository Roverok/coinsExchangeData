;(function(window) {

  'use strict';

  window.CLIENT = {
    app       : undefined,
    modules   : {},
    services  : angular.module('services', []),
    directives: angular.module('directives', []),
    filters   : angular.module('filters', []),
    entities  : angular.module('entities', []),
    objects   : angular.module('objects', [])
  };

})(window);


(function(CLIENT) {
  CLIENT.app = angular.module('app', [
    'services',
    'directives',
    'filters',
    'entities',
    'objects',
    'ui.router',
    'module.main'
  ]);

  // application constants.
  CLIENT.app.constant('CONST', {
    APPLICATION_NAME : 'Coins Exchange'
  });

  CLIENT.app.config(function($urlRouterProvider, $httpProvider) { 
    $urlRouterProvider.otherwise('/');
  });

  CLIENT.app.run(function($rootScope) {
     
    $rootScope.$safeApply = function($scope, fn) {
      fn = fn || function() {};
      if($scope.$$phase)
        fn();
      else 
        $scope.$apply(fn); 
    };
  });

})(CLIENT);