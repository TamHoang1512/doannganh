<%-- 
    Document   : Base
    Created on : Jul 26, 2022, 9:56:44 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title><tiles:insertAttribute name="title" /></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        
        
        

        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet"/>
        <link id="pagestyle" href="<c:url value='/assets/css/material-dashboard.css' />" rel="stylesheet" />

        
        <link rel="stylesheet"  href="<c:url value='/assets/css/bootstrap-grid.css' />" />
        <link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css' />" />
        <link href="<c:url value="/assets/style.css" />" rel="stylesheet" />

        <link href="<c:url value='/assets/font-awesome/css/font-awesome.css' />" rel="stylesheet">
        <link rel="shortcut icon" href="<c:url value ='/assets/icon.png' />">
    </head>
    <body>
        <tiles:insertAttribute name="header" />
        <div class="container" style="min-height: 700px; margin-top: 70px">
            
            <tiles:insertAttribute name="content"/>
            
        </div>
            <hr/>
        <tiles:insertAttribute name="footer" />
        <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>

        <a href="#" class="gotop"><i class="icon-double-angle-up"></i></a>
        <script src="/assets/js/jquery.js"></script>
        <script src="/assets/js/bootstrap.min.js"></script>
        <script src="/assets/js/jquery.easing-1.3.min.js"></script>
        <script src="/assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
        <script src="/assets/js/shop.js"></script>
    </body>
</html>