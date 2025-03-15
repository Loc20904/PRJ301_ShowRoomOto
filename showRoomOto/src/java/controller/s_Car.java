/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Car;
import repository.CarRep;
import java.sql.*;
import java.util.List;

/**
 *
 * @author phuc2
 */
public class s_Car extends HttpServlet {

    private static final int CARS_PER_PAGE = 6; // Số lượng xe trên mỗi trang
    
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
            out.println("<title>Servlet s_Car</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet s_Car at " + request.getContextPath() + "</h1>");
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
//        ArrayList<Car> ls=CarRep.getall();
//        request.getSession().setAttribute("listR", ls);
//        request.getRequestDispatcher("index.jsp").forward(request, response);
        HttpSession session = request.getSession();

        // Lấy danh sách toàn bộ xe từ database
        List<Car> allCars = CarRep.getall();

        // Lấy số trang hiện tại từ request, nếu không có thì mặc định là trang 1
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Tính toán vị trí bắt đầu và kết thúc của danh sách trên trang hiện tại
        int startIndex = (page - 1) * CARS_PER_PAGE;
        int endIndex = Math.min(startIndex + CARS_PER_PAGE, allCars.size());

        // Cắt danh sách xe theo trang
        List<Car> carsOnPage = allCars.subList(startIndex, endIndex);

        // Tính tổng số trang
        int totalPages = (int) Math.ceil((double) allCars.size() / CARS_PER_PAGE);

        // Gửi dữ liệu sang JSP
        session.setAttribute("listR", carsOnPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Chuyển hướng về trang JSP hiển thị xe
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        String cid=request.getParameter("carID");
        Car c=CarRep.getCarByID(Integer.parseInt(cid));
        request.setAttribute("car", c);
        request.getRequestDispatcher("carDetailPage.jsp").forward(request, response);
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
