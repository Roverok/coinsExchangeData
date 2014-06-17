;(function(entities) {
  
  'use strict';
  
  var func = function(objectUtils) {
    
    var Constructor = function() {
     
    };

    /**
     * get all object's own property
     * in a json format.
     * 
     * @return {object} Objects's own properties.
     */
    Constructor.prototype.toJSON = function() {
   
      var retJson = {};

      objectUtils.forOwn(this, function(val, key) {
        retJson[key] = val;
      });

      return retJson;
    };

    return Constructor;
  };

  entities.factory('Entity', [
    'objectUtils',
    func
  ]);
})(CLIENT.entities);
