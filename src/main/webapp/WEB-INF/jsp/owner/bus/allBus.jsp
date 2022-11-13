<%-- 
    Document   : allBus
    Created on : Aug 18, 2022, 9:26:19 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<a style="border: 1px solid gray" 
   class="btn btn-outline-success" href="<c:url value="/owner/${id}/bus/add" />" 
   title="View more">THÊM CHUYẾN XE
    <span class="icon-plus"></span>
</a>
<div class=" container col-12">
    <h2>CÁC CHUYẾN XE SẮP ĐI</h2>
    <div class=" well-small">
        <c:forEach items="${bus}" var="o">
            <div style="display: flex; align-items:center; margin: 0px;padding: 0px; padding-top: 10px;" class="well row-fluid mt-1">	  
                <div class="col-8">
                    <p>${o.startAt}</p>
                    Direction :
                    <c:if test="${o.direction==1}" >Đi ${o.busLineId.fromPos.name}- Đến ${o.busLineId.toPos.name}</c:if>
                    <c:if test="${o.direction==0}" >Đi ${o.busLineId.toPos.name}- Đến ${o.busLineId.fromPos.name}</c:if>
                    </div>
                    <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                        <div class="btn-group">
                            <a style="border: 1px solid green;color:green;" 
                               href="<c:url value="/owner/${uId}/bus/${o.id}" />" class="btn btn-mini">Details
                        </a>



                        <a style="border: 1px solid red;color:red;" 
                           data-id="${o.id}"
                           data-name="${o.busLineId.fromPos.name}-${o.busLineId.toPos.name}"
                           onclick="showConfirmBox(this.getAttribute('data-id'), this.getAttribute('data-name'))"
                           class="btn btn-mini">DELETE
                        </a>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</div>

<br><!-- comment -->
<div class=" container col-12">
    <h2>CÁC CHUYẾN XE ĐÃ KHỞI HÀNH</h2>
    <div class=" well-small">
        <c:forEach items="${busStarted}" var="o">
            <div style="display: flex; align-items:center; margin: 0px;padding: 0px; padding-top: 10px;" class="well row-fluid mt-1">	  
                <div class="col-8">
                    <p>${o.startAt}</p>
                    Direction :
                    <c:if test="${o.direction==1}" >Đi ${o.busLineId.fromPos.name}- Đến ${o.busLineId.toPos.name}</c:if>
                    <c:if test="${o.direction==0}" >Đi ${o.busLineId.toPos.name}- Đến ${o.busLineId.fromPos.name}</c:if>
                    </div>
                    <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                        <div class="btn-group">
                            <a style="border: 1px solid green;color:green;" 
                               href="<c:url value="/owner/${uId}/bus/${o.id}" />" class="btn btn-mini">Details
                        </a>



                        <a style="border: 1px solid red;color:red;" 
                           data-id="${o.id}"
                           data-name="${o.busLineId.fromPos.name}-${o.busLineId.toPos.name}"
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
        $('#yesOption').attr('href', '/admin/owner/delete/' + id);
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
                Delete this bus "<span id="newsTitle"></span> " ?
            </div>
            <div class="modal-footer">
                <a id="yesOption" type="button" class="btn btn-danger" >Yes</a>
            </div>
        </div>
    </div>
</div>
