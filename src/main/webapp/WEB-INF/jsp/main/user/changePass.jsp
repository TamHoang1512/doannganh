<%-- 
    Document   : changePass
    Created on : Aug 31, 2022, 10:42:59 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<c:url value="/${mePass.userId}/me/updatepass" var="action" />
<c:if test="${errMsg!=null}" >
    <p class="text text-center alert alert-danger">${errMsg}</p>
</c:if>

<form:form  method="post" action="${action}" 
            modelAttribute="mePass" enctype="multipart/form-data">
    <div class="mb-3">
        <form:hidden path="userId" class="form-control" />

    </div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Mật khẩu hiện tại</label>
        <form:input type="password" path="oldPassword" class="form-control" />

    </div>
    <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Mật khẩu mới</label>
        <form:input type="password" path="password" class="form-control" />
    </div>
    <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Nhập lại mật khẩu mới</label>
        <form:input type="password" path="confirmPassword" class="form-control" />
    </div>

    <button type="submit" class="btn btn-primary">Thay đổi</button>
</form:form>
