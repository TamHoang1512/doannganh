<%-- 
    Document   : ownerManagerAdmin
    Created on : Aug 22, 2022, 11:42:24 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<span class="d-flex"><h2 class="col-4" >Owner manage </h2><span class="d-flex m-0 p-0 align-self-center btn btn-outline-info" ><a href="/admin/owner/add">ADD OWNER</a></span></span>
<a href="<c:url value="/admin/owner/block" />">Manage Block Owner</a><br/>
<a href="<c:url value="/admin/owner/active" />">Manage UnActive Owner</a>
<div class="col-12">
    <div class=" well-small">
        <c:forEach items="${owner}" var="o">
            <div style="display: flex; align-items:center; margin: 0px;padding: 0px; padding-top: 10px;" class="well row-fluid mt-1">	  
                <div class="col-8">
                    <p>${o.name} -by- ${o.ownerUserId.username}-${o.ownerUserId.roleId.roleName} </p>
                </div>
                <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                    <div class="btn-group">
                        <a style="border: 1px solid green;color:green;" 
                           href="<c:url value="/admin/owner/info/${o.id}" />" class="btn btn-mini">VIEW
                        </a>
                           

                        <a style="border: 1px solid orange ;color: orange;" 
                           data-id="${o.id}"
                           data-name="${o.name}"
                           onclick="showConfirmBoxB(this.getAttribute('data-id'), this.getAttribute('data-name'))"
                           class="btn btn-mini">BLOCK
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

        $('#ownerTitle').text(title);
        $('#yesOption').attr('href', '/admin/owner/delete/' + id);
        $('#deleteConfirmId').modal('show');


    }
    function showConfirmBoxB(id, title) {

        $('#ownerTitleB').text(title);
        $('#yesOptionB').attr('href', '/admin/owner/block/' + id);
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
                Delete this owner "<span id="ownerTitle"></span> " ?
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
                Block this owner "<span id="ownerTitleB"></span> " ?
            </div>
            <div class="modal-footer">
                <a id="yesOptionB" type="button" class="btn btn-danger" >Yes</a>
            </div>
        </div>
    </div>
</div>
