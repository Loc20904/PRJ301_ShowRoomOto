<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Car Detail</title>
        <link rel="stylesheet" href="css/CSS_CarDetail.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <link rel="stylesheet" href="css/CSS_header.css"/>
        <!--<link rel="stylesheet" href="css/CSS_footer.css"/>-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/2.2.2/css/dataTables.bootstrap5.css"/>
    </head>
    <body>
        <%@include file="includes/headerMainPage.jsp" %>
        
        <h2 id="h2v">Booking History</h2>
        <table id="example" class="table table-striped" style="width:100%">
            <thead>
                <tr>
                    <th>Car</th>
                    <th>Employee</th>
                    <th>StartDate</th>
                    <th>Slot</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${requestScope.bookingList}">
                    <tr>
                        <td>${booking.car.carName}</td>
                        <td>${booking.employee.fullName}</td>
                        <td>${booking.startDate}</td>
                        <td>${booking.slot}</td>
                        <td>${booking.status}</td>
                        <td>
                            <c:if test="${booking.status.trim().toLowerCase() eq 'pending'}">
                                <form action="s_bookingStatus" method="get" style="display:inline;">
                                    <input type="hidden" name="bookingId" value="${booking.bookingID}">
                                    <button type="submit" class="btn btn-danger btn-sm">Cancel</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <%--<%@include file="includes/footer.jsp" %>--%>
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.datatables.net/2.2.2/js/dataTables.js"></script>
        <script src="https://cdn.datatables.net/2.2.2/js/dataTables.bootstrap5.js"></script>
        <script src="table.js"></script>
    </body>
</html>
