<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="repository.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Booking Confirmation</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <link rel="stylesheet" href="css/CSS_header.css"/>
        <style>
            body{
                background-color: #808080;
            }
        </style>
    </head>
    <body>
        <%@include file="includes/headerMainPage.jsp" %>

        <div class="container mt-5">
            <div class="card shadow p-4">
                <h2 class="text-center mb-4">Booking Confirmation</h2>

                <c:if test="${not empty sessionScope.user}">
                    <c:set var="booking" value="${sessionScope.booking}" />

                    <div class="mb-4">
                        <ul class="list-group">
                            <li class="list-group-item"><strong>Car Name:</strong> ${booking.car.carName}</li>
                            <li class="list-group-item"><strong>Start Date:</strong> ${booking.startDate}</li>
                            <li class="list-group-item"><strong>Employee:</strong> ${booking.employee.fullName}</li>
                                <c:if test="${booking.slot==1}">
                                <li class="list-group-item"><strong>Time:7h-12h</strong> </li>
                                </c:if>
                                <c:if test="${booking.slot==2}">
                                <li class="list-group-item"><strong>Time:14h-22h</strong> </li>
                                </c:if>
                        </ul>
                    </div>

                    <div class="alert alert-success text-center" role="alert">
                        <i class="bi bi-check-circle-fill"></i> Your booking is confirmed!
                    </div>
                </c:if>

                <div class="text-center">
                    <a href="s_Car" class="btn btn-primary mt-3"><i class="bi bi-house-door"></i> Back to Home</a>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    </body>
</html>
