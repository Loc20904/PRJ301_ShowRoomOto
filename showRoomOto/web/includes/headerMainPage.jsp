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
            <img src="https://www.lamborghini.com/sites/it-en/files/DAM/lamborghini/logos/2024/03_26/logo_header_01.svg" alt="Zourney Logo"> <!-- Đường dẫn tới logo Zourney -->
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
            <span class="search-icon" onclick="toggleSearch()"><i class="bi bi-search"></i></span>
            <div class="search-box hidden" id="searchBox">
                <input type="text" class="form-control sizesearch" id="searchInput" placeholder="Search your car" onkeyup="searchCar()">
                <div id="carResults"></div>
            </div>
            <span class="user-icon"><i class="bi bi-person"></i></span>
        </div>
    </div>
    <script>
        function toggleSearch() {
            let searchBox = document.getElementById("searchBox");
            searchBox.classList.toggle("hidden");
        }
    </script>
    <script src="js/JS_search.js"></script>
</nav>
