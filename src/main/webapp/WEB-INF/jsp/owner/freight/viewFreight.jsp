<%-- 
    Document   : viewFreight
    Created on : Aug 30, 2022, 1:32:37 PM
    Author     : 84344
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<br/>
<span class="d-flex"><h2 class="col-12" >THÔNG TIN GỬI NHẬN HÀNG </h2>
</span>

<a class="btn btn-secondary" href="<c:url value="/owner/${uId}/freight/add" />">THÊM ĐƠN HÀNG</a>


<div style="font-family: 'Times New Roman';color: black;" class="">
    <c:forEach items="${freight}" var="fr">
        <div style="display: flex; align-items:center; margin: 0px;padding: 0px; padding: 10px;" 
             class="well">	  
            <div class="col-8">
                <p>Từ : ${fr.sendName}</p>
                <p>Ngày gửi: <fmt:formatDate pattern="dd-MM-yyyy" value="${fr.sendDate}" /> - 
                    Ngày nhận:  <fmt:formatDate pattern="dd-MM-yyyy" value="${fr.receiveDate}" /> 
                </p>
                <div class="btn-group">
                    <c:if test="${fr.complete==0}">
                        <a style="border: 1px solid blue;color: blue;" 
                           data-id="${fr.id}"
                           data-name="${fr.id}"
                           onclick="showConfirmBoxSend(this.getAttribute('data-id'),
                                           this.getAttribute('data-name'))"
                           class="btn btn-mini">GỬI HÀNG
                        </a>
                    </c:if>
                    <c:if test="${fr.complete==1}">
                        <p class="btn btn-mini">[ĐÃ GIAO HÀNG]</p>
                    </c:if>
                </div>
            </div>
            <div style="display: flex; justify-content:center;height: 100%" class="col-4 ">

                <div class="btn-group">
                    <a style="border: 1px solid green;color:green;" 
                       href="<c:url value="/owner/${uId}/freight/info/${fr.id}" />" class="btn btn-mini">VIEW
                    </a>

                    <a style="border: 1px solid green;color:green;" 
                       href="<c:url value="/owner/${uId}/freight/update/${fr.id}" />" class="btn btn-mini">EDIT
                    </a>

                    <a style="border: 1px solid red;color:red;" 
                       data-id="${fr.id}"
                       data-name="${fr.sendName}"
                       onclick="showConfirmBox(this.getAttribute('data-id'),
                                       this.getAttribute('data-name'))"
                       class="btn btn-mini">DELETE
                    </a>


                </div>

            </div>
        </div>
    </c:forEach>
</div>
<script>

    function showConfirmBox(id, title) {

        $('#newsTitle').text(title);
        $('#yesOption').attr('href', '/owner/${uId}/freight/delete/' + id
                );
        $('#deleteConfirmId').modal('show');


    }
    function showConfirmBoxSend(id, title) {

        $('#sendTitle').text(title);
        $('#yesOptionSend').attr('href', '/owner/${uId}/freight/send/' + id
                );
        $('#sendConfirmId').modal('show');


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
                Delete this freight from "<span id="newsTitle"></span> " ?
            </div>
            <div class="modal-footer">
                <a id="yesOption" type="button" class="btn btn-danger" >Yes</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="sendConfirmId" tabindex="-1" role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Send confirm</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                GỬI ĐƠN HÀNG " <span id="sendTitle"></span> " ?
            </div>
            <div class="modal-footer">
                <a id="yesOptionSend" type="button" class="btn btn-success" >Yes</a>
            </div>
        </div>
    </div>
</div>
