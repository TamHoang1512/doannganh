<%-- 
    Document   : adminBase
    Created on : Aug 17, 2022, 2:44:27 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>














        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


        <title>

            Admin page





        </title>

        <link rel="stylesheet"  href="<c:url value='/assets/css/bootstrap-grid.css' />" />
        <link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css' />" />



        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />



        <!-- Font Awesome Icons -->
        <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>

        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">

        <!-- CSS Files -->



        <link id="pagestyle" href="<c:url value='/assets/css/material-dashboard.css' />" rel="stylesheet" />

        <link rel="shortcut icon" href="<c:url value ='/assets/icon.png' />">

        <link href="<c:url value="/assets/style.css" />" rel="stylesheet" />
        <link href="<c:url value='/assets/font-awesome/css/font-awesome.css' />" rel="stylesheet">

    </head>


    <body class="g-sidenav-show  bg-gray-100 font-monospace" >


        <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark" id="sidenav-main">

            <div class="sidenav-header">
                <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
                <a class="navbar-brand m-0" href=" https://demos.creative-tim.com/material-dashboard/pages/dashboard " target="_blank">
                    <img src="<c:url value='/assets/icon.png' />" class="navbar-brand-img h-100" alt="main_logo">
                    <span class="ms-1 font-weight-bold text-white">QuanLyBenXe</span>
                </a>
            </div>


            <hr class="horizontal light mt-0 mb-2">

            <div class=" navbar   w-auto " id="sidenav-collapse-main">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link text-white " href="<c:url value="/admin/" />">

                            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                <span class='material-icons'>dashboard</span>
                            </div>

                            <span class="nav-link-text ms-1">Dashboard</span>
                        </a>
                    </li>


                    <li class="nav-item">
                        <a class="nav-link text-white " href="<c:url value="/admin/owner"  />">

                            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                <i class="material-icons opacity-10">table_view</i>
                            </div>

                            <span class="nav-link-text ms-1">Owner Management</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white " href="<c:url value="/admin/user" />">

                            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                <i class="material-icons opacity-10">table_view</i>
                            </div>

                            <span class="nav-link-text ms-1">User Management</span>
                        </a>
                    </li>


                    <li class="nav-item">
                        <a class="nav-link text-white " href="<c:url value="/admin/news" />">

                            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                <span class="material-icons">feed</span>
                            </div>

                            <span class="nav-link-text ms-1">News</span>
                        </a>
                    </li>


                    <li class="nav-item">
                        <a class="nav-link text-white " href="<c:url value="/admin/station" />">

                            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                <span class="material-icons">anchor</span>
                            </div>

                            <span class="nav-link-text ms-1">All Station</span>
                        </a>
                    </li>



                    <li class="nav-item">
                        <a class="nav-link text-white " href="<c:url value="/admin/owner/active" />">

                            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                <i class="material-icons opacity-10">notifications

                                </i>
                            </div>

                            <span class="nav-link-text ms-1">Notifications</span>
                        </a>
                    </li>


                    <li class="nav-item mt-3">
                        <h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Account pages</h6>
                    </li>
                    <sec:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link text-white" href="<c:url value="/login" />">

                                <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                    <i class="material-icons opacity-10">person</i>
                                </div>


                                <span>
                                <sec:authentication property="principal.username" />
                                (${pageContext.session.getAttribute("currentUser").roleId.roleName})
                                </span>

                            </a>
                        </li>
                    </sec:authorize>
                    <li>
                        <sec:authorize access="isAuthenticated()">
                            <a class="nav-link text-danger" href="<c:url value="/logout" />">Dang xuat</a>
                        </sec:authorize>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white" href="<c:url value="/" />"> <span class="icon-home"></span>&nbsp;&nbsp; Home</a> 
                    </li>




                    <li class="nav-item">
                        <a class="nav-link text-white " href="/admin/register">

                            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                <span class="material-icons">how_to_reg</span>
                            </div>

                            <span class="nav-link-text ms-1">Sign Up New Admin</span>
                        </a>
                    </li>







                </ul>
            </div>

        </aside>

        <main class="main-content border-radius-lg mt-3 ">

            <tiles:insertAttribute name="adminContent"/>

        </main>






















        <!--   Core JS Files   -->
        <script src="<c:url value='/assets/js/core/popper.min.js' />" ></script>
        <script src="<c:url value='/assets/js/core/bootstrap.min.js' />" ></script>
        <script src="<c:url value='/assets/js/plugins/perfect-scrollbar.min.js' />" ></script>
        <script src="<c:url value='/assets/js/plugins/smooth-scrollbar.min.js' />" ></script>









































































        <script>
            var win = navigator.platform.indexOf('Win') > -1;
            if (win && document.querySelector('#sidenav-scrollbar')) {
                var options = {
                    damping: '0.5'
                }
                Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


    </body>

</html>
