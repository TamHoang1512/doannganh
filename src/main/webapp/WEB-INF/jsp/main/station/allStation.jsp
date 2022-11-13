<%-- 
    Document   : allStation
    Created on : Sep 3, 2022, 9:24:19 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!doctype html>



<c:forEach items="${station}" var="t">

    <div class="card mb-3">
        <div class="row g-0"> 

            <div class="col-md-4">
                <img src="${t.img}" class="img-fluid rounded-start" alt="hinh anh">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h2 class="text-uppercase bg-cover bg-dark text-center text-white p-2">${t.name}</h2>
                    <hr/>
                    <p class="card-title ">
                        <u>Địa chỉ</u>  <span class="p-1 bg-gradient text-primary">
                            ${t.location} </span>

                    </p>
                    <hr/>
                    <p class="d-flex justify-content-between"> 
                        <c:if test="${t.isBlock==0}" ><span class="border p-2  fw-bold text-white bg-success">Đang Hoạt Động</span></c:if>
                        <c:if test="${t.isBlock==1}" ><span class="border p-2 text-white bg-danger  fw-bold">Tạm Ngưng Hoạt Động</span></c:if> 
                        <a  href="<c:url value="/busstation/${t.id}" />">
                            <span class="border p-1  fw-bold text-white bg-gradient-faded-info btn-mini">XEM CHI TIẾT</span>
                        </a>
                    </p>

                        
                </div>
            </div>

        </div>
    </div>

</c:forEach>