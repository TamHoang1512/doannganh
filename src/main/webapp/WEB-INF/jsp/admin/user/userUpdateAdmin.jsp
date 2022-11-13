<%-- 
    Document   : userUpdateAdmin
    Created on : Aug 25, 2022, 11:43:18 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>

<c:url value="/admin/user/update" var="action" />

<section class="vh-60 font-monospace" style="background-color: #eee;">
    <div class="container h-60">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-6">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">

                            <div class="col-12 d-flex align-items-center order-1 order-lg-1">

                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                     class="img-fluid" alt="Sample image">

                            </div>

                            <div class="col-12 order-2 order-lg-2">
                                <c:url value="/admin/user/update/${user.userId}" var="action" />
                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Update</p>

                                <form:form method="post" action="${action}" 
                                           modelAttribute="user" enctype="multipart/form-data">
                                    <form:errors path="*" element="div" cssClass="alert alert-danger" />

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="icon-key me-3 fa-fw"></i>
                                        <div class="form-floating mb-3 flex-fill mb-0">
                                            <form:input type="password" class="form-control" path="password" id="password" placeholder="email" />
                                            <label for="email">Enter your password:</label>
                                            <form:errors path="password" element="div" cssClass="invalid-feedback" />
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="icon-key me-3 fa-fw"></i>
                                        <div class="form-floating mb-3 flex-fill mb-0 fs-6">
                                            <form:input type="password" class="form-control" path="confirmPassword" id="confirmPassword" placeholder="email" />
                                            <label for="email">Enter your confirm password:</label>
                                            <form:errors path="confirmPassword" element="div" cssClass="invalid-feedback" />
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="icon-upload me-3 fa-fw"></i>
                                        <div class=" mb-3 flex-fill mb-0 fs-6">
                                            <label for="imgId">
                                                <!--<spring:message code="product.image" />-->
                                                Avatar
                                            </label>
                                            <form:input type="file" id="avatarFile" path="avatarFile" 
                                                        cssClass="form-control" />
                                            <form:errors path="avatarFile" cssClass="text-danger" />
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row align-items-center mb-4 display-none">
                                        <i class="icon-key me-3 fa-fw"></i>
                                        <div class=" mb-3 flex-fill mb-0 fs-6">
                                            <label for="roleId" class="form-label">Role</label>
                                            <form:select path="roleId" class="form-select">
                                                <sec:authorize access="isAuthenticated()">
                                                    <option value="${pageContext.session.getAttribute("currentUser").roleId.roleId}">
                                                    ${pageContext.session.getAttribute("currentUser").roleId.roleName}
                                                    </option>

                                                </sec:authorize>
                                            </form:select>
                                        </div>

                                    </div>
                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="submit" class="btn btn-outline-success btn-lg">Update</button>
                                    </div>

                                </form:form>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<br/><br/><br/><br/><br/><br/>
