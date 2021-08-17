<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>HR</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
   
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="${contextPath}/resources/css6/font-awesome.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css6/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css6/templatemo-style.css" rel="stylesheet">


    <script src="${contextPath}/resources/js/js-fluid-meter.js"></script>
    

  </head>
  <body>  
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
          <div class="square"></div>
          <h1>HR</h1>
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
            <li><a href="/hr" ><i class="fa fa-home fa-fw"></i>Главная</a></li>
            <li><a href="/hr/diagram"><i class="fa fa-bar-chart fa-fw"></i>Диаграммы</a></li>
              <li><a href="/hr/statistics"><i class="fa fa-area-chart"></i>Статистика</a></li>
            <li><a href="/hr/profile" ><i class="fa  fa-list-alt"></i>Досье</a></li>
             <li><a href="/hr/event" ><i class="fa  fa-calendar fa-fw"></i>Календарь</a></li>
            <li><a href="/hr/interviewer" ><i class="fa fa-pencil-square-o "></i>Собеседования</a></li>
            <li><a href="/hr/message"><i class="fa fa-comments fa-fw"></i>Сообщения</a></li>
            <li><a href="/hr/staffingTable"><i class="fa fa-th-list"></i>Штатное расписание </a></li>
            <li><a  href="/hr/report"><i class="fa fa-database fa-fw"></i>Отчёт по ЗП</a></li>
            <li><a href="#" class="active"><i class="fa fa-file-text-o fa-fw"></i>Заказ справок</a></li>
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
                <li><a href="" class="active">HR</a></li>
                              </ul>  
            </nav> 
          </div>
        </div>
        <div class="templatemo-content-container">
          <div class="templatemo-flex-row flex-content-row">
            <div class="templatemo-content-widget white-bg col-2">
              <i class="fa fa-times"></i>
              <div class="square"></div>
              <h2 class="templatemo-inline-block">Инструкция для пользователя</h2><hr>
              <p>Добро пожаловать в ваш аккаует сотрудника. Данный аккаунт предоставляет вам возможность контролировать важные экономические показатели в режиме реального времени, а так же просматривать статистику за определённый период.</p><p>
              В аккаунте содержится ваша личная информация. Так же здесь размещается календарь событий, который включает в себя рассписание встреч, больничных и отпусков. При помощи вашей страничке вы можете просматривать свои расчётные листы и заказывать нужные вам документы онлайн.</p>
              <p>Если вы ознакомились со справочной информацией и она вам больше не нужна, нажните на крестик в правом верхнем углу</p>             
            </div>

            <div class="templatemo-content-widget white-bg col-2">
              <i class="fa fa-times"></i>
                      <h2 class="templatemo-inline-block">Статус выполнения вашей справки</h2><hr>
              <div class="container" style=" width: 130px;margin-left: 150px;">
                <div class="row">
                  <div class="col text-center">
                      <div id="fluid-meter" class="mx-auto"></div>
                        <select id="percentage-1"  class="form-control">
                            <c:forEach items="${certificateList}" var = "certificate" >
                                <option value="${certificate.percent}">${certificate.type} - <fmt:formatDate value="${certificate.orderDate}" pattern="dd-MM-yyyy" /></option>
                            </c:forEach>
                        </select>
                        <button type="button" id="submit-percentage-1" class="login100-form-btn" style="margin-left: 80px;">Узнать</button>
                  </div>
                </div>
              </div>
            </div>
          </div>


                    <div class="templatemo-flex-row flex-content-row">
            
            <div class="col-1">
              <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
                
                <div class="panel-heading templatemo-position-relative"><h2 class="text-uppercase">Таблица заказа справок</h2></div>
                 
                    <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
              <table class="table table-striped table-bordered templatemo-user-table">
                <thead>
                  <tr>
                    <td><a href="" class="white-text templatemo-sort-by"># <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Сотрудник <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Справки<span class="caret"></span></a></td>
                    <td>Статус справки( в % )</td>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${CertificateForm}" var = "certificate" >
                    <form method="post" action="/hr/certificate/percent">
                  <tr>
                    <td><input type="number" name="id" style="outline: none; border: none; background: #f9f9f9;" value="${certificate.id}" readonly> </td>
                    <td>${certificate.employee.name} ${certificate.employee.surname}</td>
                    <td>${certificate.type}</td>
                    <td>
<%--                        <form:input type="number" path="percent" id="percentage" value="${certificate.percent}" autofocus="true"></form:input>--%>
                        <input name="percent" id="percentage" type="number" value="${certificate.percent}">
                     <button id="submit-percentage">Уведомить</button></td>
                  </tr>
                    </form>
                </c:forEach>
         </tbody>
              </table>
