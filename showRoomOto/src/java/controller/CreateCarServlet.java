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
import model.Car;
import repository.CarRep;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CreateCarServlet", urlPatterns = {"/CreateCarServlet"})
public class CreateCarServlet extends HttpServlet {

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
            out.println("<title>Servlet CreateCarServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateCarServlet at " + request.getContextPath() + "</h1>");
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
            // Lấy dữ liệu từ request
            String carName = request.getParameter("carName");
            String type = request.getParameter("type");
            String brand = request.getParameter("brand");
            String description = request.getParameter("description");
            String imageURL = request.getParameter("carImage"); // Bổ sung imageURL

            // Kiểm tra dữ liệu đầu vào, tránh lỗi NullPointerException
            if (carName == null) {
                request.setAttribute("message", "❌ Missing required fields 1.");
                request.getRequestDispatcher("carTable.jsp").forward(request, response);
                return;
            }
             if ( type == null ) {
                request.setAttribute("message", "❌ Missing required fields2.");
                request.getRequestDispatcher("carTable.jsp").forward(request, response);
                return;
            }
              if ( brand == null ) {
                request.setAttribute("message", "❌ Missing required fields3.");
                request.getRequestDispatcher("carTable.jsp").forward(request, response);
                return;
            }
               if ( description == null) {
                request.setAttribute("message", "❌ Missing required fields4.");
                request.getRequestDispatcher("carTable.jsp").forward(request, response);
                return;
            }
            // Chuyển đổi dữ liệu số
            double price;
            int year;
            double weight;
            int stock;

            try {
                price = Double.parseDouble(request.getParameter("price"));
                year = Integer.parseInt(request.getParameter("yearOfManufacture"));
                weight = Double.parseDouble(request.getParameter("weight"));
                stock = Integer.parseInt(request.getParameter("stockQuantity"));
            } catch (NumberFormatException e) {
                request.setAttribute("message", "❌ Invalid numeric input.");
                request.getRequestDispatcher("carTable.jsp").forward(request, response);
                return;
            }

            // Tạo đối tượng Car và thêm vào database
            CarRep carDB = new CarRep();
            int newCarID = carDB.newCar(new Car(0, carName, type, brand, description, price, year, weight, stock, imageURL));

            if (newCarID > 0) {
                request.setAttribute("message", "🚗 Car added successfully!");
            } else {
                request.setAttribute("message", "❌ Failed to add car.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi để dễ debug
            request.setAttribute("message", "❌ An unexpected error occurred.");
        }

        request.getRequestDispatcher("carTable.jsp").forward(request, response);
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
