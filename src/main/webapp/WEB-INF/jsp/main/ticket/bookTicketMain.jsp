<%-- Document : bookTicketMain Created on : Aug 31, 2022, 8:26:53 PM Author : 84344 --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container col-6 "
     style="font-family: 'Time News Ronamce'; background-color: white; ">
    <c:url value="/bus/${bus.id}/book" var="action" />
    <c:if test="${errMsg!=null}">
        <p class="text text-center alert alert-danger">${errMsg}</p>
    </c:if>
    <form:form method="post" action="${action}" modelAttribute="ticketBook"
               enctype="multipart/form-data">
        <form:errors path="*" element="div" cssClass="alert alert-danger" />
        <br>
        <h2 class="text-uppercase bg-cover bg-dark text-center text-white p-2">
            ${bus.busLineId.busOwnerId.name}</h2>
        <hr>
        <div class="form-floating mb-3 mt-3 border-bottom">
            <form:input class="form-control" path="numberOfSit" type="number" id="numberOfSit"
                        name="numberOfSit" min="1" value="1" placeholder="Input number" />
            <label for="title">SỐ GHẾ MUỐN ĐẶT</label>
        </div>
        <div>
            <p>
                Giá vé : <span class="border p-2 fs-4 fw-bold">
                    <fmt:formatNumber type="number" maxFractionDigits="3"
                                      value="${bus.busLineId.ticketPrice}" /> VND
                </span>
            </p>
        </div>
        <div class=" mb-3 mt-3 border-bottom">

            <input type="radio" name="isPurchased" value="0"/>
            <label for="isPurchased">THANH TOÁN TẠI QUẦY</label><br>
            <input type="radio" name="isPurchased" value="-1"/>
            <label for="isPurchased">THANH TOÁN ONLINE</label><br>
        </div>
        <sec:authorize access="isAuthenticated()">
            <div class="form-group col-5">
                <label for="userId" class="form-label border-bottom">NGƯỜI ĐẶT</label>
                <form:select path="userId" class="form-select">
                    <option value="${pageContext.session.getAttribute(" currentUser").userId}">
                        <sec:authentication property="principal.username" />
                    </option>
                </form:select>
            </div>
        </sec:authorize>
        <div class="form-group col-5">
            <label for="userId" class="form-label border-bottom">CHUYẾN XE</label>
            <form:select path="busId" class="form-select">
                <option value="${bus.id}">${bus.startAt}</option>
            </form:select>
        </div>
        <hr>
        <div class="form-group ">

            <c:if test="${bus.direction==1}">
                Điểm đi &nbsp;&nbsp; <span
                    class="border p-1 bg-gradient text-uppercase bg-dark text-white">
                    ${bus.busLineId.fromPos.name} </span>
                <br /><br />Điểm đến <span
                    class="border p-1 bg-gradient text-uppercase bg-dark text-white">
                    ${bus.busLineId.toPos.name} </span>
                </c:if>

            <c:if test="${bus.direction==0}">
                Điểm đi &nbsp;&nbsp; <span
                    class="border p-1 text-uppercase bg-gradient bg-dark text-light">
                    ${bus.busLineId.toPos.name} </span>-
                <br /><br />Điểm đến <span
                    class="border p-1 text-uppercase bg-gradient bg-dark text-white">
                    ${bus.busLineId.fromPos.name} </span>
                </c:if>
        </div>
        <div class="form-floating">
            <br>
            <center><input type="submit" value="ĐẶT VÉ" class="btn btn-success col-6 " />
            </center>
        </div>
        <br><!-- comment -->
    </form:form>
</div>