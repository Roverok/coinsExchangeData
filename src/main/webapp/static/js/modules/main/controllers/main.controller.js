;(function(app) {

  'use strict';

  var func = function($scope, heatMapService, objectUtils, crawlerService, $http) {
	  
	  $scope.heatMapData = undefined;
	  $scope.chartData   = undefined;
	  $scope.ordersData  = undefined;
 
	  
	var formatNumber = function(number) {
		number *= 1000;
		number = Math.round(number);
		number /= 1000;
		return number;
	};  
	  
    $scope.onCreate = function() {
    	
    	 /*$http.get('permissions.json', {name: 'Yoav'}).success(function(response) {
    		 debugger;
    	 });*/
    	
    	crawlerService.crawl().then(function(responseData) {
    		objectUtils.bind($scope, 'ordersData', responseData.orders);
    	});
    	
    	heatMapService.fetch().then(function(responseData) {
    		var chartData = [];
    		objectUtils.bind($scope, 'heatMapData', responseData);
    		
    		responseData.data.slice(0, 55).forEach(function(item, index) {
    			if (/^[a-z]+_btc$/.test(item.pair.symbol)) {
    				chartData.push([item.pair.name, formatNumber(item.lastTradePriceAvg)])
    			}
    		});
    		
    		objectUtils.bind($scope, 'chartData', chartData);
    	});
    };


    $scope._super();

  };


  app.controller('MainCtrl', [
    '$scope',
    'heatMap.service',
    'objectUtils',
    'crawler.service',
    '$http',
    func
  ]);

})(CLIENT.app);

