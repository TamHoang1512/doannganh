<%-- 
    Document   : ownDetails
    Created on : Sep 1, 2022, 1:22:54 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<h2 class="text-uppercase bg-cover bg-dark text-center text-white p-2">${owninfo.name}</h2>
<hr>
<c:if test="${okMsg!=null}" >
    <p class="text text-center alert alert-success">${okMsg}</p>
</c:if>
<div class="card mb-3">
    <div class="row g-0">
        <div class="col-md-4">
            <img src="${owninfo.img}" class="img-fluid rounded-start" alt="hinh anh">
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h5 class="card-title bg-dark p-1 text-white-50">Thông tin nhà xe</h5>
                <hr/>
                <p class="card-title ">

                    Số điện thoại  <span class="border p-1 bg-gradient text-uppercase bg-dark text-white">
                        ${owninfo.phone} 
                    </span>  
                </p>
                <hr><!-- comment -->
                <c:if test="${owninfo.isFreight==1}"><span class="border p-1 bg-gradient text-uppercase bg-dark text-white">
                        CÓ NHẬN GIAO HÀNG</span> 
                    </c:if> 
                    <c:if test="${owninfo.isFreight==0}"><span class="border p-1 bg-gradient text-uppercase bg-dark text-white">
                        KHÔNG NHẬN GIAO HÀNG</span> 
                    </c:if> 

                <hr/>
                <p>${owninfo.description}</p>
                <hr/>

                <c:if test="${owninfo.isActive==1&&owninfo.isBlock ==0}" >ĐANG HOẠT ĐỘNG</c:if>
                <c:if test="${owninfo.isActive==0||owninfo.isBlock ==1}" >TẠM NGƯNG HOẠT ĐỘNG</c:if>
                </div>
            </div>
        </div>



        <hr><!-- comment -->


        <div class=" container col-12">
            <h4>CÁC TUYẾN XE</h4>
            <div class=" well-small">
            <c:forEach items="${line}" var="o">
                <div style="display: flex; align-items:center; margin: 0px;padding: 10px;" class="well row-fluid mt-1">	  
                    <div class="col-8">
                        <span><span class="font-weight-bold">${o.fromPos.name}-${o.toPos.name}</span> -belongs- <i><u>${o.busOwnerId.name}</u></i></span>
                    </div>
                    <div style="display: flex;" class="col-4 ">

                        <div class="btn-group">
                            <a  href="<c:url value="/busown/${owninfo.id}/line/${o.id}" />"/>
                            <span class="border p-1  fw-bold text-white bg-gradient-faded-info btn-mini">XEM CHI TIẾT</span>
                            </a>

                        </div>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <hr>

    <sec:authorize access="isAuthenticated()">
        <div class="container col-12 " style="font-family: 'Time News Ronamce'; background-color: white; ">
            <h4>PHẢN HỒI</h4>
            <c:url value="/busown/${owninfo.id}/comment" var="action" />
            <c:if test="${errMsg!=null}" >
                <p class="text text-center alert alert-danger">${errMsg}</p>
            </c:if>
            <form:form  method="post" action="${action}" 
                        modelAttribute="ratingPost" 
                        >
                <form:errors path="*" element="div" cssClass="alert alert-danger" />
                <br>


                <div class="" id="rating">
                    <input type="radio" id="star5" name="rate" value="5" />
                    <label class = "full" for="star5" title="Awesome - 5 stars"></label>

                    <input type="radio" id="star4" name="rate" value="4" />
                    <label class = "full" for="star4" title="Pretty good - 4 stars"></label>

                    <input type="radio" id="star3" name="rate" value="3" />
                    <label class = "full" for="star3" title="Meh - 3 stars"></label>

                    <input type="radio" id="star2" name="rate" value="2" />
                    <label class = "full" for="star2" title="Kinda bad - 2 stars"></label>

                    <input type="radio" id="star1" name="rate" value="1" />
                    <label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                </div>
                <div class="form-floating mt-3 mb-3 border-bottom">
                    <form:textarea class="form-control height-100" path="comment" id="comment" placeholder="comment" />
                    <label for="comment" style="font-size: 15px" class="font-weight-bold text-uppercase">Nội dung</label>
                </div>
                <div class="form-group col-5">
                    <form:hidden path="userId" value="${pageContext.session.getAttribute('currentUser').userId}"/>    
                </div>
                <div class="form-group col-5">
                    <form:hidden path="busOwnerId" value="${owninfo.id}"/>                
                </div>
                <hr>
                <div class="form-floating">
                    <br>
                    <center><input type="submit" value="Đánh giá" class="btn btn-success col-6 " /></center>
                </div>
                <br>
            </form:form>
        </div>        
    </sec:authorize>

    <c:if test="${rated.size()>=1}">
        <div  class=" container col-12" style="margin-bottom : 200px" id="rating"><h2>Phản hồi khách hàng:</h2>
            <c:forEach items="${rated}" var="r">
                <hr>
                <div class="row d-flex flex-row">
                    <div class="d-flex">
                        <c:forEach  begin="1" end="${r.rate}" var="ra">
                            <span class="material-icons" style="color: rgb(255, 210, 48);">star_rate</span>
                        </c:forEach>
                    </div>
                    <div class="row">
                        <div class="col-2 border">
                            <img src="${r.userId.avatar}" width="50px" alt="alt"/>
                            <span class="fw-bold">${r.userId.username}</span> 
                        </div>
                        <div class="col-10 border">${r.comment}</div>
                    </div>
                </div>
                <hr>
            </c:forEach>


        </div>
    </c:if>
    <c:if test="${rated.size()==0}"><h3 class="p-5">Chưa có phản hồi</h3></c:if>
</div>



