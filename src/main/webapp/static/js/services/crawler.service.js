;(function(services) {
  
  'use strict';
  
  var func = function($http, $q) {

    var crawl = function() {
    	var deferred = $q.defer();
    	$http.get('app/crawler').
        	success(function(data, status, headers, config) {
        		deferred.resolve(data);  
        	}).
        	error(function(data, status, headers, config) {
        		// log error
        	});
    	return deferred.promise;
    };

    return { crawl: crawl };
  };

  services.factory('crawler.service', [
    '$http',
    '$q',
    func
  ]);

})(CLIENT.services);