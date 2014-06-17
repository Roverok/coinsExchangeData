;(function(objects) {
  
  'use strict';
  
  var func = function() {
    
    var each = function(collection, func, context) {
      context = context || window;
      _.each(collection, func, context);
    };

    var forOwn = function(obj, func) {
      _.forOwn(obj, func);
    };

    var timestampDecorator = function(obj) {
      obj.ts = +new Date();
      return obj;
    };

    var isEmpty = function(arr) {
      return _.isEmpty(arr);
    };
    
    var isArray = function(arr) {
      return _.isArray(arr);
    };

    var reportDateToDate = function() {

    };


    var reportDateToDate = function(date) {
      // date is yyyymmdd
      var y, m, d, arr;
      arr = date.split('');
      y = arr.slice(0, 4);
      m = arr.slice(4, 6);
      d = arr.slice(6, 8);
      
      return m.join('') + '/' + d.join('') + '/' + y.join('');  
    };

    /**
      * This method grouped any array by any property name.
      *
      * @param {array} items The collection which should be grouped.
      * @param {string} property The property for the grouping.
      * @return {object} The grouped object.
      */
    var groupByProperty = function(items, property) {
      var len, retObj = {};
      len = items.length;
      
      for (var i = 0; i < len; i++) {
        var item, prop 

        item = items[i];
        prop = eval("items[i]." + property);
        if (!retObj[prop])
          retObj[prop] = [item];
        else
          retObj[prop].push(item);
      }

      return retObj;
    };

    var wrapStrInQuotes = function(str) {
      return "'" + str + "'";
    };

    var wrapStrInCommas = function(str) {
      return "," + str + ",";
    };

    var isUndefined = function(arg) {
      return _.isUndefined(arg);
    };

    var guid = function() {
      function _p8(s) {
          var p = (Math.random().toString(16)+"000000000").substr(2,8);
          return s ? "-" + p.substr(0,4) + "-" + p.substr(4,4) : p ;
      }
      return _p8() + _p8(true) + _p8(true) + _p8();
    };

    var vsprintf = function() {
      return _.str.vsprintf.apply(window, arguments);
    };

    var contains = function(collection, target) {
      return _.contains(collection, target);
    };

    var bind = function($scopeObj, property, dataToBind) {
      $scopeObj[property] = dataToBind; 
    };

    return {
      each              : each,
      forOwn            : forOwn,
      timestampDecorator: timestampDecorator,
      isEmpty           : isEmpty,
      isArray           : isArray,
      reportDateToDate  : reportDateToDate,
      groupByProperty   : groupByProperty,
      wrapStrInQuotes   : wrapStrInQuotes,
      wrapStrInCommas   : wrapStrInCommas,
      isUndefined       : isUndefined,
      guid              : guid,
      vsprintf          : vsprintf,
      contains          : contains,
      bind              : bind  
    };

  };

  objects.factory('objectUtils', [
    func
  ]);
})(CLIENT.objects);
