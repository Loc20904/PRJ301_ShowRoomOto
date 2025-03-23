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
    <link rel="stylesheet" href="css/bookingConfirmation.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Roboto:wght@300;400&display=swap" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #1a1a1a 0%, #4b4b4b 100%);
            font-family: 'Roboto', sans-serif;
            color: #ffffff;
            min-height: 100vh;
            margin: 0;
        }

        .container {
            padding-top: 80px;
            padding-bottom: 80px;
        }

        .card {
            background: rgba(255, 255, 255, 0.05);
            border: none;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5);
            backdrop-filter: blur(10px);
            padding: 40px;
            transition: transform 0.3s ease;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        h2 {
            font-family: 'Playfair Display', serif;
            font-size: 2.5rem;
            color: #ffd700;
            text-align: center;
            margin-bottom: 30px;
            letter-spacing: 1px;
        }

        .list-group-item {
            background: rgba(255, 255, 255, 0.1);
            border: none;
            color: #e0e0e0;
            font-size: 1.1rem;
            padding: 15px 20px;
            margin-bottom: 10px;
            border-radius: 10px;
            transition: background 0.3s ease;
        }

        .list-group-item strong {
            color: #ffd700;
            margin-right: 10px;
        }

        .list-group-item:hover {
            background: rgba(255, 255, 255, 0.2);
        }

        .alert-success {
            background: rgba(40, 167, 69, 0.2);
            border: none;
            color: #28a745;
            font-size: 1.2rem;
            padding: 20px;
            border-radius: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
        }

        .btn-primary {
            background: linear-gradient(90deg, #ffd700, #ffaa00);
            border: none;
            padding: 12px 30px;
            font-size: 1.1rem;
            border-radius: 25px;
            text-transform: uppercase;
            letter-spacing: 1px;
            transition: all 0.3s ease;
        }

        .btn-primary:hover {
            background: linear-gradient(90deg, #ffaa00, #ffd700);
            transform: scale(1.05);
        }

        .bi {
            font-size: 1.3rem;
            margin-right: 8px;
        }
    </style>
</head>
<body>
    <%@include file="includes/headerMainPage.jsp" %>

    <div class="container mt-5">
        <div class="card shadow p-4">
            <h2>Booking Confirmation</h2>

            <c:if test="${not empty sessionScope.user}">
                <c:set var="booking" value="${sessionScope.booking}" />

                <div class="mb-4">
                    <ul class="list-group">
                        <li class="list-group-item"><strong>Car Name:</strong> ${booking.car.carName}</li>
                        <li class="list-group-item"><strong>Start Date:</strong> ${booking.startDate}</li>
                        <li class="list-group-item"><strong>End Date:</strong> ${booking.endDate}</li>
                        <li class="list-group-item"><strong>Employee:</strong> ${booking.employee.fullName}</li>
                        <li class="list-group-item"><strong>Address:</strong> ${booking.address}</li>
                        <li class="list-group-item"><strong>Phone Number:</strong> ${booking.phoneNumber}</li>
                        <c:if test="${booking.slot == 1}">
                            <li class="list-group-item"><strong>Time:</strong> 7h - 12h</li>
                        </c:if>
                        <c:if test="${booking.slot == 2}">
                            <li class="list-group-item"><strong>Time:</strong> 14h - 22h</li>
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