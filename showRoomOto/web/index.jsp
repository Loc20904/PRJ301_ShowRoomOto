<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>OTOZO</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <link rel="stylesheet" href="css/CSS_index.css"/>
        <link rel="stylesheet" href="css/CSS_header.css"/>
        <link rel="stylesheet" href="css/CSS_chatbox.css">
    </head>
    <body>
        <%@include file="includes/headerMainPage.jsp" %>
        <%@include file="includes/bannerMainPage.jsp" %>
        <%@include file="includes/chatBox.jsp" %>

        <section id="car">
            <div class="container mt-5" id="list">
                <div class="row">
                    <div class="col-md-2" id="fillt">
                        <div class="card p-3 shadow-lg">
                            <h5 class="text-center"><i class="bi bi-funnel"></i> Filter</h5>

                            <h6 class="mt-3"><i class="bi bi-car-front"></i> Brand</h6>
                            <form method="get" action="s_Car">
                                <select name="brand" class="form-select no-arrow border-primary" onchange="this.form.submit()">
                                    <option value="allBrand" ${sessionScope.selectedBrand == 'allBrand' ? 'selected' : ''}>All Brands</option>
                                    <option value="VinFast" ${sessionScope.selectedBrand == 'VinFast' ? 'selected' : ''}>VinFast</option>
                                    <option value="Toyota" ${sessionScope.selectedBrand == 'Toyota' ? 'selected' : ''}>Toyota</option>
                                    <option value="Honda" ${sessionScope.selectedBrand == 'Honda' ? 'selected' : ''}>Honda</option>
                                    <option value="Ford" ${sessionScope.selectedBrand == 'Ford' ? 'selected' : ''}>Ford</option>
                                    <option value="Hyundai" ${sessionScope.selectedBrand == 'Hyundai' ? 'selected' : ''}>Hyundai</option>
                                    <option value="Kia" ${sessionScopese.lectedBrand == 'Kia' ? 'selected' : ''}>Kia</option>
                                    <option value="Mazda" ${sessionScope.selectedBrand == 'Mazda' ? 'selected' : ''}>Mazda</option>
                                    <option value="Nissan" ${sessionScopese.lectedBrand == 'Nissan' ? 'selected' : ''}>Nissan</option>
                                    <option value="Chevrolet" ${sessionScope.selectedBrand == 'Chevrolet' ? 'selected' : ''}>Chevrolet</option>
                                    <option value="Ferrari" ${sessionScopese.lectedBrand == 'Ferrari' ? 'selected' : ''}>Ferrari</option>
                                    <option value="Lamborghini" ${sessionScope.selectedBrand == 'Lamborghini' ? 'selected' : ''}>Lamborghini</option>
                                    <option value="Porsche" ${sessionScope.selectedBrand == 'Porsche' ? 'selected' : ''}>Porsche</option>
                                </select>
                            </form>

                            <h6 class="mt-3"><i class="bi bi-tags"></i> Price</h6>
                            <form method="get" action="s_Car">
                                <select name="price" class="form-select border-success" onchange="this.form.submit()">
                                    <option value="">Select Price</option>
                                    <option value="asc" ${sessionScope.selectedPrice == 'asc' ? 'selected' : ''}>Low to High</option>
                                    <option value="desc" ${sessionScope.selectedPrice == 'desc' ? 'selected' : ''}>High to Low</option>
                                </select>
                            </form>
                        </div>
                    </div>


                    <div class="col-md-10">
                        <div class="row">
                            <c:forEach var="f" items="${sessionScope.listR}">
                                <div class="col-md-4">
                                    <div class="card" id="${f.carID}">
                                        <div class="card-body">
                                            <img src="images/${f.imageURL}" class="card-img-top img-uniform" alt="Car Image">
                                            <h5 class="card-title">${f.carName}</h5>
                                            <p class="card-text">Description: ${f.description}</p>
                                            <p class="card-text">Price: ${f.price} $</p>
                                            <form method="post" action="s_Car">
                                                <input type="hidden" name="carID" value="${f.carID}" />
                                                <button class="btn btn-primary" type="submit">Book Now</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                        <!-- Thanh phân trang -->
                        <nav>
                            <ul class="pagination justify-content-center mt-4">
                                <c:if test="${sessionScope.currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="s_Car?page=${sessionScope.currentPage - 1}#car">Previous</a>
                                    </li>
                                </c:if>

                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <li class="page-item ${i == sessionScope.currentPage ? 'active' : ''}">
                                        <a class="page-link" href="s_Car?page=${i}#car">${i}</a>
                                    </li>
                                </c:forEach>

                                <c:if test="${sessionScope.currentPage < sessionScope.totalPages}">
                                    <li class="page-item">
                                        <a class="page-link" href="s_Car?page=${sessionScope.currentPage + 1}#car">Next</a </li>
                                    </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <script src="js/JS_chatBox.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    </body>
</html>