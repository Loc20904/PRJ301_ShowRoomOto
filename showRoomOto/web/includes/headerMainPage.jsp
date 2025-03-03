<%-- 
    Document   : headerMainPage
    Created on : Mar 2, 2025, 1:56:52 PM
    Author     : phuc2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <a class="nav-link active" aria-current="page" href="s_Car">HOME</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#car">Car</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="deverloping.jsp">BLOG</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="deverloping.jsp">PAGE</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="deverloping.jsp">CONTACT</a>
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
