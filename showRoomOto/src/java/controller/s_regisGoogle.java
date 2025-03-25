package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import repository.AccountRep;
import repository.CustomerRep;

/**
 *
 * @author phuc2
 */
@WebServlet(name = "s_regisGoogle", urlPatterns = {"/s_regisGoogle"})
public class s_regisGoogle extends HttpServlet {

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
            out.println("<title>Servlet s_regisGoogle</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet s_regisGoogle at " + request.getContextPath() + "</h1>");
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
        if (request.getAttribute("erormess") != null) {
            request.getRequestDispatcher("registerGoogle.jsp").forward(request, response);
            return;
        }
        String ad = request.getParameter("address");
        String phone = request.getParameter("Phone");
        Account user = (Account) request.getSession().getAttribute("user");
        int cusid = CustomerRep.addCustomer(user.getUsername(), ad, phone, user.getEmail());
        AccountRep.addAccount(user.getUsername(), user.getEmail(), "user", "normal", cusid);
        user.setRole("user");
        user.setCustomerId(cusid);
        String redirectUrl = (String) request.getSession().getAttribute("redirectUrl");
        if (redirectUrl != null) {
            request.getSession().removeAttribute("redirectUrl"); // Xóa URL sau khi sử dụng
            response.sendRedirect(redirectUrl); // Chuyển hướng đến trang đặt xe
        } else {
            response.sendRedirect("s_Car"); // Chuyển hướng đến trang chính nếu không có URL
        }
        
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
