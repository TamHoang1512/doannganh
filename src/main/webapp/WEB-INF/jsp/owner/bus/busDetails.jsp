<%-- 
    Document   : busDetails
    Created on : Aug 18, 2022, 9:26:33 AM
    Author     : 84344
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


    <h2 class="text-uppercase bg-cover bg-dark text-center text-white p-2">${bus.busLineId.busOwnerId.name}</h2>

<c:if test="${errMsg!=null}" >
    <p class="text text-center alert alert-danger">${errMsg}</p>
</c:if>
<c:if test="${okMsg!=null}" >
    <p class="text text-center alert alert-success">${okMsg}</p>
</c:if>
<div>
    <div class="card mb-3">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="${busId.img}" class="img-fluid rounded-start" alt="hinh anh">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title bg-dark p-1 text-white-50">Thông tin chuyến xe</h5>
                    <hr/>
                    <p class="card-title ">
                        <c:if test="${busId.direction==1}">
                            Điểm đi  <span class="border p-1 bg-gradient text-uppercase bg-dark text-white"> ${busId.busLineId.fromPos.name} </span>  -
                            Điểm đến <span class="border p-1 bg-gradient text-uppercase bg-dark text-white" > ${busId.busLineId.toPos.name} </span> 
                        </c:if>

                        <c:if test="${bus.direction==0}">
                            Đi từ  <span class="border p-1 text-uppercase bg-gradient bg-dark text-light" > ${busId.busLineId.toPos.name} </span>-
                            Đến    <span class="border p-1 text-uppercase bg-gradient bg-dark text-white"> ${busId.busLineId.fromPos.name} </span>
                        </c:if>
                    </p>
                    <hr/>
                    <p>Thời gian khởi hành <span class="fw-bold border p-1" > <fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${busId.startAt}" /> </span></p>
                    <hr/>
                    <p>
                        Giá vé : <span class="border p-2 fs-4 fw-bold">
                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${busId.busLineId.ticketPrice}" /> VND</span>
                    </p>
                    <p>Ghế còn trống <span class="border p-1 fw-bold">${emptySit}/${busId.capacitySit} </span></p>
                    <hr/>

                    <c:if test="${now==true}" >
                        <c:if test="${emptySit!=0}" >
                            <a href="/bus/${busId.id}/book" class="btn btn-success col-6 text-white font-weight-bold">ĐẶT VÉ</a>
                        </c:if></c:if>

                </div>
            </div>
        </div>
    </div>
</div>
                    <a href="/owner/${uId}/bus" class="btn btn-warning">Back</a>