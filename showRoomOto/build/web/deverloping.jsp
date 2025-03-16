<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                background: url('images/loginback.jpg') no-repeat center center/cover;
            }
            #dev-header {
                position: relative;
                width: 100%;
                /*height: 50px;  Chiều cao tùy chỉnh */
                /* Nền tối */

                display: flex;
                align-items: center;
                justify-content: center;
                /*overflow: hidden;  Nếu SVG vượt khung */
                color: #fff;
            }
            @keyframes gradientShift {
                0% {
                    background-position: 0% 50%;
                }
                50% {
                    background-position: 100% 50%;
                }
                100% {
                    background-position: 0% 50%;
                }
            }

            /* Thiết lập vùng header */
            #dev-header {
                position: relative;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 250px; /* Điều chỉnh kích thước phù hợp */
                margin-top: 400px;
                overflow: hidden;
            }

            /* SVG nền gợn sóng */
            #dev-header::before {
                content: "";
                position: absolute;
                width: 100%;
                height: 100%;
                top: 0;
                left: 0;
                background: url("data:image/svg+xml,%3Csvg viewBox='0 0 1200 120' xmlns='http://www.w3.org/2000/svg' preserveAspectRatio='none' style='fill:rgba(0,0,0,0.1);'%3E%3Cpath d='M0,10 C150,40 350,-30 600,30 C850,90 1050,10 1200,40 V120 H0 Z'/%3E%3C/svg%3E");
                background-size: cover;
                opacity: 0.15; /* Làm mờ SVG */
                z-index: 1;
            }

            /* Tiêu đề gradient động */
            #dev-header h2 {
                position: relative;
                z-index: 2; /* Nổi lên trên */
                font-size: 2rem;
                margin: 0;
                padding: 10px 20px;
                display: inline-block;
                background: linear-gradient(90deg, #ff8c00, #ffd700, #32cd32, #00ced1, #1e90ff, #9370db);
                background-size: 400% 400%;
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                animation: gradientShift 5s linear infinite;
                font-weight: bold;
                text-transform: uppercase;
            }

        </style>
    </head>
    <body>
        <%@include file="includes/headerMainPage.jsp" %>

        <div id="dev-header" style="margin-top: 400px;">
            <h2>Tính năng đang được phát triển</h2>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

    </body>
</html>
