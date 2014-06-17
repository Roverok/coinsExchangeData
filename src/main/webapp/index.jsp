<!DOCTYPE html>
<html lang="en" ng-app="app">
  <head>
    <title></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="">
    <!--css-->
    <link href="static/style/css/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="static/style/css/bootstrap/css/bootstrap-theme.css" rel="stylesheet" media="screen">
    <link href="static/style/css/font-awesome.css" rel="stylesheet" media="screen">
    <link href="static/style/css/application.css" rel="stylesheet" media="screen">
    <link href="static/style/css/application-pages.css" rel="stylesheet" media="screen">
    <link href="static/style/css/application-animations.css" rel="stylesheet" media="screen">
    <!--css--> 
  </head>
  <!-- main application controller -->
  <body ng-controller="AppCtrl">
    
     <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">{{applicationName}}</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div id="page">
        <ui-view id="main"></ui-view>
    </div>
    
    <!--js-->
    
    <!-- libs -->
    <script src="static/js/lib/jquery/jquery-v2.1.1.js"></script>
    <script src="static/js/lib/bootstrap-3.1.1-dist/js/bootstrap.js"></script>
    <script src="static/js/lib/utils/lodash-v2.4.1.js"></script>
    <script src="static/js/lib/utils/custom.js"></script>
    <script src="static/js/lib/highcharts/highcharts.js"></script>
    <!-- Angular.JS-->
    <script src="static/js/lib/angular/angular-v1.2.16.js"></script>
    <script src="static/js/lib/angular/angular-ui-router-v0.2.10.js"></script>
    
    <!-- app main -->
    <script src="static/js/app.js"></script>
    <script src="static/js/app.controller.js"></script>
    
    <!-- services -->
    <script src="static/js/services/heatMap.service.js"></script>
    <script src="static/js/services/strings.service.js"></script>
    <script src="static/js/services/crawler.service.js"></script>

    <!-- directive -->
    <script src="static/js/directives/heatmap/heatmap.directive.js"></script>
    <script src="static/js/directives/tooltip/tooltip.directive.js"></script> 
    <script src="static/js/directives/chart/chart.directive.js"></script>
    <script src="static/js/directives/orders/orders.directive.js"></script>        
    
    <!-- objects -->
    <script src="static/js/objects/objectUtils.object.js"></script>
    
    <!-- entities -->
    <script src="static/js/entities/entity.entity.js"></script>
    <script src="static/js/entities/tail.entity.js"></script>
    <script src="static/js/entities/asset.entity.js"></script>    

    <!-- main -->
    <script src="static/js/modules/main/router.js"></script>
    <script src="static/js/modules/main/controllers/main.controller.js"></script>
    
    
    <!--js-->
  </body>
</html>
