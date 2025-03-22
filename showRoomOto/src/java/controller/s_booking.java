package controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import model.Account;
import model.Booking;
import model.Customer;
import model.Employee;
import repository.CarRep;
import repository.CustomerRep;
import repository.EmployeeRep;
import repository.bookingRep;

/**
 *
 * @author phuc2
 */
public class s_booking extends HttpServlet {

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
            out.println("<title>Servlet s_booking</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet s_booking at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
        // Lưu các thông tin cần thiết vào session để chuyển hướng sau khi đăng nhập
        String carID = request.getParameter("carID");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String slot = request.getParameter("slot");
        String employeeID = request.getParameter("employeeID");

        // Tạo URL để chuyển hướng sau khi đăng nhập
        String redirectUrl = request.getRequestURI() + "?carID=" + carID +
                             "&startDate=" + startDate +
                             "&endDate=" + endDate +
                             "&slot=" + slot +
                             "&employeeID=" + employeeID;

        session.setAttribute("redirectUrl", redirectUrl); // Lưu URL vào session
        response.sendRedirect("login.jsp"); // Chuyển hướng đến trang đăng nhập
        return; // Dừng thực hiện tiếp
    }

        // Lấy thông tin khách hàng từ session
        Account acc = (Account) session.getAttribute("user");
        int customerID = acc.getCustomerId();
        System.out.println("id from acc sess: " + customerID);

        // Lấy dữ liệu từ form
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        int carID = Integer.parseInt(request.getParameter("carID"));
        int slot = Integer.parseInt(request.getParameter("slot"));
        int employeeID = Integer.parseInt(request.getParameter("employeeID")); // Lấy employeeID

        // Lấy thông tin khách hàng từ database
        Customer customer = CustomerRep.getCustomerByID(customerID);
        System.out.println("cus info: " + customer.toString());

        // Tạo đối tượng Booking
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setStartDate(LocalDate.parse(startDate));
        booking.setEndDate(LocalDate.parse(endDate));
        booking.setCar(CarRep.getCarByID(carID));
        booking.setSlot(slot);
        booking.setEmployee(EmployeeRep.getEmployeeById(employeeID));

        // Lưu thông tin đặt xe vào database
        bookingRep.addBooking(booking, slot, employeeID);

        // Lưu vào session
        session.setAttribute("booking", booking);

        // Chuyển hướng đến trang xác nhận
        request.getRequestDispatcher("confirmPage.jsp").forward(request, response);
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
        
    }
}
