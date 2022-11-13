<%-- 
    Document   : stationManager
    Created on : Aug 22, 2022, 12:38:50 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<span class="d-flex"><h2 class="col-4" >Station manage </h2><span class="d-flex m-0 p-0 align-self-center btn btn-outline-info" ><a href="/admin/station/add">ADD NEW STATION</a></span></span>
<a href="<c:url value="/admin/station/block" />">Manage Block station</a>
<div class="col-12">
    <div class=" well-small">
        <c:forEach items="${station}" var="o">
            <div style="display: flex; align-items:center; margin: 0px;padding: 0px; padding-top: 10px;" class="well row-fluid mt-1">	  
                <div class="col-8">
                    <p>${o.name} -by- ${o.adminId.username}-${o.adminId.roleId.roleName} 
                        -at- <fmt:formatDate pattern="dd-MM-yyyy" value="${o.createdAt}" /></p>
                </div>
                <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                    <div class="btn-group">
                        <a style="border: 1px solid green;color:green;" 
                           href="<c:url value="/admin/station/info/${o.id}" />" class="btn btn-mini">VIEW
                        </a>
                        <a style="border: 1px solid green;color:green;" 
                           href="<c:url value="/admin/station/update/${o.id}" />" class="btn btn-mini">EDIT
                        </a>
                           

<!--                        <a style="border: 1px solid orange ;color: orange;" 
                           data-id="${o.id}"
                           data-name="${o.name}"
                           onclick="showConfirmBoxB(this.getAttribute('data-id'), this.getAttribute('data-name'))"
                           class="btn btn-mini">BLOCK
                        </a>-->
                           
                           
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
