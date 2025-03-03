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

        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

        <link rel="stylesheet" href="css/CSS_index.css"/>
        <link rel="stylesheet" href="css/CSS_header.css"/>
    </head>
    <body>

        <!-- Header (Đè lên banner) -->
        <%@include file="includes/headerMainPage.jsp" %>

        <!-- Banner (Carousel trượt full viền, bị header đè) -->
        <%@include file="includes/bannerMainPage.jsp" %>
        <section id="car">
        <div class="container mt-5" id="list">
            <div class="row">
                <c:forEach var="f" items="${sessionScope.listR}">
                    <div class="col-md-4">
                        <div class="card" id="${f.carID}">
                            <div class="card-body">
                                <img src="images/${f.imageURL}" class="card-img-top img-uniform" alt="Car Image" >
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
        </div>
            </section>
        <!-- Bootstrap JS, jQuery, và Bootstrap Icons -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

    </body>
</html>
