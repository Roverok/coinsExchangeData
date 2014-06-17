// Main app Controller
// -----------------------
;(function(app) {

  'use strict';

  // Dependency injection.
  var func = function(
    $scope, 
    $rootScope, 
    $location, 
    $state, 
    StringsService,
    CONST,
    objectUtils 
  ) {
    
    $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
      
    });



    // redirect helper for all childs scope.
    $scope.redirectTo = function(location, replace) {
      $location.path(location);  
      if (replace)
        $location.replace();
    };



    // set common functionality for all controllers.  
    $scope._super = function() {      

      if (!this.hasOwnProperty('onCreate')) {
        // TODO: throw exeption
        alert('scope doesnt have onCreate function');
        return;
      }   
      this.onCreate();

      if (this.hasOwnProperty('registerObservers') && _.isFunction(this.registerObservers)) {
        this.registerObservers();
      }

      this.getStrings = function(moduleName) {
         StringsService.get(this, moduleName); 
      };

      this.setMainClass = function(className) {
         $('#main').removeClass()
            .addClass(className)  
      };  
      
      this.$on('$destroy', function() {
        
      });
    };



    $scope.registerObservers = function() {
     
    }; 


   
    $scope.onCreate = function() {
      objectUtils.bind($scope, 'applicationName', CONST.APPLICATION_NAME);  
    };
    
    $scope._super();
  };



  app.controller('AppCtrl', [
    '$scope', 
    '$rootScope',
    '$location',
    '$state',
    'StringsService',
    'CONST',
    'objectUtils',
    func
  ]);


})(CLIENT.app);
