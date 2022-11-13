<%-- 
    Document   : viewBusOwn
    Created on : Sep 1, 2022, 1:19:04 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
 
<div class="col-12">
    <div class=" well-small">
        <c:forEach items="${own}" var="o">
            <div style="display: flex; align-items:center; margin: 0px;padding: 0px; padding-top: 10px;" class="well row-fluid mt-1">	  
                <div class="col-8">
                    <p>${o.name} -by- ${o.ownerUserId.username}-${o.ownerUserId.roleId.roleName} </p>
                </div>
                <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                    <div class="btn-group">
                        <a style="border: 1px solid green;color:green;" 
                           href="<c:url value="/busown/${o.id}" />" class="btn btn-mini">VIEW
                        </a>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</div>
