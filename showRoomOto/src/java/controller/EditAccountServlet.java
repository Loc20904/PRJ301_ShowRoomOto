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
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import model.Account;
import repository.AccountRep;

/**
 *
 * @author ACER
 */
@WebServlet(name = "EditAccountServlet", urlPatterns = {"/EditAccountServlet"})
public class EditAccountServlet extends HttpServlet {

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
            out.println("<title>Servlet EditAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditAccountServlet at " + request.getContextPath() + "</h1>");
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
            int accID = Integer.parseInt(request.getParameter("accID"));
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String role = request.getParameter("role"); // Không bao giờ null
            String authority = request.getParameter("authority");
            String password = request.getParameter("password");
            //
            // Xử lý ngày đăng ký
//            java.sql.Date regisDate = null;
//            if (regisDateStr != null && !regisDateStr.isEmpty()) {
//                regisDate = java.sql.Date.valueOf(regisDateStr); // Chuyển trực tiếp thành java.sql.Date
//            }

            // Kiểm tra quyền của người đăng nhập
//            HttpSession session = request.getSession();
//            String currentUserRole = (String) session.getAttribute("userRole");

            // Nếu user không phải admin, không cho phép đổi role
//            if (!"admin".equals(currentUserRole) && request.getParameter("role") != null) {
//                request.setAttribute("message", "You do not have permission to change roles.");
//                request.getRequestDispatcher("error.jsp").forward(request, response);
//                return;
//            }

            // Xử lý Customer ID & Employee ID (có thể null)
//            Integer customerID = getNullableInt(request, "customerID");
//            Integer employeeID = getNullableInt(request, "employeeID");

            // Nếu role là "customer" => chỉ lưu customerID, đặt employeeID thành null
//            if ("customer".equals(role)) {
//                employeeID = null;
//            } else {
//                customerID = null;
//            }

            // Tạo đối tượng Account

            // Cập nhật thông tin tài khoản
            AccountRep accountDB = new AccountRep();
            boolean success = accountDB.updateAccount(username, email, role, authority, password, accID);

            // Gửi phản hồi về JSP
            if (success) {
                request.setAttribute("message", "Account updated successfully!");
            } else {
                request.setAttribute("message", "Failed to update account.");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
        }

        // Chuyển hướng về trang danh sách tài khoản
        request.getRequestDispatcher("accountTable.jsp").forward(request, response);
    }

    // Hàm hỗ trợ chuyển đổi String sang Integer, trả về null nếu rỗng
    private Integer getNullableInt(HttpServletRequest request, String paramName) {
        String paramValue = request.getParameter(paramName);
        return (paramValue == null || paramValue.isEmpty()) ? null : Integer.parseInt(paramValue);
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
