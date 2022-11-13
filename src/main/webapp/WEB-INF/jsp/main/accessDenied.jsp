<%-- 
    Document   : accessDenied
    Created on : Sep 7, 2022, 2:27:31 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${param.accessDenied!=null}">
    <div class="alert alert-danger text-light text-center fw-bold">
        BẠN KHÔNG CÓ QUYỀN TRUY CẬP!<br>
        You dont have permission for this action! Please login and try again.
    </div>
</c:if>
