<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%--<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>--%>
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
    <link href="${contextPath}/resources/css61/templatemo-style.css" rel="stylesheet">
    

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
            <li><a href="/hr/interviewer"><i class="fa fa-pencil-square-o "></i>Собеседования</a></li>
            <li><a href="/hr/message" ><i class="fa fa-comments fa-fw"></i>Сообщения</a></li>
            <li><a href="#"  class="active"><i class="fa fa-th-list"></i>Штатное расписание </a></li>
            <li><a href="/hr/report"><i class="fa fa-database fa-fw"></i>Отчёт по ЗП</a></li>
            <li><a href="/hr/certificate" ><i class="fa fa-file-text-o fa-fw"></i>Заказ справок</a></li>
            <li><a href="/logout"><i class="fa fa-times-circle fa-fw"></i>Выход</a></li>
          </ul>  
        </nav>
      </div>
           <!-- Main content --> 
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
              <p>Здесь вы можете скачать штатное расписание в нескольких форматах и за разные периоды времени, а так же вы можете выбрать отдел банковского учереждения и узанть ео штатное расписание</p>
              <p>Если вы ознакомились со справочной информацией и она вам больше не нужна, нажните на крестик в правом верхнем углу</p>             
            </div>
            </div>
          
          </div> <!-- Second row ends -->
          <form name="form" method="post" action="/download/fullReport.xlsx">
            <div class="col-1">
              <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
                <i class="fa fa-times"></i>

                <div class="panel-heading templatemo-position-relative"><h2 class="text-uppercase">Просмотр штатного расписания</h2></div>
<div class="staffing">
    <select name="department">
  <option label="Выберите нужный отдел" value="1" selected>Выберите нужный отдел</option>
       <option value="Управление розничного бизнеса">Управление розничного бизнеса</option>
       <option value="Управление корпоративного бизнеса">Управление корпоративного бизнеса</option>
       <option value="Управление безопасности и контроля">Управление безопасности и контроля</option>
       <option value="Управление межбанковских коммуникаций">Управление межбанковских коммуникаций</option>
       <option value="Бухгалтерия">Бухгалтерия</option>
       <option value="Управление операций на фондовом рынке">Управление операций на фондовом рынке</option>
       <option value="Юридическое управление">Юридическое управление</option>
       <option value="Управление развития">Управление развития</option>
       <option value="Пиар (PR) и реклама">Пиар (PR) и реклама</option>
       <option value="Управление информационных технологий">Управление информационных технологий</option>
</select>

 <select id = "month" name="month">
  <option label="Выберите нужный месяц" value="1" selected>Выберите нужный месяц</option>
  <option value="1">Январь</option>
  <option value="2">Февраль</option>
  <option value="3">Март</option>
     <option value="4">Апрель</option>
  <option value="5">Май</option>
  <option value="6">Июнь</option>
   <option value="7">Июль</option>
  <option value="8">Август</option>
  <option value="9">Сентябрь</option>
   <option value="10">Октябрь</option>
  <option value="11">Ноябрь</option>
  <option value="12">Декабрь</option>
</select>

<%-- <select>--%>
<%--  <option label="Выберите нужный формат расписания" value="1" selected>Выберите нужный формат расписания</option>--%>
<%--  <option>.xls</option>--%>
<%--  <option>.txt</option>--%>
<%--</select>--%>
    <a href="/download/report.xlsx" class="login100-form-btn">Получить расписание</a>
<%--    <a href="/download/fullReport.xlsx" class="login100-form-btn">Общее расписание</a>--%>
<%--   <button type="submit" class="login100-form-btn">Получить расписание</button>--%>
   <button type="submit" class="login100-form-btn">Общее расписание</button>
</div>
    </div>   
<div class="col-1">
              <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
                <i class="fa fa-times"></i>

                <div class="panel-heading templatemo-position-relative"><h2 class="text-uppercase">Пример штатного расписания</h2></div>
<div class="profile-photo-container">
          <img src="${contextPath}/resources/images3/staffing_table.png" alt="Profile Photo" class="img-responsive" style="
    margin-left: 250px;
">  
          
        </div>  

                </div>
    </div>
          <footer class="text-right">
            <p>КР &copy; 2021 БГУИР
            | Developer: Стерликов Сергей</p>
          </footer>         
        </div>
          </form>
      </div>
    </div>
    <!-- JS -->
  
   
  </body>
</html>