<%-- 
    Document   : updateBus
    Created on : Sep 2, 2022, 10:55:17 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<center><h2>CẬP NHẬT CHUYẾN XE</h2></center>

<label style="color:blue;font-weight: bold" for="title">TÊN NHÀ XE: ${own.name}</label>

<div class="container col-6 " style="font-family: 'Time News Ronamce'; background-color: white; ">
    <c:url value="/owner/${uid}/bus/update/${bId}" var="action" />

    <form:form  method="post" action="${action}" 
                modelAttribute="updateBus" enctype="multipart/form-data"
                >
        <form:errors path="*" element="div" cssClass="alert alert-danger" />
        

        <form:hidden path="id" />
        <form:hidden path="img" />
        
        <div class="form-group col-5">
            <label for="busLineId" class="form-label border-bottom font-weight-bold" style="color:blue">CHỌN TUYẾN XE</label>
            <form:select cssStyle="width:300px;"   path="busLineId" cssClass="form-select font-weight-bold">
                <c:forEach items="${busLine}" var="l">
                    <option class="font-weight-bold"  value="${l.id}">${l.fromPos.name}-${l.toPos.name}</option>
                </c:forEach>
            </form:select>
        </div>
        <div class="form-group col-5">
            <label for="direction" class="form-label border-bottom font-weight-bold" style="color:blue">HƯỚNG ĐI:</label>
            <form:select path="direction" cssClass="form-select font-weight-bold">
                <option class="font-weight-bold"  value="1">ĐI</option>
                <option class="font-weight-bold"  value="0">VỀ</option>
            </form:select>
        </div>
        
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input 
                class="form-control" 
                path="capacitySit" 
                type="number" 
                id="capacitySit" 
                name="capacitySit" min="1"
                placeholder="Input number" />
            <label for="title">SỐ LƯỢNG GHẾ</label>
        </div>
        
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input 
                class="form-control" 
                path="startAt" 
                type="datetime-local" 
                id="startAt" 
                name="startAt"
                placeholder="Input number" />
            <label for="title">NGÀY GIỜ KHỞI HÀNH</label>
        </div>
        
        <div class="form-group">
            <label for="imgId" style="color:blue;font-weight: bold;border-bottom: 1px solid">
                HÌNH ẢNH
            </label>
            <form:input type="file" id="imgId" path="imgFile" 
                        cssClass="form-control border-bottom font-weight-bold" />
        </div>
        
        <div class="form-floating mt-3 mb-3 border-bottom">
            <form:textarea class="form-control height-200" path="description" id="description" placeholder="description" />
            <label style="color:blue;font-weight: bold" for="content">THÔNG TIN THÊM</label>
        </div>

        <div class="form-floating">
            <br>
            <input type="submit" value="SAVE" class="btn btn-success " />
        </div>
    </form:form>
</div>