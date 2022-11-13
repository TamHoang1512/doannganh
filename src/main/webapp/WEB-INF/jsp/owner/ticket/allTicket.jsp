<%-- 
    Document   : allTicKet
    Created on : Aug 28, 2022, 12:24:23 AM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<span class="d-flex"><h2 class="col-4" >ALL TICKET </h2></span>

<p>soluong ${allTicket.size()}</p>

<div class="col-12">
    <div class=" well-small">


        <table class="table table-dark table-striped">
            <thead>
                <tr>
                    <th class="text text-center">Mã số vé</th>
                    <th class="text text-center">Số lượng ghế</th>
                    <th class="text text-center">Chuyến xe</th>
                    <th class="text text-center">Ngày giờ khởi hành</th>
                    <th class="text text-center">Người đặt</th>
                    <th class="text text-center">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${allTicket}" var="o">
                    <tr>
                        <td class="text text-center">${o.id}</td>
                        <td class="text text-center">${o.numberOfSit}</td>
                        <td class="text text-center">${o.busId.id}</td>
                        <td class="text text-center">${o.busId.startAt}</td>
                        <td class="text text-center">${o.userId.username}</td>
                        <td class="text text-center"><a style="border: 1px solid red;color:red;" 
                       data-id="${o.id}"
                       data-name="${o.busId.id}"
                       onclick="showConfirmBox(this.getAttribute('data-id'),
                                           this.getAttribute('data-name'))"
                       class="btn btn-mini">DELETE
                    </a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</div>
<script>

    function showConfirmBox(id, title) {

        $('#ticketTitle').text(title);
        $('#yesOption').attr('href', '/owner/${uId}/ticket/delete/' + id);
        $('#deleteConfirmId').modal('show');


    }
</script>
<div class="modal fade" id="deleteConfirmId" tabindex="-1" role="dialog" 
     aria-labelledby="modelTitleId" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete confirm</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Delete this ticket "<span id="ticketTitle"></span> " ?
            </div>
            <div class="modal-footer">
                <a id="yesOption" type="button" class="btn btn-danger" >Yes</a>
            </div>
        </div>
    </div>
</div>

