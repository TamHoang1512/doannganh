<%-- 
    Document   : updateFreight
    Created on : Sep 2, 2022, 10:42:14 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container col-6 " style="font-family: 'Time News Ronamce'; background-color: white; ">


    <c:url value="/owner/${uId}/freight/update/${frId}" var="action" />


    <form:form  method="post" action="${action}" 
                modelAttribute="freightUpdate" enctype="multipart/form-data"
                >
        <form:errors path="*" element="div" cssClass="alert alert-danger" />
        
        <form:hidden path="id"/>
        <form:hidden path="complete"/>
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input cssStyle="font-size: 20px;" class="form-control" path="sendName" id="sendName" />
            <label for="sendName" style="font-size: 20px" class="font-weight-bold text-uppercase">Tên người gửi</label>
        </div>
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input type="number" min="1" cssStyle="font-size: 20px;" class="form-control" path="sendPhone" id="sendPhone" />
            <label for="sendPhone" style="font-size: 20px" class="font-weight-bold text-uppercase">Số điện thoại</label>
        </div>
            
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input type="email" cssStyle="font-size: 20px;" class="form-control" path="sendEmail" id="sendEmail" />
            <label for="sendEmail" style="font-size: 20px" class="font-weight-bold text-uppercase">Email người gửi</label>
        </div>
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input 
                class="form-control" 
                path="sendDate" 
                type="datetime-local" 
                id="sendDate" 
                name="sendDate"
                placeholder="Input number" />
            <label for="sendDate">NGÀY GIỜ GỬI</label>
        </div>
            
            
            
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input cssStyle="font-size: 20px;" class="form-control" path="receiveName" id="receiveName" />
            <label for="receiveName" style="font-size: 20px" class="font-weight-bold text-uppercase">Tên người nhận</label>
        </div>
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input type="number" min="1" cssStyle="font-size: 20px;" class="form-control" path="receivePhone" id="receivePhone" />
            <label for="receivePhone" style="font-size: 20px" class="font-weight-bold text-uppercase">Số điện thoại người nhận</label>
        </div>
            
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input type="email" cssStyle="font-size: 20px;" class="form-control" path="receiveEmail" id="receiveEmail" />
            <label for="receiveEmail" style="font-size: 20px" class="font-weight-bold text-uppercase">Email người nhận</label>
        </div>
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input 
                class="form-control" 
                path="receiveDate" 
                type="datetime-local" 
                id="receiveDate" 
                name="receiveDate"
                placeholder="Input number" />
            <label for="receiveDate">NGÀY GIỜ NHẬN</label>
        </div>
            
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input 
                class="form-control" 
                path="totalPrice" 
                type="number" 
                id="totalPrice" 
                name="totalPrice" min="1"
                placeholder="Input number" />
            <label for="title">GIÁ: </label>
        </div> 

        <div class="form-floating mt-3 mb-3 border-bottom">
            <form:textarea class="form-control height-200" path="info" id="info" placeholder="info" />
            <label for="info" style="font-size: 20px" class="font-weight-bold text-uppercase">thông tin thêm</label>
        </div>

        <div class="form-group col-5">
            <label for="ownerId" 
                   class="form-label border-bottom text-uppercase font-weight-bold" 
                   style="font-size: 20px">Nhà xe
            </label>
            <form:select path="ownerId" cssClass="form-select font-weight-bold">
                    <option value="${own.id}">${own.name}</option>
            </form:select>
        </div>


        <div class="form-floating">
            <br>
            <input type="submit" value="SAVE" class="btn btn-danger " />
        </div>
    </form:form>
</div>

