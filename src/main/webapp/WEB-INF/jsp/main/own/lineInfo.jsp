<%-- 
    Document   : lineInfo
    Created on : Sep 1, 2022, 1:37:33 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>


<c:if test="${bus.size()==0}"><h3><i>Chưa có chuyến xe phù hợp</i></h3></c:if>
<c:if test="${bus.size()!=0}">
    <h3 class="bg-white text-center p-2 font-weight-bold">DANH SÁCH CHUYẾN XE THEO TUYẾN</h3>
<ul class="thumbnails row">
    <c:forEach items="${bus}" var="l">
        <li class="col-4 d-flex">
            <div class="thumbnail flexEqual">

                <a class="zoomTool" href="<c:url value="/bus/${l.id}" />" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
                <a class="flexAll" href="product_details.html"><img src="${l.img}" alt=""></a>
                <div class="caption cntr flexShrink">
                    <p class="font-weight-bold">${l.busLineId.fromPos.name} - ${l.busLineId.toPos.name}</p>
                    <p>Ngày đi:<span class="fw-bold border p-1" > <fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${l.startAt}" /> </span></p>
                    <p><strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${l.busLineId.ticketPrice}" /> VND</strong></p>
                    <h4><a class="shopBtn" href="<c:url value="/bus/${l.id}" />" title="view"> XEM </a></h4>
                    <br class="clr">
                </div>
            </div>
        </li>
    </c:forEach>
</ul>
</c:if>