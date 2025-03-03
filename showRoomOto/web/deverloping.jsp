<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deverloping</title>
      <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <link rel="stylesheet" href="css/CSS_header.css"/>
    <style>
        /* Style cho header */
        body{
            background: url('images/banner1.jpg') no-repeat center center/cover;
        }
        .dev-header {
            position: relative;
            width: 100%;
            height: 200px; /* Chiều cao tùy chỉnh */
             /* Nền tối */
            
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden; /* Nếu SVG vượt khung */
            color: #fff;
        }
        .dev-header h2 {
            position: relative;
            z-index: 2; /* Để text nổi lên trên SVG */
            font-size: 1.8rem;
            margin: 0;
            
        }
        /* Style cho SVG */
        .dev-header svg {
            position: absolute;
            top: 0; 
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
            z-index: 1; /* Dưới text */
            opacity: 0.15; /* Độ mờ của SVG */
        }
        .dev-header h2 {
    font-size: 1.8rem;
    margin: 0;
    /* Tạo background gradient với nhiều màu */
    background: linear-gradient(90deg, #ff8c00, #ffd700, #32cd32, #00ced1, #1e90ff, #9370db);
    background-size: 200%;
    /* Dùng background clip để áp dụng gradient cho text */
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    animation: gradientShift 5s linear infinite;
}

@keyframes gradientShift {
    0% {
        background-position: 0% 50%;
    }
    100% {
        background-position: 100% 50%;
    }
}

    </style>
</head>
<body>
    <%@include file="includes/headerMainPage.jsp" %>
    
    <div class="dev-header" style="margin-top: 450px;">
        <h2>Tính năng đang được phát triển</h2>
    </div>
   <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

</body>
</html>
