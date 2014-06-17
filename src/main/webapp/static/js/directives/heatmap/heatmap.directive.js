;(function(directives) {
  
  'use strict';

  var func = function($rootScope, $compile) { 

    var linkFunc = function($scope, $elem, attrs) {    
      
      var bindWatchers = function() {
    	  $scope.$watch('heatMapData', function(newVal, oldVal) {
    		  if (!newVal) return;
    		  render();
    	  });
      };
      
      var extractOptions = function() {
    	  
      };
      
      var setLocalUiVars = function() {
    	  
      };
      
      var registerListeners = function() {
    	 
      };
      
      var render = function() {
    	  var items = $scope.heatMapData.data.slice(0, 55);
    	  for (var i = 0; i < items.length; i++) {
    		  var html = '', childScope, compiledHtml, placeholder, item = items[i];
    		  
    		  childScope      = $rootScope.$new();
              childScope.item = item;  
              placeholder     = $elem.find('#' + item.pair.symbol);
    		  
    		  html += '<div class="cx-pair" tooltip data="item">';
              	html += '<div class="cx-pair-name">' + item.pair.name + '</div>';
              	html += '<div class="cx-pair-last-traded-price">' + item.lastTradePriceAvg.toFixed(4) + '</div>';
              html += '</div>'; 
                            
              compiledHtml = $compile(html)(childScope);
              placeholder.append(compiledHtml);
    	  }
      };
                     
     



      var init = function() {
    	bindWatchers();
        extractOptions();
        setLocalUiVars();
        registerListeners();
      };

      init();
    };



    return {
      restrict    : 'E',
      replace     : true,   
      scope       : {heatMapData: '='},
      templateUrl : 'static/js/directives/heatmap/heatmap.tpl.html',
      link        : linkFunc
    };    
  };

  directives.directive('heatmap', [
    '$rootScope',
    '$compile',
    func
  ]);
})(CLIENT.directives);
