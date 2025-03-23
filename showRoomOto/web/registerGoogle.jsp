<%-- 
    Document   : registerGoogle
    Created on : Mar 21, 2025, 9:31:54 PM
    Author     : phuc2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Complete Your Registration - Luxury Car Booking</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800&family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/registerGoogle.css">
</head>
<body>
    <!-- Video nền -->
    <video autoplay muted loop id="background-video">
        <source src="images/7020064_Car_Speed_3840x2160.mp4" type="video/mp4" >
        <p>Your browser does not support the video tag, or the video file could not be found at: videos/7020064_Car_Speed_3840x2160.mp4</p>
    </video>

    <div class="register-container">
        <!-- Logo -->
        <div class="logo">
            <img src="https://www.lamborghini.com/sites/it-en/files/DAM/lamborghini/logos/2024/03_26/logo_header_01.svg" alt="Luxury Car Booking Logo">
        </div>
        <h2>Complete Your Registration</h2>
        <c:if test="${requestScope.erormess}!=null">
            <h6 style="color: #00ced1;">${requestScope.erormess}</h6>
        </c:if>
        
        <form method="post" action="s_regisGoogle">
            <div class="form-floating mb-4 input-icon">
                <input type="text" class="form-control" name="address" id="address" placeholder="Address" required>
                <label for="address">Address</label>
                <i class="fas fa-map-marker-alt"></i>
            </div>
            <div class="form-floating mb-4 input-icon">
                <input type="text" class="form-control" name="Phone" id="Phone" placeholder="Phone Number" required>
                <label for="Phone">Phone Number</label>
                <i class="fas fa-phone"></i>
            </div>
            <div class="d-grid">
                <button type="submit" class="btn btn-submit">Submit</button>
            </div>
        </form>
        <div class="back-to-login">
            <a href="login.jsp">Back to Login</a>
        </div>
    </div>

    <!-- Bootstrap 5 JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
    <!-- Script kiểm tra video -->
    <script>
        const video = document.getElementById('background-video');
        video.addEventListener('error', function(e) {
            console.error('Error loading video:', e);
            alert('Could not load video. Please check the console for more details.');
        });
        video.addEventListener('loadeddata', function() {
            console.log('Video loaded successfully');
        });
    </script>
</body>
</html>