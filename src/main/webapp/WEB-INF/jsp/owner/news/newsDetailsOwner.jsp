<%-- 
    Document   : newsDetail
    Created on : Aug 17, 2022, 5:47:12 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<c:set var="roleUser" value="${pageContext.session.getAttribute('currentUser').roleId.roleName}" />
<c:set var="uId" value="${pageContext.session.getAttribute('currentUser').userId}" />

<div class="row gx-5 mt-3">
    <div class="col-md-5 mb-4">
        <div class=" shadow-2-strong rounded-5" data-mdb-ripple-color="light">

            <image src="<c:url value="${news.img}" />" class="img-fluid" />

            <a href="#!">
                <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
            </a>
        </div>
    </div>

    <div class="col-md-7 mb-4">
        <span class="badge bg-danger px-2 py-1 shadow-1-strong mb-3">News of the day</span>
        <h4><strong>${news.title}</strong>
            <div class="btn-group">

                <c:if test="${roleUser==('admin')}" >
                    <c:if test="${news.isBlock == 0}" >
                        <a style="border: 1px solid orange ;color: orange;" 
                           data-id="${news.id}"
                           data-name="${news.title}"
                           onclick="showConfirmBoxB(this.getAttribute('data-id'), this.getAttribute('data-name'))"
                           class="btn btn-mini">BLOCK
                        </a>
                    </c:if>
                    <c:if test="${news.isBlock != 0}" >
                        <a style="border: 1px solid lightblue ;color: lightblue;" 
                           href="<c:url value="/admin/news/block/${news.id}" />" class="btn btn-mini">UNBLOCK</a>
                    </c:if>

                </c:if>


                <c:if test="${roleUser==('bus_owner')}" >
                    <c:url value="/owner/${uId}/news/add" var="action" />
                </c:if>



                <c:if test="${roleUser==('admin')}" >
                    <a style="border: 1px solid red;color:red;" 
                       data-id="${news.id}"
                       data-name="${news.title}"
                       onclick="showConfirmBox(this.getAttribute('data-id'), this.getAttribute('data-name'))"

                       class="btn btn-mini">DELETE
                    </a>
                </c:if>
                <c:if test="${roleUser==('bus_owner')}" >
                    <a style="border: 1px solid red;color:red;" 
                       data-id="${news.id}"
                       data-name="${news.title}"
                       onclick="showConfirmBoxOwner(this.getAttribute('data-id'), this.getAttribute('data-name'))"

                       class="btn btn-mini">DELETE
                    </a>
                </c:if>




            </div>
        </h4>
        <p class="text-muted">
            ${news.content}
        </p>
        <h6>By ${news.userId.username}</h6>
        <p class="small" >Created at: ${news.createdAt}</p>

    </div>

</div>


<script>

    function showConfirmBox(id, title) {

        $('#newsTitle').text(title);
        $('#yesOption').attr('href', '/admin/news/delete/' + id);
        $('#deleteConfirmId').modal('show');


    }
    function showConfirmBoxOwner(id, title) {

        $('#newsTitle').text(title);
        $('#yesOption').attr('href', '/owner/${uId}/news/delete/' + id
                );
        $('#deleteConfirmId').modal('show');


    }
    
    function showConfirmBoxB(id, title) {

        $('#newsTitleB').text(title);
        $('#yesOptionB').attr('href', '/admin/news/block/' + id);
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
                Delete this news "<span id="newsTitle"></span> " ?
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
                Block this news "<span id="newsTitleB"></span> " ?
            </div>
            <div class="modal-footer">
                <a id="yesOptionB" type="button" class="btn btn-danger" >Yes</a>
            </div>
        </div>
    </div>
</div>