<%-- 
    Document   : newsUpdateAdmin
    Created on : Sep 3, 2022, 2:19:36 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container col-6 " style="font-family: 'Time News Ronamce'; background-color: white; ">


    <c:set var="roleUser" value="${pageContext.session.getAttribute('currentUser').roleId.roleName}" />
    <c:set var="uId" value="${pageContext.session.getAttribute('currentUser').userId}" />



    <c:url value="/admin/news/update/${nId}" var="action" />



    <form:form  method="post" action="${action}" 
                modelAttribute="tintucUpdate" enctype="multipart/form-data"
                >
        <form:errors path="*" element="div" cssClass="alert alert-danger" />
        
        <form:hidden path="id" />
        <form:hidden path="img" />
        
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input cssStyle="font-size: 20px;" class="form-control" path="title" id="title" placeholder="Input news title" />
            <label for="title" style="font-size: 20px" class="font-weight-bold text-uppercase">Tiêu đề tin tức</label>
            <form:errors path="title" element="div" cssClass="invalid-feedback" />
        </div>

        <div class="form-floating mt-3 mb-3 border-bottom">
            <form:textarea class="form-control height-200" path="content" id="content" placeholder="Content" />
            <label for="content" style="font-size: 20px" class="font-weight-bold text-uppercase">Nội dung tin</label>
            <form:errors path="content" element="div" cssClass="invalid-feedback" />
        </div>

        <div class="form-group col-5">
            <label for="userId" class="form-label border-bottom text-uppercase font-weight-bold" style="font-size: 20px">Người đăng tải</label>
            <form:select path="userId" cssClass="form-select font-weight-bold">
                <sec:authorize access="isAuthenticated()">
                    <option value="${pageContext.session.getAttribute("currentUser").userId}">
                        <sec:authentication property="principal.username" />
                    </option>

                </sec:authorize>
            </form:select>
        </div>
        <div class="form-group">
            <label for="imgId border-bottom" style="font-size: 20px" class="font-weight-bold text-uppercase">
                <!--<spring:message code="product.image" />-->
                Hình ảnh đính kèm
            </label>
            <form:input class="border-bottom" type="file" id="imgId" path="imgFile" 
                        cssClass="form-control" />
            <form:errors path="imgFile" cssClass="text-danger" />
        </div>

        <div class="form-floating">
            <br>
            <input type="submit" value="SAVE" class="btn btn-danger " />
        </div>
    </form:form>
</div>

