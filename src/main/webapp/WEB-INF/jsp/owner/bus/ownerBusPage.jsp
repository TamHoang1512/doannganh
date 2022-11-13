<%-- 
    Document   : ownerBusPage
    Created on : Aug 28, 2022, 11:44:25 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>




<!--<p>${busAll.size()}</p>-->
<a style="border: 1px solid gray" 
   class="btn btn-outline-success" href="<c:url value="/owner/${uId2}/bus/add" />" 
   title="View more">THÊM CHUYẾN XE
    <span class="icon-plus"></span>
</a>
<ul class="thumbnails row">
    <c:forEach items="${busAll}" var="l">
        <li class="col-12">
            <div class="thumbnail">

                <div style="margin: 3px" class="caption row lh-base">
                    <c:if test="${l.direction==0}">
                        <p class="col-4"> Từ :${l.busLineId.fromPos.name} <br> Đến :${l.busLineId.toPos.name}</p>
                    </c:if>
                    <c:if test="${l.direction==1}">
                    <p class="col-4"> Từ : ${l.busLineId.toPos.name} <br> Đến : ${l.busLineId.fromPos.name}</p>
                    </c:if>
                    <p class="col-4 lh-base">Ngày giờ đi: ${l.startAt}</p>
                    <p class="col-2">Giá vé:<strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${l.busLineId.ticketPrice}" /> </strong></p>
                    <p class="col-2"><a class="shopBtn" href="<c:url value="/owner/${uId2}/bus/${l.id}" />" title="view">VIEW</a>
                    <a class="shopBtn" href="<c:url value="/owner/${uId2}/bus/update/${l.id}" />" title="view">EDIT </a>
                    </p>
                    
                    <br class="clr">
                </div>
            </div>
        </li>
    </c:forEach>
</ul>
