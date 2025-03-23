/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import model.Booking;
import repository.BookingDetailDB;
import repository.CarRep;
import repository.CustomerRep;
import repository.EmployeeRep;

/**
 *
 * @author ACER
 */
@WebServlet(name = "EditBookingServlet", urlPatterns = {"/EditBookingServlet"})
public class EditBookingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditBookingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditBookingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override

protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        int bookingDetailID = Integer.parseInt(request.getParameter("bookingDetailID"));
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        int carID = Integer.parseInt(request.getParameter("carID"));
        int custID = Integer.parseInt(request.getParameter("custID"));
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        int slot = Integer.parseInt(request.getParameter("slot"));

        // Chuyển đổi từ String sang java.sql.Date
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        java.sql.Date startDate = java.sql.Date.valueOf(LocalDate.parse(request.getParameter("startDate"), df));
        java.sql.Date endDate = java.sql.Date.valueOf(LocalDate.parse(request.getParameter("endDate"), df));

        // Kiểm tra ràng buộc ngày tháng
        if (startDate.after(endDate)) {
            request.setAttribute("message", "❌ Start date must be before or equal to end date.");
        } else {
            Booking updatedBooking = new Booking(bookingID,CustomerRep.getCustomerByID(custID),
                                LocalDate.now(),"pending",CarRep.getCarByID(carID),
                                EmployeeRep.getEmployeeById(employeeID), LocalDate.parse(startDate.toString()),
                                LocalDate.parse(endDate.toString()), slot)
                ;
            BookingDetailDB bookingDB = new BookingDetailDB();

            boolean success = bookingDB.updateBooking(updatedBooking);

            if (success) {
                request.setAttribute("message", "📅 Booking updated successfully!");
            } else {
                request.setAttribute("message", "❌ Failed to update booking.");
            }
        }
    } catch (NumberFormatException e) {
        request.setAttribute("message", "❌ Invalid number format.");
    } catch (DateTimeParseException e) {
        request.setAttribute("message", "❌ Invalid date format. Please use YYYY-MM-DD.");
    } catch (Exception e) {
        request.setAttribute("message", "❌ Error: " + e.getMessage());
    }

    request.getRequestDispatcher("bookingTable.jsp").forward(request, response);
}



    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
