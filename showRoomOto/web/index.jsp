<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OTOZO</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <style>
        /* Header */
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
        
        /* Đảm bảo banner full width và full viền */
        .carousel {
            width: 100%;
            margin: 0;
            padding: 0;
            z-index: 1000; /* Z-index thấp hơn header để bị đè */
        }
        .carousel-inner {
            width: 100%;
        }
        .carousel-item {
            height: 700px; /* Điều chỉnh chiều cao theo nhu cầu */
            background-size: cover;
            background-position: center;
        }
        .carousel-caption {
           
            padding: 10px;
            border-radius: 5px;
            color: #ffffff; /* Văn bản màu trắng */
        }
        .btn-custom {
            background-color: #ffffff;
            border: 1px solid #ffffff;
            color: #000000;
            padding: 5px 15px; /* Thu nhỏ nút theo hình */
        }
        .btn-custom:hover {
            background-color: #e0e0e0;
            color: #000000;
        }
        /* Tùy chỉnh nút trượt */
        .carousel-control-prev, .carousel-control-next {
            width: 5%; /* Thu nhỏ kích thước nút */
        }
        .carousel-control-prev-icon, .carousel-control-next-icon {
            background-color: rgba(0, 0, 0, 0.5); /* Nền mờ cho nút */
            border-radius: 50%; /* Hình tròn */
        }
    </style>
</head>
<body>

<!-- Header (Đè lên banner) -->
<nav class="header navbar navbar-expand-lg">
    <div class="container-fluid">
        <!-- Logo -->
        <a class="navbar-brand" href="#">
            <img src="images/logoLam.jpg" alt="Zourney Logo"> <!-- Đường dẫn tới logo Zourney -->
        </a>
        
        <!-- Menu -->
        <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">HOME</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Car</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">BLOG</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">PAGE</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">CONTACT</a>
                </li>
            </ul>
        </div>
        
        <!-- Phần bên phải: Số điện thoại, tìm kiếm, người dùng -->
        <div class="header-right">
            <span class="phone-icon"><i class="bi bi-telephone-fill"></i> 0242 242 0777</span>
            <span class="search-icon"><i class="bi bi-search"></i></span>
            <span class="user-icon"><i class="bi bi-person"></i></span>
        </div>
    </div>
</nav>

<!-- Banner (Carousel trượt full viền, bị header đè) -->
<%@include file="includes/bannerMainPage.jsp" %>

<!-- Bootstrap JS, jQuery, và Bootstrap Icons -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

</body>
</html>