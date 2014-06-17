;(function(entities) {
  
  'use strict';
  
  var func = function(Entity, objectUtils) {
    
    var Constructor = function() {
         
    };

    Constructor.extend(Entity);
    return Constructor;
  };

  entities.factory('Asset', [
    'Entity',
    'objectUtils',
    func
  ]);
})(CLIENT.entities);
