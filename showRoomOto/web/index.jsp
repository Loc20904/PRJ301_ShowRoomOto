<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show room oto</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <style>
        /* Đảm bảo banner full width và full viền */
        .carousel {
            width: 100%;
            margin: 0;
            padding: 0;
        }
        .carousel-inner {
            width: 100%;
        }
        .carousel-item {
            height: 600px; /* Điều chỉnh chiều cao theo nhu cầu */
            background-size: cover;
            background-position: center;
        }
        .carousel-caption {
            /*background-color: rgba(77, 77, 77, 0.8);  Nền xám cho text, theo hình ảnh tham khảo */
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

<!-- Carousel (Banner trượt full viền) -->
<%@include file="includes/bannerMainPage.jsp" %>

<!-- Bootstrap JS và jQuery -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

</body>
</html>