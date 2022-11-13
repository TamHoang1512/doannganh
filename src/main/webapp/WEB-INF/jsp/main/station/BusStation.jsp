<%-- 
    Document   : BusStation
    Created on : Jul 27, 2022, 9:26:56 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!doctype html>


<h2 class="text-uppercase bg-cover bg-dark text-center text-white p-2">${t.name}</h2>

<div class="card mb-3">
    <div class="row g-0">
        <div class="col-md-4">
            <img src="${t.img}" class="img-fluid rounded-start" alt="hinh anh">
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h5 class="card-title bg-dark p-1 text-white-50">Thông tin bến xe</h5>
                <hr/>
                <p class="card-title ">
                    Địa chỉ  <span class="border p-1 bg-gradient text-uppercase bg-dark text-white">
                        ${t.location} </span>

                </p>
                <hr/>
                <p> Tình trạng:
                    <c:if test="${t.isBlock==0}" ><span class="border p-2 fs-4 fw-bold">Đang Hoạt Động</span></c:if>
                    <c:if test="${t.isBlock==1}" ><span class="border p-2 fs-4 fw-bold">Tạm Ngưng Hoạt Động</span></c:if> 
                    </p>
                    <hr/>

                </div>
            </div>
        </div>
    </div>

    <div class=" container col-12">
        <div class=" well-small">
        <c:if test="${line.size()==0}"> <span>Không có tuyến xe phù hợp</span></c:if>
        <c:if test="${line.size()!=0}"> <h1>Các tuyến xe đề xuất</h1></c:if>
        <c:forEach items="${line}" var="o">
            <div style="display: flex; align-items:center; margin: 0px;padding: 10px; " class="well row-fluid mt-1 col-8">	  
                <div class="col-8">
                    <span class="font-weight-normal"><span class="text-uppercase fw-bold">${o.busOwnerId.name}</span>  ${o.fromPos.name}-${o.toPos.name} </span> 
                </div>
                <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                    <div class="btn-group">
                        <a  href="<c:url value="/busown/${o.busOwnerId.id}/line/${o.id}" />" />
                            <span class="border p-1  fw-bold text-white bg-gradient-faded-info btn-mini">XEM CHI TIẾT</span>
                    </a>

                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</div>
