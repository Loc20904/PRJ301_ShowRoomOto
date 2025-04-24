<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Auto Showroom Blog</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Custom Header CSS -->
    <link rel="stylesheet" href="css/CSS_header.css">
    <link rel="stylesheet" href="css/CSS_footer.css">
    <!-- Inline Styles -->
    <style>
        body {
            background: url('images/loginback.jpg') no-repeat center center/cover;
            min-height: 100vh;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
        }

        /* Header Placeholder */
        .header-placeholder {
            min-height: 80px; /* Dự phòng chiều cao tối thiểu cho header */
        }

        /* Blog Container */
        .blog-container {
            max-width: 1200px;
            margin: 20px auto 0; /* Khoảng cách từ header xuống */
            padding: 40px 15px;
            background: rgba(255, 255, 255, 0.9); /* Nền trắng mờ */
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            position: relative;
            z-index: 1; /* Đảm bảo nằm dưới header nếu header fixed */
        }

        .blog-title {
            text-align: center;
            color: #333;
            font-size: 2.5rem;
            font-weight: bold;
            margin-bottom: 40px;
            text-transform: uppercase;
        }

        .blog-card {
            background: #fff;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
            height: 100%;
            display: flex;
            flex-direction: column;
        }

        .blog-card:hover {
            transform: translateY(-5px);
        }

        .blog-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }

        .blog-card-body {
            padding: 20px;
            flex-grow: 1;
        }

        .blog-card-meta {
            color: #6c757d;
            font-size: 0.9rem;
            margin-bottom: 10px;
        }

        .blog-card-title {
            font-size: 1.25rem;
            font-weight: bold;
            color: #333;
            margin-bottom: 15px;
        }

        .blog-card-excerpt {
            color: #555;
            font-size: 1rem;
            line-height: 1.5;
            margin-bottom: 0;
        }

        /* Pagination */
        .pagination {
            justify-content: center;
            margin-top: 40px;
        }

        /* Footer */
        #dev-footer {
            position: relative;
            height: 150px;
            margin-top: 40px;
            overflow: hidden;
        }

        #dev-footer::before {
            content: "";
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background: url("data:image/svg+xml,%3Csvg viewBox='0 0 1200 120' xmlns='http://www.w3.org/2000/svg' preserveAspectRatio='none' style='fill:rgba(0,0,0,0.1);'%3E%3Cpath d='M0,10 C150,40 350,-30 600,30 C850,90 1050,10 1200,40 V120 H0 Z'/%3E%3C/svg%3E");
            background-size: cover;
            opacity: 0.15;
            z-index: 1;
        }

        /* Responsive Adjustments */
        @media (max-width: 768px) {
            .blog-title {
                font-size: 2rem;
            }
            .blog-card img {
                height: 150px;
            }
            .header-placeholder {
                min-height: 60px; /* Giảm chiều cao trên mobile */
            }
        }
    </style>
</head>
<body>
    <!-- Header Include -->
    <%@include file="includes/headerMainPage.jsp" %>

    <!-- Header Placeholder -->
    <div class="header-placeholder"></div>

    <!-- Blog Section -->
    <section class="blog-container">
        <h1 class="blog-title">Tin Tức & Blog Ô Tô</h1>
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
            <!-- Blog Post 1 -->
            <div class="col">
                <div class="blog-card">
                   <img src="${pageContext.request.contextPath}/images/blog-2.jpg" alt="Car in desert landscape">
                    <div class="blog-card-body">
                        <span class="blog-card-meta">All Turbo | April 20, 2023</span>
                        <h3 class="blog-card-title">Công Nghệ Đợi Chờ Lâu Đời Có Thể Thay Đổi Thế Giới</h3>
                        <p class="blog-card-excerpt">Khám phá công nghệ đột phá sắp ra mắt, hứa hẹn thay đổi cách chúng ta lái xe và bảo vệ môi trường...</p>
                    </div>
                </div>
            </div>

            <!-- Blog Post 2 -->
            <div class="col">
                <div class="blog-card">
                    <img src="${pageContext.request.contextPath}/images/lambo.jpg" alt="Car in desert landscape">
                    <div class="blog-card-body">
                        <span class="blog-card-meta">All Turbo | April 20, 2023</span>
                        <h3 class="blog-card-title">Top 5 Xe Thể Thao Đáng Mua Nhất 2023</h3>
                        <p class="blog-card-excerpt">Danh sách những mẫu xe thể thao nổi bật với hiệu suất vượt trội và thiết kế ấn tượng...</p>
                    </div>
                </div>
            </div>

            <!-- Blog Post 3 -->
            <div class="col">
                <div class="blog-card">
                    <img src="${pageContext.request.contextPath}/images/Ford Focus.jpg" alt="Car in desert landscape">
                    <div class="blog-card-body">
                        <span class="blog-card-meta">All Turbo | April 20, 2023</span>
                        <h3 class="blog-card-title">Hướng Dẫn Chọn SUV Phù Hợp Gia Đình</h3>
                        <p class="blog-card-excerpt">Tìm hiểu các yếu tố quan trọng khi chọn SUV: không gian, an toàn, và tiết kiệm nhiên liệu...</p>
                    </div>
                </div>
            </div>

            <!-- Blog Post 4 -->
            <div class="col">
                <div class="blog-card">
                    <img src="${pageContext.request.contextPath}/images/VinFast President.jpg" alt="Car in desert landscape">
                    <div class="blog-card-body">
                        <span class="blog-card-meta">All Turbo | April 20, 2023</span>
                        <h3 class="blog-card-title">Cách Tiết Kiệm Nhiên Liệu Xe Hơi</h3>
                        <p class="blog-card-excerpt">Bí quyết đơn giản giúp bạn giảm chi phí nhiên liệu mà vẫn duy trì hiệu suất xe...</p>
                    </div>
                </div>
            </div>

            <!-- Blog Post 5 -->
            <div class="col">
                <div class="blog-card">
                    <img src="${pageContext.request.contextPath}/images/Toyota Camry.jpg" alt="Car in desert landscape">
                    <div class="blog-card-body">
                        <span class="blog-card-meta">All Turbo | April 20, 2023</span>
                        <h3 class="blog-card-title">Xu Hướng Xe Điện Cũ Tại Việt Nam</h3>
                        <p class="blog-card-excerpt">Tại sao xe điện cũ đang trở thành lựa chọn phổ biến tại thị trường Việt Nam...</p>
                    </div>
                </div>
            </div>

            <!-- Blog Post 6 -->
            <div class="col">
                <div class="blog-card">
                    <img src="${pageContext.request.contextPath}/images/Honda Civic.jpg" alt="Car in desert landscape">
                    <div class="blog-card-body">
                        <span class="blog-card-meta">All Turbo | April 20, 2023</span>
                        <h3 class="blog-card-title">Xe Điện Tốt Nhất Dưới 1 Tỷ</h3>
                        <p class="blog-card-excerpt">Danh sách xe điện giá rẻ với hiệu suất cao, phù hợp cho người dùng Việt...</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Pagination -->
        <nav aria-label="Blog pagination" class="pagination">
            <ul class="pagination">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Trước</a>
                </li>
                <li class="page-item active"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">Sau</a>
                </li>
            </ul>
        </nav>
    </section>
    
    <!-- Footer --> 
    <div id="dev-footer"></div>
 <%@include file="includes/footer.jsp" %>
    <!-- External Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>