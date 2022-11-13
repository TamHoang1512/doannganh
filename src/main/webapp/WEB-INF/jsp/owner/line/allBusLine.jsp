<%-- 
    Document   : allBusLine
    Created on : Aug 18, 2022, 9:26:44 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<p>${line.size()}</p>


<a style="border: 1px solid gray" 
   class="btn btn-outline-success" href="<c:url value="/owner/${uId}/line/add" />" 
        title="View more">Thêm tuyến xe
    <span class="icon-plus"></span>
</a>
<div class=" container col-12">
    <h2>CÁC TUYẾN XE </h2>
    <div class=" well-small thumbnails thumbnail ">
        <c:forEach items="${owner}" var="o">
            <div style="display: flex; align-items:center; margin: 5px;padding: 0px; padding-top: 10px;" 
                 class="well row-fluid mt-1">	  
                <div class="col-8">
                    <p>${o.fromPos.name}-${o.toPos.name} -belongs- ${o.busOwnerId.name}</p>
                </div>
                <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                    <div class="btn-group">
                        <a style="border: 1px solid green;color:green;" 
                           href="<c:url value="/owner/${uId}/own/${busOwnerId}/line/${o.id}" />" class="btn btn-mini">VIEW
                        </a>
                        
                        <a style="border: 1px solid green;color:green;" 
                           href="<c:url value="/owner/${uId}/line/update/${o.id}" />" class="btn btn-mini">EDIT
                        </a>


                        <a style="border: 1px solid red;color:red;" 
                           data-id="${o.id}"
                           data-name="${o.fromPos.name}-${o.toPos.name}"
                           onclick="showConfirmBox(this.getAttribute('data-id'), this.getAttribute('data-name'))"
                           class="btn btn-mini">DELETE
                        </a>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>

    function showConfirmBox(id, title) {

        $('#newsTitle').text(title);
        $('#yesOption').attr('href', '/owner/${uId}/line/delete/' + id);
        $('#deleteConfirmId').modal('show');


    }
</script>
<div class="modal fade" id="deleteConfirmId" tabindex="-1" role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete confirm</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Delete this news "<span id="newsTitle"></span> " ?
            </div>
            <div class="modal-footer">
                <a id="yesOption" type="button" class="btn btn-danger" >Yes</a>
            </div>
        </div>
    </div>
</div>