<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>


<nav style="margin-top: 50px" class="navbar navbar-expand-lg navbar-dark bg-dark pd05" aria-label="Eighth navbar example ">
    <div class="container">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExample07">
            <ul  class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/"/>">TRANG CHỦ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/station"/>">CÁC BẾN XE</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/news"/>" tabindex="-1" aria-disabled="true">TIN TỨC</a>
                </li>
                <sec:authorize access="isAuthenticated()">
                    <c:set var="uId" value="${pageContext.session.getAttribute('currentUser').userId}" />
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/${uId}/booked" />" tabindex="-1" aria-disabled="true">LỊCH SỬ ĐẶT VÉ</a>
                    </li>
                </sec:authorize>

                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/find"/>" tabindex="-1" aria-disabled="true">TÌM KIẾM 
                        <svg aria-hidden="true" class="w-5 h-5 text-gray-500 dark:text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path></svg>
       
                    </a>
                </li>

            </ul>
        </div>
    </div>
</nav>



<div class="m-1"></div>
<div class="row">
    <div id="sidebar" class="col-3">
        <div class="well well-small">
            <h5>CÁC BẾN XE</h5>
            <hr>
            <ul style="list-style: none" class="nav row">
                <c:forEach items="${station}" var="t">
                    <li>
                        <a href="<c:url value="/busstation/${t.id}" />">
                            <span class="icon-chevron-right"></span>${t.name}
                        </a>
                    </li>
                    <hr>
                </c:forEach>
            </ul>
        </div>

        <div class="well well-small">
            <a href="<c:url value="/news" />"><h5>THÔNG BÁO <span class="btn btn-mini pull-right">View More 
                        <span class="icon-plus"></span>
                    </span></h5></a>
            <hr>
            <ul style="list-style: none" class="row nav">
                <c:forEach items="${orthers}" var="o">
                    <li class="mb-3">
                        <a class="col-12" href="<c:url value="/news/info/${o.id}" />">
                            <span><span class="icon-chevron-right"></span>${o.title} </span>
                            <sub class="d-flex justify-content-end">
                                <fmt:formatDate pattern="dd-MM-yyyy" value="${o.createdAt}" />
                            </sub>
                        </a>
                    </li>
                    <hr>
                </c:forEach>
            </ul>
        </div>    

    </div>

    <div class="col-9 font-monospace">
        <div class="well np">
            <div id="myCarousel" class="carousel slide homCar">
                <h6><i>Ads</i></h6>
                <div class="carousel-inner">

                    <c:forEach items="${own}" var="b">

                        <div class="item"> <a href="<c:url value="/busown/${b.id}" />">
                                <img style="width:100%" src="${b.img}" alt="...">
                                <div class="carousel-caption row text-center">
                                    <h4 class="col-12 ">${b.name}</h4>

                                </div></a>
                        </div>

                    </c:forEach>
                </div>
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
            </div>
        </div>
        <!--
        New Products
        -->
        <div class="well well-small">
            <h3><a class="btn btn-mini pull-right" href="/list_bus">View More 
                    <span class="icon-plus"></span>
                </a> Các chuyến xe mới  
            </h3>

            <hr>
            <ul class="thumbnails row">
                <c:forEach items="${bus}" var="l" begin="0" end="4">
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
        </div>


        <div class="well well-small">
            <h3><a class="btn btn-mini pull-right" href="<c:url value="/busown/" />">View More 
                    <span class="icon-plus"></span>
                </a> Các nhà xe  
            </h3>
            <hr>

            <ul class="thumbnails row">
                <c:forEach items="${own}" var="o">
                    <div class="card bg-dark text-white col-5 m-3">
                        <a class="flexAll opacity-3" href="<c:url value="/bussown/${o.id}" />" ><img src="${o.img}" alt=""></a>
                        <div class="card-img-overlay">
                            <div class="caption cntr flexShrink">
                                <h2 class="text-uppercase fw-bold text-white ">
                                    ${o.name}
                                </h2>
                                <div class="btn-group">
                                    <a  
                                        href="<c:url value="/busown/${o.id}" />" class="btn btn-warning">Xem
                                    </a>
                                    <br class="clr">
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </ul>


        </div>
    </div>
</div>

