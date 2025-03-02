<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
</head>
<body>

<!-- Header (Đè lên banner) -->
<%@include file="includes/headerMainPage.jsp" %>

<!-- Banner (Carousel trượt full viền, bị header đè) -->
<%@include file="includes/bannerMainPage.jsp" %>

<form method="get" action="s_Car">
    <label for="carID"></label><input name="carID" type="text">
    <input type="submit" value="get detail">
</form>
<!-- Bootstrap JS, jQuery, và Bootstrap Icons -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

</body>
</html>
