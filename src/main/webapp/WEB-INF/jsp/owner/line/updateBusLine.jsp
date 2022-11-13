<%-- 
    Document   : updateBusLine
    Created on : Sep 2, 2022, 10:59:36 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<center><h2>CHỈNH SỬA CHUYẾN XE</h2></center>

<label style="color:blue;font-weight: bold" for="title">TÊN NHÀ XE: ${own.name}</label>

<div class="container col-6 " style="font-family: 'Time News Ronamce'; background-color: white; ">
    <c:url value="/owner/${uid}/line/update/${lId}" var="action" />

    <form:form  method="post" action="${action}" 
                modelAttribute="updateLine" enctype="multipart/form-data"
                >
        <form:errors path="*" element="div" cssClass="alert alert-danger" />
        
        <form:hidden path="id" />
        <form:hidden path="img" />
        <div class="form-group col-5">
            <label for="busLineId" class="form-label border-bottom font-weight-bold" style="color:blue">CHỌN ĐIỂM ĐI</label>
            <form:select cssStyle="width:300px;"   path="fromPos" cssClass="form-select font-weight-bold">
                <c:forEach items="${station}" var="st">
                    <option class="font-weight-bold"  value="${st.id}">${st.name}</option>
                </c:forEach>
            </form:select>
        </div>
        <div class="form-group col-5">
            <label for="busLineId" class="form-label border-bottom font-weight-bold" style="color:blue">CHỌN ĐIỂM ĐẾN</label>
            <form:select cssStyle="width:300px;"   path="toPos" cssClass="form-select font-weight-bold">
                <c:forEach items="${station}" var="st">
                    <option class="font-weight-bold"  value="${st.id}">${st.name}</option>
                </c:forEach>
            </form:select>
        </div>
        
        
        <div class="form-group col-5">
            <label for="direction" class="form-label border-bottom font-weight-bold" style="color:blue">THUỘC NHÀ XE:</label>
            <form:select path="busOwnerId" cssClass="form-select font-weight-bold">
                <option class="font-weight-bold"  value="${own.id}">${own.name}</option>
            </form:select>
        </div>
        
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input 
                class="form-control" 
                path="ticketPrice" 
                type="number" 
                id="ticketPrice" 
                name="ticketPrice" min="1"
                placeholder="Input number" />
            <label for="title">GIÁ VÉ</label>
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