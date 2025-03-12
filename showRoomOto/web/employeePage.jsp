<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Employee Page</title>
    <style>
        /* Reset CSS */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
    background: linear-gradient(145deg, #1f1f1f 0%, #8c8c8c 100%);
    text-align: center;
    padding: 20px;
    height: 100vh; /* Đảm bảo nền phủ toàn bộ màn hình */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}


        h2 {
            font-size: 28px;
            margin-bottom: 20px;
            color: #e63946;
            text-transform: uppercase;
            font-weight: bold;
        }

        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background: #f9f9f9;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0px 0px 10px rgba(255, 255, 255, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #444;
        }

        th {
            background-color: #e63946;
            color: white;
            font-size: 16px;
            text-transform: uppercase;
        }

        tr:nth-child(even) {
            background-color: #252525;
        }

        tr:hover {
            background-color: #333;
            color: white;
            transition: 0.3s;
        }

        td {
            font-size: 14px;
        }

        /* Button Style */
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            font-size: 14px;
            text-transform: uppercase;
            text-decoration: none;
            color: white;
            background-color: #e63946;
            border-radius: 5px;
            transition: 0.3s;
        }

        .btn:hover {
            background-color: #ff3f4d;
        }
    </style>
</head>
<body>

    <h2>Danh Sách Booking của bạn</h2>

    <table>
        <thead>
            <tr>
                <th>Nhân Viên</th>
                <th>Khách Hàng</th>
                <th>Xe</th>
                <th>Ngày Đặt</th>
                <th>Ngày Bắt Đầu</th>
                <th>Ngày Kết Thúc</th>
                <th>Slot</th>
                <th>Trạng Thái</th>
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
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
