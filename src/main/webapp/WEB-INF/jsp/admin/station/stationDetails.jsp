<%-- 
    Document   : stationDetails
    Created on : Aug 22, 2022, 12:39:14 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<div class="row gx-5 mt-3">
    <div class="col-md-5 mb-4">
        <div class=" shadow-2-strong rounded-5" data-mdb-ripple-color="light">

            <image src="<c:url value="${station.img}" />" class="img-fluid" />

            <a href="#!">
                <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
            </a>
        </div>
    </div>

    <div class="col-md-7 mb-4">
        <span class="badge bg-danger px-2 py-1 shadow-1-strong mb-3">Station</span>
        <h4><strong>${station.name}</strong>
            <div class="btn-group">
<!--                
                <c:if test="${station.isBlock == 0}" >
                    <a style="border: 1px solid orange ;color: orange;" 
                       data-id="${station.id}"
                       data-name="${station.name}"
                       onclick="showConfirmBoxB(this.getAttribute('data-id'), this.getAttribute('data-name'))"
                       class="btn btn-mini">BLOCK
                    </a>
                </c:if>
                <c:if test="${station.isBlock != 0}" >
                    <a style="border: 1px solid lightblue ;color: lightblue;" 
                       href="<c:url value="/admin/station/block/${station.id}" />" class="btn btn-mini">UNBLOCK</a>
                </c:if>
                -->
                <a style="border: 1px solid red;color:red;" 
                   data-id="${station.id}"
                   data-name="${station.name}"
                   onclick="showConfirmBox(this.getAttribute('data-id'), this.getAttribute('data-name'))"
                   class="btn btn-mini">DELETE
                </a>
            </div>
        </h4>
        <p class="text-muted">
            ${station.location}
        </p>
        <h6>By ${station.adminId.username}</h6>
        <p class="small" >Created at: ${station.createdAt}</p>

        
    </div>

</div>


<script>

    function showConfirmBox(id, title) {

        $('#stationTitle').text(title);
        $('#yesOption').attr('href', '/admin/station/delete/' + id);
        $('#deleteConfirmId').modal('show');


    }
    function showConfirmBoxB(id, title) {

        $('#stationTitleB').text(title);
        $('#yesOptionB').attr('href', '/admin/station/block/' + id);
        $('#blockConfirmId').modal('show');


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
                Delete this station "<span id="stationTitle"></span> " ?
            </div>
            <div class="modal-footer">
                <a id="yesOption" type="button" class="btn btn-danger" >Yes</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="blockConfirmId" tabindex="-1" role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Block confirm</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Block this station "<span id="stationTitleB"></span> " ?
            </div>
            <div class="modal-footer">
                <a id="yesOptionB" type="button" class="btn btn-danger" >Yes</a>
            </div>
        </div>
    </div>
</div>
