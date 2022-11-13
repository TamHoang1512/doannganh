<%-- 
    Document   : bookHistory
    Created on : Aug 31, 2022, 8:48:29 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<span class="d-flex"><h2 class="col-4" >LỊCH SỬ ĐẶT VÉ</h2></span>


<div class="col-12">
    <div class=" well-small">


        <table class="table table-dark table-striped">
            <thead>
                <tr>
                    <th class="text text-center">Mã số vé</th>
                    <th class="text text-center">Số lượng ghế</th>
                    <th class="text text-center">Chuyến xe</th>
                    <th class="text text-center">Ngày giờ khởi hành</th>
                    <th class="text text-center">Tổng tiền</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${allTicket}" var="o">
                    <tr>
                        <td class="text text-center">${o.id}</td>
                        <td class="text text-center">${o.numberOfSit}</td>
                        <td class="text text-center"><a href="/bus/${o.busId.id}">${o.busId.id} [Xem]</a></td>
                        <td class="text text-center"><fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${o.busId.startAt}" /></td>
                        <td class="text text-center"><fmt:formatNumber type="number" maxFractionDigits="3" value="${o.numberOfSit*o.busId.busLineId.ticketPrice}" /> VND</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</div>
