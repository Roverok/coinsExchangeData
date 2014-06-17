;(function(services) {
  
  'use strict';
  
  var func = function() {
    
    var _const, common;  

    _const = {
      
    };  

    // shared application strings values.
    common = {
      btns : {
        save: 'Save',
        close: 'Close',
      },
      messages: {
        updateSuccess: 'You\'r settings have been updated.'
      }
    };



    // module specific strings values.
    var strings = {
      main: {
        containerTitle: 'Dashboard',
        templateTitle: 'Dashboard Settings',
        title: 'Select You\'r Widgets'
      }
    }



    // get the specific module strings object
    // extended with the common strings object.
    var get = function($scope, moduleName) {
      $scope.strings = _.extend(_.extend(strings[moduleName], common), _const);  
    };
   
    return { get: get };
  };

  services.factory('StringsService', [
    func
  ]);

})(CLIENT.services);
