<%-- 
    Document   : ownerAddAdmin
    Created on : Aug 22, 2022, 11:42:38 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container col-6 " style="font-family: 'Time News Ronamce'; background-color: white; ">
    <c:url value="/admin/owner/add" var="action" />

    <form:form  method="post" action="${action}" 
               modelAttribute="ownerAdd" enctype="multipart/form-data"
               >
        <form:errors path="*" element="div" cssClass="alert alert-danger" />

        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input cssStyle="font-size: 15px;" class="form-control" path="name" id="name" placeholder="Input OWNER title" />
            <label style="color:blue;font-weight: bold" for="title">TÊN NHÀ XE</label>
            <form:errors path="name" element="div" cssClass="invalid-feedback" />
        </div>
        
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input cssStyle="font-size: 15px;" class="form-control" path="phone" id="phone" placeholder="Input OWNER phone" />
            <label style="color:blue;font-weight: bold" for="title">SDT NHÀ XE</label>
            <form:errors path="phone" element="div" cssClass="invalid-feedback" />
        </div>

        <div class="form-floating mt-3 mb-3 border-bottom">
            <form:textarea class="form-control height-200" path="description" id="description" placeholder="description" />
            <label style="color:blue;font-weight: bold" for="content">THÔNG TIN THÊM</label>
            <form:errors path="description" element="div" cssClass="invalid-feedback" />
        </div>

        <div class="form-group col-5">
            <label for="userId" class="form-label border-bottom font-weight-bold" style="color:blue">TÀI KHOẢN CHỦ NHÀ XE</label>
            <form:select path="ownerUserId" cssClass="form-select font-weight-bold">
                <c:forEach items="${allUsers}" var="u">
                    <option class="font-weight-bold"  value="${u.userId}">${u.username}</option>
                </c:forEach>
            </form:select>
        </div>
        <div class="form-group">
            <label for="imgId" style="color:blue;font-weight: bold;border-bottom: 1px solid">
                HÌNH ẢNH
            </label>
            <form:input type="file" id="imgId" path="imgFile" 
                        cssClass="form-control border-bottom font-weight-bold" />
            <form:errors path="imgFile" cssClass="text-danger" />
        </div>

        <div class="form-floating">
            <br>
            <input type="submit" value="ADD" class="btn btn-success " />
        </div>
    </form:form>
</div>
