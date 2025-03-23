<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
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

        .forgot-password-container {
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
    <div class="forgot-password-container">
        <h2 class="text-center mb-4">Forgot Password</h2>
        <% if (request.getAttribute("message") != null) { %>
            <div class="alert alert-info text-center" role="alert">
                <%= request.getAttribute("message") %>
            </div>
        <% } %>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger text-center" role="alert">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
        <form action="forgotPassword" method="post">
            <div class="form-floating mb-3">
                <input type="email" class="form-control border-0 border-bottom rounded-0" name="email" id="email" placeholder="name@example.com" required>
                <label for="email" class="form-label">Email</label>
            </div>
            <div class="d-grid">
                <button class="btn btn-lg btn-dark rounded-0 fs-6" type="submit">Submit</button>
            </div>
        </form>
        <p class="text-center mt-3">
            <a href="login.jsp" class="link-secondary text-decoration-none" style="color: black;">Back to Login</a>
        </p>
    </div>
</body>
</html>