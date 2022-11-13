<%-- 
    Document   : listBus
    Created on : Sep 1, 2022, 12:39:02 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<c:url value="/find" var="action" />
<form action="${action}">

    <table class="table">
        <thead>
            <tr>
                TÌM CHUYẾN XE
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row">Chọn tuyến xe</th>
                <td><b>TỪ: </b>
                    <select class="form-select" 
                            id="fromPos" placeholder="Chọn điểm" 
                            name="fromPos">
                        <option selected="selected" value="0">-- Tất cả --</option>
                        <c:forEach items="${station}"  var="st">
                            <option value="${st.id}">${st.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><b>ĐẾN: </b>
                    <select class="form-select" 
                            id="toPos" placeholder="Nhập điểm" 
                            name="toPos">
                        <option selected="selected" value="0">-- Tất cả --</option>
                        <c:forEach items="${station}"  var="st">
                            <option value="${st.id}">${st.name}</option>
                        </c:forEach>
                    </select>
                </td>

            </tr>
            <tr>
                <th scope="row">Chọn mức giá</th>
                <td colspan="1">
                    <input  
                           type="number" min="0" 
                           class="form-control  bg-white flexAll float-end border-none outline-none" 
                           id="price" 
                           placeholder="Nhập giá" 
                           name="price"/>
                </td>
            </tr>
        <tr>
            <td></td>
            <td colspan="2">
                <button style="outline: none; border: none;" type="submit" class="w-100 text-center bg-success text-light">Tìm chuyến xe</button>
            </td>
        </tr>
        </tbody>
    </table>


</form>
<hr/>
<c:if test="${bus.size()>0}">
    <h3>THÔNG TIN CÁC CHUYẾN XE</h3>
    <ul class="thumbnails row">
        <c:forEach items="${bus}" var="l">
            <div class="card bg-dark text-white col-4 m-1">
                <a class="zoomTool" href="<c:url value="/bus/${l.id}" />" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
                <a class="flexAll opacity-4" href="<c:url value="/bus/${l.id}" />" ><img src="${l.img}" alt=""></a>
                <div class="card-img-overlay">
                    <div class="caption cntr flexShrink">
                        <p>${l.busLineId.fromPos.name} - ${l.busLineId.toPos.name}</p>
                        <p>Ngày đi:<span class="fw-bold border p-1" > <fmt:formatDate pattern="dd-MM-yyyy" value="${l.startAt}" /> </span></p>
                        <p><strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${l.busLineId.ticketPrice}" /> VND</strong></p>
                        <h4><a class="shopBtn" href="<c:url value="/bus/${l.id}" />" title="view"> XEM </a></h4>
                        <br class="clr">
                    </div>
                </div>
            </div>
        </c:forEach>
    </ul>
</c:if>
<c:if test="${bus.size()==0}"><p class="text text-center text-warning text-center bg-dark">KHÔNG CÓ CHUYẾN XE NHƯ YÊU CẦU</p></c:if>