<%-- 
    Document   : ownerActiveAdmin
    Created on : Aug 24, 2022, 3:12:09 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<h2>Owner Active</h2><a href="<c:url value="/admin/owner" />"><< Back to Manage Active</a>
<div class="col-12">
    <div class=" well-small">
        <c:forEach items="${activeOwner}" var="o">
            <div style="display: flex; align-items:center; margin: 0px;padding: 0px" class="well row-fluid">	  
                <div class="col-8">
                    <p>${o.name} -by- ${o.ownerUserId.username}-${o.ownerUserId.roleId.roleName} 
                    </p>
                </div>
                <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                    <div class="btn-group">
                        
                        <a style="border: 1px solid lightblue ;color: lightblue;" 
                           href="<c:url value="/admin/owner/active/${o.id}" />" class="btn btn-mini">ACTIVE</a>
                        <a style="border: 1px solid red;color:red;" 
                           href="<c:url value="/admin/owner/delete/${o.id}" />" class="btn btn-mini">DELETE</a>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
    <div class=" well-small">
        <c:if test="${activeOwner.size()==0}" ><span class="text-bg-dark">Nothing here</span></c:if>
    </div>
</div>
