;(function(directives) {
  
  'use strict';

  var func = function() { 

    var linkFunc = function($scope, $elem, attrs) {    


      var init = function() {
    	
      };

      init();
    };



    return {
      restrict    : 'E',
      replace     : true,   
      scope       : {ordersData: '='},
      templateUrl : 'static/js/directives/orders/orders.tpl.html',
      link        : linkFunc
    };    
  };

  directives.directive('orders', [
    func
  ]);
})(CLIENT.directives);
