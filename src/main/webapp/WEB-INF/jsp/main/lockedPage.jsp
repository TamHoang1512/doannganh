<%-- 
    Document   : lockOwnerPage
    Created on : Aug 28, 2022, 9:43:29 PM
    Author     : 84344
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<br><br><br><br><br><br>
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
  <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
  </symbol>
</svg>
<center>
<div class="alert alert-danger d-flex align-items-center col-8 text-center" role="alert">
  <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
  <div class="text-uppercase font-weight-bold text-center"  style="color:white">
    Nhà xe chưa được phép hoạt động hoặc đã bị khóa.
  </div>
</div>
    <div><a class="btn btn-outline-warning" href="<c:url value="/" />">Về Trang Chủ</a></div>
</center>