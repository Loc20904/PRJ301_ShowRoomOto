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
@WebServlet(name = "EditCarServlet", urlPatterns = {"/EditCarServlet"})
public class EditCarServlet extends HttpServlet {

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
            out.println("<title>Servlet EditCarServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditCarServlet at " + request.getContextPath() + "</h1>");
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
            // Kiểm tra carID có hợp lệ không
            int carID;
            try {
                carID = Integer.parseInt(request.getParameter("carID"));
            } catch (NumberFormatException e) {
                request.setAttribute("message", "❌ Invalid Car ID.");
                request.getRequestDispatcher("carTable.jsp").forward(request, response);
                return;
            }

            // Lấy dữ liệu từ request
            String carName = request.getParameter("carName");
            String type = request.getParameter("type");
            String brand = request.getParameter("brand");
            String description = request.getParameter("description");
            String imageURL = request.getParameter("carImage"); // Nếu có thêm ảnh

            // Kiểm tra dữ liệu không được null
            if (carName == null || type == null || brand == null || description == null) {
                request.setAttribute("message", "❌ Missing required fields.");
                request.getRequestDispatcher("carTable.jsp").forward(request, response);
                return;
            }

            // Chuyển đổi dữ liệu số, kiểm tra lỗi
            double price;
            int year;
            double weight;
            int stock;

            try {
                price = Double.parseDouble(request.getParameter("price"));
                year = Integer.parseInt(request.getParameter("year"));
                weight = Double.parseDouble(request.getParameter("weight"));
                stock = Integer.parseInt(request.getParameter("stock"));
            } catch (NumberFormatException e) {
                request.setAttribute("message", "❌ Invalid numeric input.");
                request.getRequestDispatcher("carTable.jsp").forward(request, response);
                return;
            }

            // Cập nhật thông tin xe
            Car updatedCar = new Car(carID, carName, type, brand, description, price, year, weight, stock, imageURL);
            CarRep carDB = new CarRep();
            boolean success = carDB.updateCar(updatedCar);

            if (success) {
                request.setAttribute("message", "✅ Car updated successfully!");
            } else {
                request.setAttribute("message", "❌ Failed to update car.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi để debug
            request.setAttribute("message", "❌ Error: " + e.getMessage());
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
