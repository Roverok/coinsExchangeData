;(function(services) {
  
  'use strict';
  
  var func = function($http, $q) {

    var fetch = function() {
    	var deferred = $q.defer();
    	$http.get('app/heatmap').
        	success(function(data, status, headers, config) {
        		deferred.resolve(data);  
        	}).
        	error(function(data, status, headers, config) {
        		// log error
        	});
    	return deferred.promise;
    };

    return { fetch: fetch };
  };

  services.factory('heatMap.service', [
    '$http',
    '$q',
    func
  ]);

})(CLIENT.services);