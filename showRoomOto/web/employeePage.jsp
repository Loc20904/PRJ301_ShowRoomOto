<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Employee Page</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <link rel="stylesheet" href="css/CSS_employeePage.css"/>
        <link rel="stylesheet" href="css/CSS_header.css"/>
        <style>
            .table-responsive {
                max-height: 400px; /* Giới hạn chiều cao bảng */
                overflow-y: auto;  /* Bật cuộn dọc */
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 8px;
                text-align: left;
            }

            .table thead {
                position: sticky;
                top: 0;
                background: #343a40;
                color: white;
                z-index: 1;
            }
        </style>
    </head>
    <body>

        <%@include file="includes/headerMainPage.jsp" %>

        <h2>Danh Sách Booking của bạn</h2>
        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>Nhân Viên</th>
                        <th>Khách Hàng</th>
                        <th>Xe</th>
                        <th>Ngày Đặt</th>
                        <th>Ngày Bắt Đầu</th>
                        <th>Ngày Kết Thúc</th>
                        <th>Slot</th>
                        <th>Trạng Thái</th>
                        <th>Xác nhận booking</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="booking" items="${requestScope.lbk}">
                        <tr>
                            <td>${booking.employee.fullName}</td>
                            <td>${booking.customer.fullName}</td>
                            <td>${booking.car.carName}</td>
                            <td>${booking.bookingDate}</td>
                            <td>${booking.startDate}</td>
                            <td>${booking.endDate}</td>
                            <td>${booking.slot}</td>
                            <td>${booking.status}</td>
                            <td>
                                <c:if test="${booking.status == 'pending'}">
                                    <form action="s_bookingStatus" method="post" style="display:inline;">
                                        <input type="hidden" name="bookingId" value="${booking.bookingID}">
                                        <button type="submit" class="btn btn-success btn-sm">Finish</button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    </body>
</html>
