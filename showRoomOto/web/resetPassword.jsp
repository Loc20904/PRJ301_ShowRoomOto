<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <style>
        body {
            background: url('images/loginb.jpg') no-repeat center center fixed;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .reset-password-container {
            background: rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(2px);
            border-radius: 10px;
            padding: 2rem;
            width: 100%;
            max-width: 400px;
        }
    </style>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="reset-password-container">
        <h2 class="text-center mb-4">Reset Password</h2>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger text-center" role="alert">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
        <% if (request.getAttribute("message") != null) { %>
            <div class="alert alert-info text-center" role="alert">
                <%= request.getAttribute("message") %>
            </div>
        <% } %>
        <% if (request.getAttribute("showForm") == null || !(Boolean)request.getAttribute("showForm")) { %>
            <p class="text-center">
                <a href="login.jsp" class="link-secondary text-decoration-none" style="color: black;">Back to Login</a>
            </p>
        <% } else { %>
            <form action="resetPassword" method="post">
                <input type="hidden" name="token" value="<%= request.getParameter("token") %>">
                <div class="form-floating mb-3">
                    <input type="password" class="form-control border-0 border-bottom rounded-0" name="newPassword" id="newPassword" placeholder="New Password" required>
                    <label for="newPassword" class="form-label">New Password</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control border-0 border-bottom rounded-0" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password" required>
                    <label for="confirmPassword" class="form-label">Confirm Password</label>
                </div>
                <div class="d-grid">
                    <button class="btn btn-lg btn-dark rounded-0 fs-6" type="submit">Reset Password</button>
                </div>
            </form>
        <% } %>
    </div>
</body>
</html>