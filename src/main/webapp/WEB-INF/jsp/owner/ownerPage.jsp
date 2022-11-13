<%-- 
    Document   : OwnerPage
    Created on : Aug 18, 2022, 8:08:48 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<div class="container">
    <span class="d-flex"><h2 class="col-4" >QUẢN LÝ NHÀ XE </h2>
    </span>
</div>
<a style="border: 1px solid gray" 
   class="btn btn-mini" href="<c:url value="/owner/${id}/own/add" />" 
        title="View more">ĐĂNG KÝ NHÀ XE
    <span class="icon-plus"></span>
</a>
   <c:if test="${errMsg != null}">
       <p class="text text-center text-danger">${errMsg}</p>
   </c:if>
<br/><br/><br/>
<div class=" container col-12">
    <h4>NHÀ XE</h4>
    <div class=" well-small">
        <c:forEach items="${lead}" var="o">
            <div style="display: flex; align-items:center; margin: 0px;padding: 0px; padding-top: 10px;" class="well row-fluid mt-1">	  
                <div class="col-8">
                    <p>${o.name} -by- ${o.ownerUserId.username}-${o.ownerUserId.roleId.roleName} </p>
                </div>
                <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                    <div class="btn-group">

                        <c:if test="${o.isBlock==1}" >
                            <span style="border: 1px solid orangered;color: orangered;" class="btn btn-mini">Đã bị khóa</span>
                        </c:if>

                        <a style="border: 1px solid green;color:green;" 
                           href="<c:url value="/owner/${uId}/own/${o.id}/line" />" class="btn btn-mini">VIEW
                        </a>
                        
                        <a style="border: 1px solid green;color:green;" 
                           href="<c:url value="/owner/${uId}/own/update/${o.id}" />" class="btn btn-mini">EDIT
                        </a>



                        <a style="border: 1px solid red;color:red;" 
                           data-id="${o.id}"
                           data-name="${o.name}"
                           onclick="showConfirmBox(this.getAttribute('data-id'), this.getAttribute('data-name'))"
                           class="btn btn-mini">DELETE
                        </a>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>

    function showConfirmBox(id, title) {

        $('#newsTitle').text(title);
        $('#yesOption').attr('href', '/owner/${uId}/own/delete/' + id);
        $('#deleteConfirmId').modal('show');


    }
</script>
<div class="modal fade" id="deleteConfirmId" tabindex="-1" role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete confirm</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Delete this news "<span id="newsTitle"></span> " ?
            </div>
            <div class="modal-footer">
                <a id="yesOption" type="button" class="btn btn-danger" >Yes</a>
            </div>
        </div>
    </div>
</div>


<h4>CÁC CHUYẾN XE MỚI</h4>
<ul class=" thumbnails row">
    <c:forEach items="${bus}" var="l">
        <li class="col-4 d-flex">
            <div class="thumbnail flexEqual">

                <a class="zoomTool" href="<c:url value="/owner/${uId}/bus/${l.id}" />" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
                <a class="flexAll" href="product_details.html"><img src="${l.img}" alt=""></a>
                <div class="caption cntr flexShrink">
                    <c:if test="${l.direction==0}">
                        <p> Từ :${l.busLineId.fromPos.name} - đến :${l.busLineId.toPos.name}</p>
                    </c:if>
                    <c:if test="${l.direction==1}">
                        <p> Từ : ${l.busLineId.toPos.name} - đến : ${l.busLineId.fromPos.name}</p>
                    </c:if>
                    <p>Ngày giờ đi: ${l.startAt}</p>
                    <p>Giá vé:<strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${l.busLineId.ticketPrice}" /> </strong></p>
                    <c:if test="${l.busLineId.busOwnerId.isBlock==1}" >
                        <span style="border: 1px solid orangered;color: orangered;">Đã bị khóa</span>
                    </c:if>
                        <c:if test="${l.busLineId.busOwnerId.isBlock==0}" >
                    <h4><a class="shopBtn" href="<c:url value="/owner/${uId}/bus/${l.id}" />" title="view"> VIEW </a></h4>
                    </c:if>
                    <br class="clr">
                </div>
            </div>
        </li>
    </c:forEach>
</ul>