<%-- 
    Document   : stationBlock
    Created on : Aug 22, 2022, 12:31:59 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<h2>Station Blocked</h2><a href="<c:url value="/admin/station/" />">Back to Manage Station</a>
<div class="col-12">
    <div class=" well-small">
        <c:forEach items="${blockSta}" var="o">
            <div style="display: flex; align-items:center; margin: 0px;padding: 0px" class="well row-fluid">	  
                <div class="col-8">
                    <p>${o.name} -by- ${o.adminId.username}-${o.adminId.roleId.roleName} 
                        -at- <fmt:formatDate pattern="dd-MM-yyyy" value="${o.createdAt}" /></p>
                </div>
                <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                    <div class="btn-group">
                        
                        <c:if test="${o.isBlock == 1}" >
                        <a style="border: 1px solid lightblue ;color: lightblue;" 
                           href="<c:url value="/admin/station/block/${o.id}" />" class="btn btn-mini">UNBLOCK</a>
                        </c:if>
                        <a style="border: 1px solid red;color:red;" 
                           href="<c:url value="/admin/station/delete/${o.id}" />" class="btn btn-mini">DELETE</a>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
    <div class=" well-small">
        <c:if test="${blockSta.size()==0}" ><span class="text-bg-dark">Nothing here</span></c:if>
    </div>
</div>
