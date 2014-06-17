;(function(directives) {
  
  'use strict';

  var func = function($rootScope, $timeout) { 

    var linkFunc = function($scope, $elem, attrs) {    
    	

      /**
       * Extracting the options attribute on the html tag.
       *
       * @void.
       */
      var extractOptions = function() {
        //options = $rootScope.$eval(attrs.options) || {};
      };



      /**
       * Setting dom references.
       *
       * @void.
       */
      var setLocalUiVars = function() {
        
      };



      var generateTooltipHtml = function() {
        var html = '';

        html += '<div style="padding:5px;">';
          html += '<div><span>Name</span>: <span>' + $scope.data.pair.name + '</span></div>';
          html += '<div><span>Last Traded Price</span> : <span>' + $scope.data.lastTradePriceAvg + '</span></div>';
          html += '<div><span>Trading Volume</span> : <span>' + $scope.data.volumeAvg + '</span></div>';
        html += '</div>';

        return html;
      };
      

      /**
       * Register for dom events.
       *
       * @void.
       */  
      var registerListeners = function() {	
		$elem.tooltip({
			html: true,
			title: generateTooltipHtml()
	    });
      };


      var init = function() {
        extractOptions();
        setLocalUiVars();
        registerListeners();
      };

      init();
    };


    return {
      restrict  : 'EA', 
      scope     : {data: '='},
      link      : linkFunc
    };    
  };

  directives.directive('tooltip', [
    '$rootScope',
    '$timeout',
    func
  ]);
})(CLIENT.directives);
