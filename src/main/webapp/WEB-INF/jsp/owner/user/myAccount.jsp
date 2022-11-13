<%-- 
    Document   : myAccount
    Created on : Aug 31, 2022, 9:51:05 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-4 border-1 " style="border-right: 1px solid green">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                <img class="rounded-circle mt-5" width="150px" 
                     src="${me.avatar}">
                <span class="font-weight-bold">${me.username}</span>
                <a href="<c:url value="/${me.userId}/me/updatepass" />">Change password</a>
                <a>Change information</a>
                
                
            </div>
        </div>
        <div class="col-md-8 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Profile Infomation</h4>
                </div>
                <div class="row mt-2 ">
                    <div class="col-md-6"><label class="labels">Email</label>
                        <span class="border p-1 bg-gradient text-uppercase bg-dark text-white"> ${me.email}</span>
                    </div>
                    <div class="col-md-6"><label class="labels">Phone number</label>
                        <span class="border p-1 bg-gradient text-uppercase bg-dark text-white"> ${me.phone}</span>
                    </div>
                </div>
                
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">Role</label>
                        <span class="border p-1 bg-gradient text-uppercase bg-dark text-white"> ${me.roleId.roleName}</span>
                    </div>
                    
                </div>
                
            </div>
        </div>
    </div>
</div>
