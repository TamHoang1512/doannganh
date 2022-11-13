<%-- Document : Header Created on : Jul 26, 2022, 9:56:25 AM Author : 84344 --%>

  <%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

      <nav class="navbar fixed-top d-flex justify-content-center">
          <div class="container-fluid">
            <div class="container topNav d-flex justify-content-between ">
              <div><a class="logo" href="/">
                  <img width="80px" src="<c:url value="/assets/logo.png" />" alt="travel together">
                </a>
              </div>
              <div class="alignR  align-items-center">
                <a href="<c:url value="/" />"> <span class="icon-home"></span> HOME</a>
                <sec:authorize access="isAuthenticated()">
                  <a class="text-uppercase" href="<c:url value="
                    /${pageContext.session.getAttribute('currentUser').userId}/me" />"><span class="icon-user "></span>
                  My Account</a>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                  <a href="<c:url value=" /register" />"><span class="icon-edit"></span> FREE REGISTER
                  </a>
                  <a href="<c:url value=" /login" />"><span class="icon-envelope"></span>SIGN IN</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                  <a class="nav-link text-danger text-uppercase" href="<c:url value=" /login" />">
                  <sec:authentication property="principal.username" />
                  (${pageContext.session.getAttribute("currentUser").roleId.roleName})
                  </a>
                  <a class="nav-link text-danger" href="<c:url value=" /logout" />">SIGN OUT</a>
                </sec:authorize>
                <sec:authorize access="hasAuthority('admin')">
                  <a class="nav-link text-secondary" href="<c:url value=" /admin/" />">ADMIN</a>
                </sec:authorize>
                <sec:authorize access="hasAuthority('bus_owner')">
                  <a class="nav-link text-secondary" href="<c:url value='/owner/${pageContext.session.getAttribute("
                    currentUser").userId}' />">OWNER
                  </a>
                </sec:authorize>

              </div>
            </div>
          </div>
        </nav>



          <!--
    Navigation Bar Section 
    -->