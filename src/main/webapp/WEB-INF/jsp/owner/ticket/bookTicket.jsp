<%-- 
    Document   : bookTicket
    Created on : Aug 27, 2022, 2:57:45 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container col-6 " style="font-family: 'Time News Ronamce'; background-color: white; ">
    <c:url value="/owner/${id}/bus/${busId.id}/ticket/book" var="action" />

    <form:form  method="post" action="${action}" 
               modelAttribute="ticket" enctype="multipart/form-data"
               >
        <form:errors path="*" element="div" cssClass="alert alert-danger" />

        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input 
                class="form-control" 
                path="numberOfSit" 
                type="number" 
                id="numberOfSit" 
                name="numberOfSit" min="1"
                placeholder="Input number" />
            <label for="title">SỐ GHẾ MUỐN ĐẶT</label>
            <form:errors path="numberOfSit" element="div" cssClass="invalid-feedback" />
        </div>
        <div class=" mb-3 mt-3 border-bottom">
            <input 
                name="isPurchased" 
                value="0"
                type="checkbox" 
                checked="checked"
                />
            <label class="form-label border-bottom">THANH TOÁN TẠI QUẦY</label>
        </div>

        <div class="form-group col-5">
            <label for="userId" class="form-label border-bottom">NGƯỜI ĐẶT</label>
            <form:select path="userId" class="form-select">
                <option value="${u.userId}">${u.username}</option>
            </form:select>
        </div>
        <div class="form-group col-5">
            <label for="userId" class="form-label border-bottom">NGÀY KHỞI HÀNH</label>
            <form:select path="busId" class="form-select">
                <option value="${busId.id}">${busId.startAt}</option>
            </form:select>
        </div>


        <div class="form-floating">
            <br>
            <input type="submit" value="ADD" class="btn btn-danger " />
        </div>
    </form:form>
</div>