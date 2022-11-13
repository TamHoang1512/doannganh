<%-- 
    Document   : newsAdminBlock
    Created on : Aug 20, 2022, 1:00:54 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<h2>News Blocked</h2><a href="<c:url value="/admin/news/" />">Back to Manage news</a>
<div class="col-12">
    <div class=" well-small">
        <c:forEach items="${block}" var="o">
            <div style="display: flex; align-items:center; margin: 0px;padding: 0px" class="well row-fluid">	  
                <div class="col-8">
                    <p>${o.title} -by- ${o.userId.username}-${o.userId.roleId.roleName} 
                        -at- <fmt:formatDate pattern="dd-MM-yyyy" value="${o.createdAt}" /></p>
                </div>
                <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                    <div class="btn-group">
                        
                        <c:if test="${o.isBlock == 1}" >
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
    <div class=" well-small">
        <c:if test="${block.size()==0}" ><span class="text-bg-dark">Nothing here</span></c:if>
    </div>
</div>