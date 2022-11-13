<%-- 
    Document   : adminPage
    Created on : Aug 17, 2022, 1:38:20 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
    <div class="row">
        <div class="col-12 border-1">
            <div>
                <h3>NEWS LIST</h3>
                <a style="border: 1px solid gray" class="btn btn-mini" href="<c:url value="/admin/news" />" title="View more">View More <span class="icon-plus"></span></a>
            </div>
            <div class=" well-small">
                <c:forEach items="${orthers}" var="o">
                    <div style="display: flex; align-items:center; margin: 0px;padding: 0px" class="well row-fluid">	  
                        <div class="col-8">
                            <p>${o.title} -by- ${o.userId.username}-${o.userId.roleId.roleName} 
                                -at- <fmt:formatDate pattern="dd-MM-yyyy" value="${o.createdAt}" /></p>
                        </div>
                        <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                            <div class="btn-group">
                                <a style="border: 1px solid green;color:green;" href="" class="btn btn-mini">VIEW</a>
                                <c:if test="${o.isBlock ==0}" >
                                    <a style="border: 1px solid orange ;color: orange;" 
                                       href="<c:url value="/admin/news/block/${o.id}" />" class="btn btn-mini">BLOCK</a>
                                </c:if>
                                <c:if test="${o.isBlock ==1}" >
                                    <a style="border: 1px solid lightblue ;color: lightblue;" 
                                       href="<c:url value="/admin/news/block/${o.id}" />" class="btn btn-mini">UNBLOCK</a>
                                </c:if>
                                <a style="border: 1px solid red;color:red;" 
                                   href="<c:url value="/admin/news/delete/${o.id}" />" class="btn btn-mini">DELETE</a>
                            </div>

                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
        <div class="col-6 border-1">
            <h3>OWNER LIST</h3>
            <a style="border: 1px solid gray" class="btn btn-mini " href="products.html" title="View more">View More <span class="icon-plus"></span></a>



        </div>
    </div>
</div>
