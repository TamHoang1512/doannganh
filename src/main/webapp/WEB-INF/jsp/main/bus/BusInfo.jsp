<%-- 
    Document   : BusInfo
    Created on : Jul 28, 2022, 5:28:48 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!doctype html>


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
                <img src="${bus.img}" class="img-fluid rounded-start" alt="hinh anh">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title bg-dark p-1 text-white-50">Thông tin chuyến xe</h5>
                    <hr/>
                    <p class="card-title ">
                        <c:if test="${bus.direction==1}">
                            Điểm đi  <span class="border p-1 bg-gradient text-uppercase bg-dark text-white"> ${bus.busLineId.fromPos.name} </span>  -
                            Điểm đến <span class="border p-1 bg-gradient text-uppercase bg-dark text-white" > ${bus.busLineId.toPos.name} </span> 
                        </c:if>

                        <c:if test="${bus.direction==0}">
                            Đi từ  <span class="border p-1 text-uppercase bg-gradient bg-dark text-light" > ${bus.busLineId.toPos.name} </span>-
                            Đến    <span class="border p-1 text-uppercase bg-gradient bg-dark text-white"> ${bus.busLineId.fromPos.name} </span>
                        </c:if>
                    </p>
                    <hr/>
                    <p>Thời gian khởi hành <span class="fw-bold border p-1" > <fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${bus.startAt}" /> </span></p>
                    <hr/>
                    <p>
                        Giá vé : <span class="border p-2 fs-4 fw-bold">
                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${bus.busLineId.ticketPrice}" /> VND</span>
                    </p>
                    <p>Ghế còn trống <span class="border p-1 fw-bold">${emptySit}/${bus.capacitySit} </span></p>
                    <hr/>
                    <a href="/bus/${bus.id}/book" class="btn btn-success col-6 text-white font-weight-bold">ĐẶT VÉ</a>
                </div>
            </div>
        </div>
    </div>

    <c:if test="${offerBus.size()>0}">
        <h3>Đề xuất khác</h3>
        <ul class="thumbnails row">
            <c:forEach items="${offerBus}" var="l" >
                <c:if test="${l.id!=bus.id}">
                    <div class="card bg-dark text-white col-4 m-1">
                    <a class="zoomTool" href="<c:url value="/bus/${l.id}" />" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
                    <a class="flexAll opacity-4" href="<c:url value="/bus/${l.id}" />" ><img src="${l.img}" alt=""></a>
                    <div class="card-img-overlay">
                        <div class="caption cntr">
                            <p class="shadow-sm fw-bold">${l.busLineId.fromPos.name} - ${l.busLineId.toPos.name}</p>
                            <p  class="fw-bold">Ngày đi <span class="fw-bold border p-1" > <fmt:formatDate pattern="dd-MM-yyyy" value="${l.startAt}" /> </span></p>
                            <p  class="fw-bold"><strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${l.busLineId.ticketPrice}" /> VND</strong></p>
                            <h4><a class="shopBtn" href="<c:url value="/bus/${l.id}" />" title="view"> XEM </a></h4>
                            <br class="clr">
                            <h2 class="text-uppercase fw-bold border text-light">${l.busLineId.busOwnerId.name}</h2>
                        </div>
                    </div>
                </div>
                </c:if>
            </c:forEach>
        </ul>
    </c:if>
</div>


