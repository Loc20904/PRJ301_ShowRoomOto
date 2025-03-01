<%-- 
    Document   : bannerMainPage
    Created on : Mar 1, 2025, 4:41:48 PM
    Author     : phuc2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="tourCarousel" class="carousel slide" data-bs-ride="carousel">
    <!-- Slides (Các banner) -->
    <div class="carousel-inner">
        <!-- Slide 1: Venice -->
        <div class="carousel-item active" style="background-image: url('images/banner1.jpg');">
            <div class="carousel-caption d-none d-md-block">
                <h1>Amazing lamborghini</h1>
                <p>1023 reviews ★★★★★ 4.5/5</p>
                <a href="#" class="btn btn-custom">Take Me There →</a>
            </div>
        </div>

        <!-- Slide 2: Paris -->
        <div class="carousel-item" style="background-image: url('images/banner2.jpg');">
            <div class="carousel-caption d-none d-md-block">
                <h1>Try your favorite Car</h1>
                <p>850 reviews ★★★★★ 4.7/5</p>
                <a href="#" class="btn btn-custom">Take Me There →</a>
            </div>
        </div>

        <!-- Slide 3: Tokyo -->
        <div class="carousel-item" style="background-image: url('images/banner3.png');">
            <div class="carousel-caption d-none d-md-block">
                <h1>Suitable for all styles</h1>
                <p>1200 reviews ★★★★★ 4.6/5</p>
                <a href="#" class="btn btn-custom">Take Me There →</a>
            </div>
        </div>
    </div>

    <!-- Nút trượt trái/phải (không có indicators) -->
    <button class="carousel-control-prev" type="button" data-bs-target="#tourCarousel" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#tourCarousel" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
