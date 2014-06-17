;(function(directives) {
  
  'use strict';

  var func = function($rootScope) { 

    var linkFunc = function($scope, $elem, attrs) {    
      
		var bindWatchers = function() {
			$scope.$watch('chartData', function(newVal, oldVal) {
				if (!newVal) return;
				
				initializeChart();
			});
		};
		
		
		var initializeChart = function() {
			 $elem.highcharts({
		            chart: {
		                type: 'column'
		            },
		            title: {
		                text: 'BTC Exchange'
		            },
		            subtitle: {
		                text: ''
		            },
		            xAxis: {
		                type: 'category',
		                labels: {
		                    rotation: -45,
		                    style: {
		                        fontSize: '13px',
		                        fontFamily: 'Verdana, sans-serif'
		                    }
		                }
		            },
		            yAxis: {
		                min: 0,
		                title: {
		                    text: ''
		                }
		            },
		            legend: {
		                enabled: false
		            },
		            tooltip: {
		                pointFormat: '<b>{point.y}</b>',
		            },
		            series: [{
		                name: '',
		                data: $scope.chartData,
		                dataLabels: {
		                    enabled: true,
		                    //rotation: -90,
		                    color: '#333',
		                    align: 'right',
		                    x: -9,
		                    y: -3,
		                    style: {
		                        fontSize: '11px',
		                        fontFamily: 'Verdana, sans-serif',
		                        //textShadow: '0 0 3px black'
		                    }
		                }
		            }]
		        });
		};


		var init = function() {
			bindWatchers();
		};

		init();
    };



    return {
      restrict    : 'E',
      replace     : true,   
      scope       : {chartData: '='},
      template    : '<div class="cx-chart"></div>',
      link        : linkFunc
    };    
  };

  directives.directive('chart', [
    '$rootScope',
    func
  ]);
})(CLIENT.directives);
