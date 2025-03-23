<%-- 
    Document   : accountInfo
    Created on : Mar 22, 2025, 8:54:50 PM
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
            <h2 class="text-center">Account Information</h2>
            <c:if test="${not empty sessionScope.updateSuccess}">
                <div class="alert alert-success">${sessionScope.updateSuccess}</div>
                <c:remove var="updateSuccess" scope="session"/>
            </c:if>
            <c:if test="${not empty sessionScope.updateError}">
                <div class="alert alert-danger">${sessionScope.updateError}</div>
                <c:remove var="updateError" scope="session"/>
            </c:if>
            <c:choose>
                <c:when test="${account.role eq 'user'}">
                    <table class="table table-bordered">
                        <tr><th>Customer ID</th><td>${customer.customerId}</td></tr>
                        <tr><th>Username</th><td>${account.username}</td></tr>
                        <tr><th>Full Name</th><td>${customer.fullName}</td></tr>
                        <tr><th>Address</th><td>${customer.address}</td></tr>
                        <tr><th>Phone Number</th><td>${customer.phoneNumber}</td></tr>
                        <tr><th>Email</th><td>${customer.email}</td></tr>
                        <tr><th>Register Date</th><td>${account.regisDate}</td></tr>
                    </table>
                </c:when>
                <c:otherwise>
                    <table class="table table-bordered">
                        <tr><th>Employee ID</th><td>${employee.employeeId}</td></tr>
                        <tr><th>Username</th><td>${account.username}</td></tr>
                        <tr><th>Full Name</th><td>${employee.fullName}</td></tr>
                        <tr><th>Address</th><td>${employee.address}</td></tr>
                        <tr><th>Phone Number</th><td>${employee.phoneNumber}</td></tr>
                        <tr><th>Email</th><td>${employee.email}</td></tr>
                        <tr><th>Register Date</th><td>${account.regisDate}</td></tr>
                        <tr><th>Position</th><td>${employee.position}</td></tr>
                    </table>
                </c:otherwise>
            </c:choose>

            <!-- Nút Update -->
            <form action="updateAccount.jsp" method="post">
                <input type="hidden" name="role" value="${account.role}">
                <input type="hidden" name="userId" value="${account.role eq 'user' ? customer.customerId : employee.employeeId}">
                <input type="hidden" name="username" value="${account.username}">
                <input type="hidden" name="fullName" value="${account.role eq 'user' ? customer.fullName : employee.fullName}">
                <input type="hidden" name="address" value="${account.role eq 'user' ? customer.address : employee.address}">
                <input type="hidden" name="phoneNumber" value="${account.role eq 'user' ? customer.phoneNumber : employee.phoneNumber}">
                <input type="hidden" name="email" value="${account.role eq 'user' ? customer.email : employee.email}">
                <button type="submit" class="btn btn-primary">Update Information</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    </body>
</html>
