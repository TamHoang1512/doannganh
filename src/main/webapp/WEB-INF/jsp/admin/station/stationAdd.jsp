<%-- 
    Document   : stationAdd
    Created on : Aug 22, 2022, 12:39:02 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container col-6 " style="font-family: 'Time News Ronamce'; background-color: white; ">
    <c:url value="/admin/station/add" var="action" />

    <form:form  method="post" action="${action}" 
               modelAttribute="station" enctype="multipart/form-data"
               >
        <form:errors path="*" element="div" cssClass="alert alert-danger" />

        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input class="form-control" path="name" id="name" placeholder="Input Name" />
            <label for="title">Name of Station</label>
            <form:errors path="name" element="div" cssClass="invalid-feedback" />
        </div>

        <div class="form-floating mt-3 mb-3 border-bottom">
            <form:textarea class="form-control height-100" path="location" id="location" placeholder="Address" />
            <label for="location">Address</label>
            <form:errors path="location" element="div" cssClass="invalid-feedback" />
        </div>

        <div class="form-group col-5">
            <label for="userId" class="form-label border-bottom">Nguoi Dang</label>
            <form:select path="adminId" class="form-select">
                <c:forEach items="${allUsers}" var="u">
                    <option value="${u.userId}">${u.username}</option>
                </c:forEach>
            </form:select>
        </div>
        <div class="form-group">
            <label for="imgId border-bottom">
                <!--<spring:message code="product.image" />-->
                Picture
            </label>
            <form:input class="border-bottom" type="file" id="imgId" path="imgFile" 
                        cssClass="form-control" />
            <form:errors path="imgFile" cssClass="text-danger" />
        </div>

        <div class="form-floating">
            <br>
            <input type="submit" value="ADD" class="btn btn-danger " />
        </div>
    </form:form>
</div>
