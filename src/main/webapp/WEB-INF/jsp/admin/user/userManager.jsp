<%-- 
    Document   : UserManager
    Created on : Jul 27, 2022, 2:52:03 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-light bg-light navbar-expand-lg">
    <div class="container">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExample07">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active font-weight-bold text-uppercase" style="font-size: 18px" aria-current="page" href="/admin/user">All User</a>
                </li>
                <c:forEach items="${role}" var="r">
                    <c:url value="/admin/user" var="rUrl">
                        <c:param name="roleId" value="${r.roleId}" />
                    </c:url>
                    <li class="nav-item">
                        <a class="nav-link font-weight-bold text-uppercase" style="font-size: 18px" href="${rUrl}">${r.roleName}</a>
                    </li>
                </c:forEach>
            </ul>
            <form style="width: 250px">
                <input style="border: 1px solid blue" 
                       name="kw"
                       class="form-control" type="text" 
                       placeholder="Search by username or email" aria-label="Search"/>
            </form>
        </div>
    </div>
</nav>
<div class="container"><a class="font-weight-bold btn btn-outline-info" 
                          style="font-size: 16px;color:green" 
                          href="<c:url value="/admin/register" />">Đăng ký admin</a></div>
<center>
    <!--<h2>User Manager Page</h2>-->
    <table class="table">
        <thead>
            <tr style="text-align: center">
                <th>Avatar</th>
                <th>Username</th>
                <th>Email</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${allUsers}" var="u">

                <tr>

                    <td class="tbUser" ><img width="120px" src="<c:url value="${u.avatar}" />" /></td>
                    <td class="tbUser">${u.username}</td>
                    <td class="tbUser">${u.email}</td>
                    <td class="tbUser">${u.roleId.roleName}</td>
                    <td class="tbUser">
                        <a style="border: 1px solid green;color: green;" href="<c:url value="/admin/user/${u.userId}" />" class="btn btn-mini">View</a> 
                        <c:if test="${u.roleId.roleName != 'admin'}">
                            <a style="border: 1px solid red;color:red;" 
                               data-id="${u.userId}"
                               data-name="${u.username}"
                               onclick="showConfirmBox(this.getAttribute('data-id'), this.getAttribute('data-name'))"
                               class="btn btn-mini">DELETE
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</center>

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