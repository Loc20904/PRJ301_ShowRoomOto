/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package repository;

import model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Car;
import model.Customer;
import model.Employee;
import static repository.DatabaseInfo.DBURL;
import static repository.DatabaseInfo.DRIVERNAME;
import static repository.DatabaseInfo.PASSDB;
import static repository.DatabaseInfo.USERDB;



public class bookingRep implements DatabaseInfo{
    
    public static Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
        }
        try {
            Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    
    
    public static List<Booking> getBookingsByEmployee(int employeeId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.BookingID, c.customerID, c.FullName AS CustomerName, b.BookingDate, b.Status, " +
                     "bd.StartDate, bd.EndDate, bd.Slot, car.CarID, car.carName, " +
                     "e.EmployeeID, e.FullName AS EmployeeName " +
                     "FROM Booking b " +
                     "JOIN BookingDetail bd ON b.BookingID = bd.BookingID " +
                     "JOIN Customer c ON b.customerID = c.customerID " +
                     "JOIN Car car ON bd.CarID = car.CarID " +
                     "JOIN Employee e ON bd.EmployeeID = e.EmployeeID " +
                     "WHERE bd.EmployeeID = ?";

        try (Connection conn = getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customerID"));
                customer.setFullName(rs.getString("CustomerName"));

                Car car = new Car();
                car.setCarID(rs.getInt("CarID"));
                car.setCarName(rs.getString("carName"));

                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("EmployeeID"));
                employee.setFullName(rs.getString("EmployeeName"));

                Booking booking = new Booking();
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setCustomer(customer);
                booking.setBookingDate(rs.getDate("BookingDate").toLocalDate());
                booking.setStatus(rs.getString("Status"));
                booking.setStartDate(rs.getDate("StartDate").toLocalDate());
                booking.setEndDate(rs.getDate("EndDate").toLocalDate());
                booking.setSlot(rs.getInt("Slot"));
                booking.setCar(car);
                booking.setEmployee(employee);

                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}


