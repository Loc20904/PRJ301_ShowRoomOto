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
import model.Account;
import repository.AccountRep;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

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
            out.println("<title>Servlet CreateAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateAccountServlet at " + request.getContextPath() + "</h1>");
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
            // Lấy thông tin từ form
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String role = request.getParameter("role");
            String authority = request.getParameter("authority");

            // Xử lý CustomerID và EmployeeID (cho phép null)
            Integer customerID = request.getParameter("customerID") != null && !request.getParameter("customerID").isEmpty()
                    ? Integer.parseInt(request.getParameter("customerID")) : null;

            Integer employeeID = request.getParameter("employeeID") != null && !request.getParameter("employeeID").isEmpty()
                    ? Integer.parseInt(request.getParameter("employeeID")) : null;

            // Xử lý ngày đăng ký (Không được null)
            String regisDateStr = request.getParameter("regisDate");
            java.sql.Date regisDate;
            if (regisDateStr == null || regisDateStr.trim().isEmpty()) {
                regisDate = new java.sql.Date(System.currentTimeMillis()); // Lấy ngày hiện tại nếu không nhập
            } else {
                regisDate = java.sql.Date.valueOf(regisDateStr);
            }

            // Kiểm tra trường bắt buộc
            if (username == null || username.trim().isEmpty()
                    || password == null || password.trim().isEmpty()
                    || email == null || email.trim().isEmpty()
                    || role == null || role.trim().isEmpty()) {
                request.setAttribute("message", "❌ Missing required fields.");
            } else {
                AccountRep accountDB = new AccountRep();

                // ✅ Hash password trước khi lưu (nếu cần)
                // String hashedPassword = hashPassword(password);  // Nếu dùng Bcrypt
                Account newAccount = new Account(0, username, password, email, role, authority, LocalDate.parse(regisDate.toString()), customerID, employeeID);
                int newAccountID = accountDB.newAccount(newAccount);

                if (newAccountID > 0) {
                    request.setAttribute("message", "✅ Account created successfully!");
                } else {
                    request.setAttribute("message", "❌ Failed to create account. Username or email might be taken.");
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "❌ Invalid number format for CustomerID or EmployeeID.");
        } catch (Exception e) {
            request.setAttribute("message", "❌ An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Log lỗi ra console để debug
        }

        request.getRequestDispatcher("accountTable.jsp").forward(request, response);
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
