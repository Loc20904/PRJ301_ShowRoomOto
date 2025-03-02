<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Car Detail</title>
        <link rel="stylesheet" href="css/CSS_CarDetail.css"/>
    </head>
    <body>

        <!-- Lấy Car bằng JSTL/EL -->
        <jsp:useBean id="car" class="model.Car" scope="request" />
        <!-- Hero section -->
        <div class="hero">
            <!-- Nếu muốn overlay, bỏ comment
            <div class="hero-overlay"></div>
            -->
            <div class="hero-content">
                <!-- Hiển thị tên xe -->
                <h1>${car.carName}</h1>
                <!-- Hiển thị mô tả -->
                <p>${car.description}</p>
            </div>
        </div>

        <!-- Phần nội dung chi tiết -->
        <div class="details">
            <h2>Thông tin chi tiết</h2>
            <table>
                <tr>
                    <th>Car Name</th>
                    <td>${car.carName}</td>
                </tr>
                <tr>
                    <th>Type</th>
                    <td>${car.type}</td>
                </tr>
                <tr>
                    <th>Brand</th>
                    <td>${car.brand}</td>
                </tr>
                <tr>
                    <th>Description</th>
                    <td>${car.description}</td>
                </tr>
                <tr>
                    <th>Year of Manufacture</th>
                    <td>${car.yearOfManufacture}</td>
                </tr>
                <tr>
                    <th>Weight</th>
                    <td>${car.weight} kg</td>
                </tr>
                <tr>
                    <th>Price</th>
                    <td>$${car.price}</td>
                </tr>
            </table>
        </div>
    </body>
</html>
