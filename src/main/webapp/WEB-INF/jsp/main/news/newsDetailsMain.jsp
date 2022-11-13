<%-- 
    Document   : newsDetailsMain
    Created on : Aug 19, 2022, 11:40:17 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<div class="row gx-5 mt-3">
    <div class="col-md-5 mb-4">
        <div class=" shadow-2-strong rounded-5" data-mdb-ripple-color="light">

            <image src="<c:url value="${news.img}" />" class="img-fluid" />

<!--            <a href="#!">
                <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
            </a>-->
        </div>
    </div>

    <div class="col-md-7 mb-4 bg-white">
        <span class="badge bg-danger px-2 py-1 shadow-1-strong mb-3">News of the day</span>
        <h4><strong>${news.title}</strong>
        </h4>
        <p class="font-weight-normal">
            ${news.content}
        </p>
        <h6>By ${news.userId.username}</h6>
        <p class="small font-weight-normal" >Created at: ${news.createdAt}</p>
        <p><a class="btn-mini border border-1" href="<c:url value="/news/" />"><< Quay lại</a></p>
    </div>

</div>
