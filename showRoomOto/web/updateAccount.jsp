<%-- 
    Document   : updateAccount
    Created on : Mar 22, 2025, 9:26:39 PM
    Author     : Hong Quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Information</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <link rel="stylesheet" href="css/CSS_employeePage.css"/>
        <link rel="stylesheet" href="css/CSS_header.css"/>
    </head>
    <body>
        <%@include file="includes/headerMainPage.jsp" %>
        <div class="container mt-5">
            <h2 class="text-center">Update Account Information</h2>

            <form action="UpdateAccountServlet" method="post">
                <input type="hidden" name="role" value="${param.role}">
                <input type="hidden" name="userId" value="${param.userId}">

                <div class="mb-3">
                    <label class="form-label">Username</label>
                    <input type="text" class="form-control" name="username" value="${param.username}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">Full Name</label>
                    <input type="text" class="form-control" name="fullName" value="${param.fullName}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Address</label>
                    <input type="text" class="form-control" name="address" value="${param.address}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Phone Number</label>
                    <input type="text" class="form-control" name="phoneNumber" value="${param.phoneNumber}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" value="${param.email}" required>
                </div>

                <button type="submit" class="btn btn-success">Save Changes</button>
                <a href="accountInfo.jsp" class="btn btn-secondary">Cancel</a>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>