<%-- 
    Document   : registerGoogle
    Created on : Mar 21, 2025, 9:31:54 PM
    Author     : phuc2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="s_regisGoogle">
            <label for="address" >Address</label>
            <input type="text" name="address" required>
            <label for="Phone" >Phone number</label>
            <input type="text" name="Phone" required>
            <input type="submit" value="submit">
        </form>
    </body>
</html>