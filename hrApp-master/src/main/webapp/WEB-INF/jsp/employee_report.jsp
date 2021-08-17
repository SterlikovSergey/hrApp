<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Employee</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="${contextPath}/resources/css5/font-awesome.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css5/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css5/templatemo-style.css" rel="stylesheet">

    <link href="${contextPath}/resources/vendors/bower_components/datatables/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
      </head>
  <body>  
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
          <div class="square"></div>
          <h1>Employee</h1>
        </header>
        <div class="profile-photo-container">
          <img src="${contextPath}/resources/images3/profile-photo.jpg" alt="Profile Photo" class="img-responsive">
          <div class="profile-photo-overlay"></div>
        </div>      
        <!-- Search box -->
        <form class="templatemo-search-form" role="search">
          <div class="input-group">
              <button type="submit" class="fa fa-search"></button>
              <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">           
          </div>
        </form>
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
        </div>
        <nav class="templatemo-left-nav">          
          <ul>
           <li><a href="/worker" ><i class="fa fa-home fa-fw"></i>Главная</a></li>
            <li><a href="/worker/diagram"><i class="fa fa-bar-chart fa-fw"></i>Диаграммы</a></li>
            <li><a href="#" class="active"><i class="fa fa-database fa-fw"></i>Отчёт по ЗП</a></li>
            <li><a href="/worker/event" ><i class="fa  fa-calendar fa-fw"></i>Календарь</a></li>
            <li><a href="/worker/certificate" ><i class="fa fa-file-text-o fa-fw"></i>Заказ справок</a></li>
            <li><a href="/logout"><i class="fa fa-times-circle fa-fw"></i>Выход</a></li>
          </ul>  
        </nav>
      </div>
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
          <div class="row">
            <nav class="templatemo-top-nav col-lg-12 col-md-12">
              <ul class="text-uppercase">
                <li><a href="" class="active">Сотрудник</a></li>
                              </ul>  
            </nav> 
          </div>
        </div>
        <div class="templatemo-content-container">
                   
           <div class="templatemo-flex-row flex-content-row">
             <div class="col-1">
              <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
                <i class="fa fa-times"></i>
                <div class="panel-heading templatemo-position-relative"><h2 class="text-uppercase">Таблица заработной платы</h2></div>
                <div class="table-responsive">


<!-- Main Content -->
    <div class="page-wrapper">
      <div class="container-fluid">
      
        <!-- Row -->
        <div class="row">
          <div class="col-sm-12">
            <div class="panel panel-default card-view">
              <div class="panel-wrapper collapse in">
                <div class="panel-body">
                  <div class="table-wrap">
                    <div class="table-responsive">
                      <table id="example" class="table table-hover display  pb-30" >
                        
                        <thead>
                          <tr>
                            <th>No.</th>
                            <th>Начисленно</th>
                            <th>Надбавки</th>
                            <th>Удержано</th>
                            <th>Дата начисления</th>
                            <th>Итог</th>

                          </tr>
                        </thead>
                        
                        <tbody>
                        <c:forEach items="${payrollForm}" var = "payroll" >
                          <tr>
                            <td>${payroll.id}</td>
                            <td>${payroll.accrued}</td>
                            <td>${payroll.allowances}</td>
                            <td>${payroll.hold}</td>
                            <td>${payroll.date}</td>
                            <td>${payroll.total}</td>
                          </tr>
                        </c:forEach>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>  
          </div>
        </div>
        <!-- /Row -->
      </div>
      
            
    </div>

                </div>                          
              </div>
            </div>           
          </div> <!-- Second row ends -->
          <footer class="text-right">
            <p>КР &copy; 2021 БГУИР
            | Developer: Стерликов Сергей</p>
          </footer>         
        </div>
      </div>
    </div>
    
<!-- JS table -->


    <!-- JS -->
    <script src="${contextPath}/resources/js3/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
    <script src="${contextPath}/resources/js3/jquery-migrate-1.2.1.min.js"></script> <!--  jQuery Migrate Plugin -->
    <script src="https://www.google.com/jsapi"></script> <!-- Google Chart -->
    <script>
      /* Google Chart 
      -------------------------------------------------------------------*/
      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart); 
      
      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

          // Create the data table.
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Topping');
          data.addColumn('number', 'Slices');
          data.addRows([
            ['Доллор', 3],
            ['Евро', 1],
            ['Российский руб.', 1],
            ['Юань', 1],
            ['Злотый', 2]
          ]);

          // Set chart options
          var options = {'title':'Статистика за последний месяц'};

          // Instantiate and draw our chart, passing in some options.
          var pieChart = new google.visualization.PieChart(document.getElementById('pie_chart_div'));
          pieChart.draw(data, options);

          var barChart = new google.visualization.BarChart(document.getElementById('bar_chart_div'));
          barChart.draw(data, options);
      }

      $(document).ready(function(){
        if($.browser.mozilla) {
          //refresh page on browser resize
          // http://www.sitepoint.com/jquery-refresh-page-browser-resize/
          $(window).bind('resize', function(e)
          {
            if (window.RT) clearTimeout(window.RT);
            window.RT = setTimeout(function()
            {
              this.location.reload(false); /* false to get page from cache */
            }, 200);
          });      
        } else {
          $(window).resize(function(){
            drawChart();
          });  
        }   
      });
      
    </script>
    <script type="text/javascript" src="${contextPath}/resources/js3/templatemo-script.js"></script>      <!-- Templatemo Script -->
<!-- /#wrapper -->
  
  <!-- JavaScript -->
  
    <!-- jQuery -->
    <script src="${contextPath}/resources/vendors/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${contextPath}/resources/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    
  <!-- Data table JavaScript -->
  <script src="${contextPath}/resources/vendors/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
  <script src="${contextPath}/resources/vendors/bower_components/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
  <script src="${contextPath}/resources/vendors/bower_components/datatables.net-buttons/js/buttons.flash.min.js"></script>
  <script src="${contextPath}/resources/vendors/bower_components/jszip/dist/jszip.min.js"></script>
  <script src="${contextPath}/resources/vendors/bower_components/pdfmake/build/pdfmake.min.js"></script>
  <script src="${contextPath}/resources/vendors/bower_components/pdfmake/build/vfs_fonts.js"></script>
  <!-- кнопки -->
  <script src="${contextPath}/resources/vendors/bower_components/datatables.net-buttons/js/buttons.html5.min.js"></script>
  <script src="${contextPath}/resources/vendors/bower_components/datatables.net-buttons/js/buttons.print.min.js"></script>
  <script src="${contextPath}/resources/dist/js/export-table-data.js"></script>
  
  
  
  </body>
</html>