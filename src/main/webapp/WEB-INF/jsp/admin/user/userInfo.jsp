<%-- 
    Document   : UserInfo
    Created on : Jul 27, 2022, 4:53:11 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div
    class="card mb-3 text-white"
    style="width: 30rem; margin: 3rem auto"
    >
    <div class="row g-0">
        <div class="col-md-4">
            <img
                src="<c:url value="${u.avatar}" />"
                class="img-fluid rounded-start"
                alt="Profile Picture"
                />
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h3 class="card-title">Username: ${u.username}</h3>
                <h5 class="small">Contact info:</h5>
                <p class="card-text">
                    Email: ${u.email}
                </p>
                <p class="card-text">
                    Phone: ${u.phone}
                </p>
                <p class="card-text">
                    Role:  ${u.roleId.roleName}
                </p>
            </div>
            <c:if test="${u.roleId.roleName != 'admin'}">
                <a 
                   data-id="${u.userId}"
                   data-name="${u.username}"
                   onclick="showConfirmBox(this.getAttribute('data-id'), this.getAttribute('data-name'))"
                   class="btn btn-danger">DELETE
                </a>
            </c:if> 
            <a href="<c:url value="/admin/user" />" class="btn btn-info">Back</a>
        </div>
    </div>
</div>
<script>

    function showConfirmBox(id, title) {

        $('#newsTitle').text(title);
        $('#yesOption').attr('href', '/admin/user/delete/' + id);
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
                Delete this user "<span id="newsTitle"></span> " ?
            </div>
            <div class="modal-footer">
                <a id="yesOption" type="button" class="btn btn-danger" >Yes</a>
            </div>
        </div>
    </div>
</div>