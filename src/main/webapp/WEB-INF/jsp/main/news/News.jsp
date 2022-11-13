<%-- 
    Document   : News
    Created on : Aug 19, 2022, 4:26:53 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<div>
    <h3 class="font-weight-bold fs-4 text-center">Tin Tức</h3>
</div>
<div class=" well-small">
    <c:forEach items="${orthers}" var="o">
        <div style="display: flex; align-items:center; margin: 0px;padding: 0px;" class="well row-fluid mt-1">
            <div class="col-3">
                <img width="120" src="<c:url value="${o.img}" />" />
            </div>
            <div class="col-7">
                <p class="m-lg-2 mt-2 mb-2 font-weight-normal">${o.title} <small><i>-by- <span class="border border-1">${o.userId.username}-${o.userId.roleId.roleName}</span> 
                    -at- <fmt:formatDate pattern="dd-MM-yyyy" value="${o.createdAt}" /></i></small></p>
            </div>
            <div style="display: flex; justify-content:center;height: 100%" class="col-2 ">

                <div class="btn-group">
                    <a  href="<c:url value="/news/info/${o.id}" />" />
                            <span class="border p-1  fw-bold text-white bg-gradient-faded-info btn-mini">XEM CHI TIẾT</span>
                    </a>
                </div>

            </div>
        </div>
    </c:forEach>
</div>
