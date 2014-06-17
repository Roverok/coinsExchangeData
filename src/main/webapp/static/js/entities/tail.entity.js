;(function(entities) {
  
  'use strict';
  
  var func = function(Entity, objectUtils) {
    
    var Constructor = function(name, lastTradedPrice, P_Chng, tradingVolume) {
      this.name = name;
      this.lastTradedPrice = lastTradedPrice;
      this.P_Chng = P_Chng;
      this.tradingVolume = tradingVolume;
    };

    Constructor.extend(Entity);
    return Constructor;
  };

  entities.factory('Tail', [
    'Entity',
    'objectUtils',
    func
  ]);
})(CLIENT.entities);