<%--                для анимации--%>
<%--                <input type="text" id="percentage-1" type="number" value="${certificate.percent}">--%>
<%--                <button id="submit-percentage-1">Уведомить</button>--%>
            </div>                          
          </div> 

            
              </div>
            </div>           
          </div> 

         
          <div class="templatemo-flex-row flex-content-row">
            
            <div class="col-1">
              <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
                <i class="fa fa-times"></i>
                <div class="panel-heading templatemo-position-relative"><h2 class="text-uppercase">Заказ справок онлайн</h2></div>
                <div class="table-responsive">
                    <div class="pricingTable">
  
  <ul class="pricingTable-firstTable">
      <li class="pricingTable-firstTable_table">
          <form method="post">
              <h1 id = "typeCertificate" class="pricingTable-firstTable_table__header">Справка по месту работы</h1>
              <p class="pricingTable-firstTable_table__pricing"><span>ожидание</span><span>1-2</span><span>недели</span></p>
              <ul class="pricingTable-firstTable_table__options">
                  <li>${name}</li>
                  <li>${date}</li>
                  <li><input type="text" name="typeCertificate" style="outline: none; border: none; background: #f9f9f9;" value="по месту работы" readonly> </li>
              </ul>
              <div><button type="submit" class="pricingTable-firstTable_table__getstart" style="width: 250px">Заказать</button></div>
          </form>
      </li>
      <li class="pricingTable-firstTable_table">
          <form method="post">
              <h1  name="в посольство" class="pricingTable-firstTable_table__header">Справка в посольство</h1>
              <p class="pricingTable-firstTable_table__pricing"><span>ожидание</span><span>2-3</span><span>дня</span></p>
              <ul class="pricingTable-firstTable_table__options">
                  <li>${name}</li>
                  <li>${date}</li>
                  <li><input type="text" style="outline: none; border: none; background: #f9f9f9;" name="typeCertificate" value="в посольство" readonly> </li>
              </ul>
              <div><button type="submit" class="pricingTable-firstTable_table__getstart" style="width: 250px">Заказать</button></div>
          </form>
      </li>
      <li class="pricingTable-firstTable_table">
          <form method="post">
              <h1 name="на получения кредита" class="pricingTable-firstTable_table__header">Справка на получения кредита</h1>
              <p class="pricingTable-firstTable_table__pricing"><span>ожидание</span><span>1-2</span><span>месяца</span></p>
              <ul class="pricingTable-firstTable_table__options">
                  <li>${name}</li>
                  <li>${date}</li>
                  <li><input type="text" name="typeCertificate" style="outline: none; border: none; background: #f9f9f9;"  value="на получения кредита" readonly> </li>
              </ul>
              <div><button type="submit" class="pricingTable-firstTable_table__getstart" style="width: 250px">Заказать</button></div>
          </form>
      </li>
      <li class="pricingTable-firstTable_table">
          <form method="post">
              <h1 name="для получения льгот" class="pricingTable-firstTable_table__header">Справка для получения льгот</h1>
              <p class="pricingTable-firstTable_table__pricing"><span>ожидание</span><span>2-3</span><span>дня</span></p>
              <ul class="pricingTable-firstTable_table__options">
                  <li>${name}</li>
                  <li>${date}</li>
                  <li><input type="text" name="typeCertificate" style="outline: none; border: none; background: #f9f9f9;" value="для получения льгот" readonly> </li>
              </ul>
              <div><button type="submit" class="pricingTable-firstTable_table__getstart" style="width: 250px">Заказать</button></div>
          </form>
      </li>
  </ul>
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
    
    <!-- JS -->
    <script src="${contextPath}/resources/js3/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
    <script src="${contextPath}/resources/js3/jquery-migrate-1.2.1.min.js"></script> <!--  jQuery Migrate Plugin -->
    <script src="https://www.google.com/jsapi"></script> <!-- Google Chart -->
    
    <script type="text/javascript" src="${contextPath}/resources/js3/templatemo-script.js"></script>      <!-- Templatemo Script -->
 <script>
    var fm = new FluidMeter();
    fm.init({
      targetContainer: document.getElementById("fluid-meter"),
      fillPercentage: 15,
      options: {
        fontFamily: "Raleway",
        drawPercentageSign: false,
        drawBubbles: true,
        size: 300,
        borderWidth: 19,
        backgroundColor: "#e2e2e2",
        foregroundColor: "#fafafa",
        foregroundFluidLayer: {
          fillStyle: "green",
          angularSpeed: 100,
          maxAmplitude: 12,
          frequency: 30,
          horizontalSpeed: -150
        },
        backgroundFluidLayer: {
          fillStyle: "#4fab40",
          angularSpeed: 100,
          maxAmplitude: 9,
          frequency: 30,
          horizontalSpeed: 150
        }
      }
    });

   

    window.onload = function () {
      document.getElementById("submit-percentage-1").onclick = function () {
        fm.setPercentage(Number(document.getElementById('percentage-1').value))
      }

    };
  </script>
  </body>
</html>