<%-- 
    Document   : stats
    Created on : Aug 30, 2022, 9:22:49 PM
    Author     : 84344
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-info">THONG KE BAO CAO</h1>

<div class="row">
    <div class="col-md-6 col-xs-12">
        <table class="table">
            <tr>
                <th>Id</th>
                <th>Tên tuyến xe</th>
                <th>SL Chuyến xe</th>
            </tr>
            <c:forEach items="${countStats}" var="c">

                <tr>
                    <td>${c[0]}</td>
                    <td>${c[1]}<br/>${c[2]}</td>
                    <td>${c[3]}</td>
                </tr>

            </c:forEach>
        </table>
    </div>
    <div class="col-md-6 col-xs-12">
        <canvas id="myChart"></canvas>
    </div>
</div>









<p>${revenuStats.size()}</p>
<div class="row">
    <div class="col-md-6 col-xs-12 border-right border-5">
        <table class="table">
            <tr>
                <th>Tên tuyến xe</th>
                <th>SL Chuyến xe</th>
                <th>Doanh thu</th>
            </tr>
            <c:forEach items="${revenuStats}" var="c">
                <tr>
                    <td>${c[0]} <br/>${c[1]}</td>
                    <td>${c[2]}</td>
                    <td>
                        <fmt:formatNumber type="number" value="${c[3]}" maxFractionDigits="3" /> VND
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-6 col-xs-12 border">
        <c:url value="/owner/${uId}/stats" var="action" />
        <form action="${action}">
            <div class="mb-3 mt-3">
                <label for="quarter" class="form-label fw-bold bg-dark text-light">Nhập quý</label>
                <select class="form-control border border-1 bg-white" id="quarter" placeholder="Enter quarter" name="quarter">
                    <c:forEach begin="1" end="4" var="i">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3 border border-1 ">
                <label for="year" class="form-label">Year</label>
                <input type="number" min="2000" class="form-control fw-bold bg-white" id="year" placeholder="Nhập năm" name="year">
            </div>
           
            <button type="submit" class="btn btn-success col-6">Xem</button>
        </form>
        <canvas id="myChart2"></canvas>
    </div>
</div>








<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    window.onload = function () {
        let data = [];
        let labels = [];
        let data2 = [];
        let labels2 = [];
        let lbline = "";
    <c:forEach items="${countStats}" var="c">
        lbline = "${c[1]} - ${c[2]}";
                data.push('${c[3]}');
                labels.push(lbline);
    </c:forEach>

    <c:forEach items="${revenuStats}" var="c">
                lbline = "${c[0]} - ${c[1]}";
                data2.push(${c[3]});
                labels2.push(lbline);
    </c:forEach>
                revenueStats(labels2, data2);
                cateStats(labels, data);

            };


            function cateStats(labels, data) {
                const ctx = document.getElementById('myChart').getContext('2d');
                const myChart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: labels,
                        datasets: [{
                                label: 'Thong ke so san pham theo danh muc',
                                data: data,
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)',
                                    'rgba(54, 162, 235, 0.2)',
                                    'rgba(255, 206, 86, 0.2)',
                                    'rgba(75, 192, 192, 0.2)',
                                    'rgba(153, 102, 255, 0.2)',
                                    'rgba(255, 159, 64, 0.2)'
                                ],
                                borderColor: [
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(54, 162, 235, 1)',
                                    'rgba(255, 206, 86, 1)',
                                    'rgba(75, 192, 192, 1)',
                                    'rgba(153, 102, 255, 1)',
                                    'rgba(255, 159, 64, 1)'
                                ],
                                borderWidth: 1
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            }

            function revenueStats(labels, data) {
                const ctx = document.getElementById('myChart2').getContext('2d');
                const myChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                                label: 'Thong ke doanh thu',
                                data: data,
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)',
                                    'rgba(54, 162, 235, 0.2)',
                                    'rgba(255, 206, 86, 0.2)',
                                    'rgba(75, 192, 192, 0.2)',
                                    'rgba(153, 102, 255, 0.2)',
                                    'rgba(255, 159, 64, 0.2)'
                                ],
                                borderColor: [
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(54, 162, 235, 1)',
                                    'rgba(255, 206, 86, 1)',
                                    'rgba(75, 192, 192, 1)',
                                    'rgba(153, 102, 255, 1)',
                                    'rgba(255, 159, 64, 1)'
                                ],
                                borderWidth: 1
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            }
</script>