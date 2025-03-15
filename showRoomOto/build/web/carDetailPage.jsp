<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Car Detail</title>
        <link rel="stylesheet" href="css/CSS_CarDetail.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <style>
            /* Dùng pseudo-element để hiển thị ảnh car và làm mờ (hoặc giữ nguyên tuỳ ý) */
            .hero::before {
                content: "";
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                /* Lấy ảnh từ car.imageURL */
                background: url('images/${car.imageURL}') no-repeat center center/cover;
                /* Nếu muốn làm mờ, bỏ comment dòng dưới */
                /* filter: blur(2px); */
            }
            .header {
    position: fixed;
    top: 0;
    width: 100%;
    background: url('images/header-background.jpg') no-repeat center center; /* Đường dẫn ảnh núi */
    background-size: cover;
    z-index: 1050; /* Z-index cao hơn banner để đè lên */
    padding: 10px 0;
    transition: background-color 0.3s ease; /* Hiệu ứng chuyển màu khi hover */
    border-bottom: #ffffff solid 0.05px;
    padding-bottom: 20px;
    padding-top: 15px;
}
.header:hover {
    background: linear-gradient(45deg, #b0b0b0, #808080, #a0a0a0); /* Màu kim loại khi hover */
}

.navbar-brand img {
    height: 30px; /* Điều chỉnh kích thước logo */
    margin-left: 20px; /* Thêm khoảng cách bên trái cho logo */
}

.nav-link {
    color: #ffffff !important; /* Văn bản trắng */
    font-weight: bold;
    margin: 0 15px;
    text-transform: uppercase; /* Chuyển chữ in hoa (tương tự hình) */
}
.nav-link:hover {
    color: #ff8c00 !important; /* Màu cam khi hover (tương tự logo) */
}

.header-right {
    display: flex;
    align-items: center;
    margin-right: 20px; /* Thêm khoảng cách bên phải */
}

.phone-icon, .search-icon, .user-icon {
    color: #ffffff;
    margin-left: 15px;
    cursor: pointer;
    font-size: 1.2rem; /* Tăng kích thước biểu tượng */
}
.phone-icon:hover, .search-icon:hover, .user-icon:hover {
    color: #ff8c00; /* Màu cam khi hover */
}
        </style>
        <link rel="stylesheet" href="css/CSS_chatbox.css"/>
    </head>
    <body>
        <%@include file="includes/headerMainPage.jsp" %>
        <%@include file="includes/chatBox.jsp" %>
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
        <!-- Bootstrap JS, jQuery, và Bootstrap Icons -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
        <<script src="js/JS_chatBox.js"></script>
    </body>
</html>
