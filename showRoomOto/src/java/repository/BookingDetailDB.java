package repository;

import repository.DatabaseInfo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;
import model.Car;
import model.Customer;
import model.Employee;
public class BookingDetailDB implements DatabaseInfo {

    public static Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
        }
        try {
            return DriverManager.getConnection(DBURL, USERDB, PASSDB);
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

public static List<Booking> listAllBookings() {
    List<Booking> bookings = new ArrayList<>();
    String sql = "SELECT b.BookingID, c.customerID, c.FullName AS CustomerName, b.BookingDate, b.Status, " +
                 "bd.BookingDetailID, bd.StartDate, bd.EndDate, bd.Slot, car.CarID, car.carName, " +
                 "e.EmployeeID, e.FullName AS EmployeeName " +
                 "FROM Booking b " +
                 "JOIN BookingDetail bd ON b.BookingID = bd.BookingID " +
                 "JOIN Customer c ON b.customerID = c.customerID " +
                 "JOIN Car car ON bd.CarID = car.CarID " +
                 "JOIN Employee e ON bd.EmployeeID = e.EmployeeID";

    try (Connection conn = getConnect();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

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
            booking.setBookingDetailID(rs.getInt("BookingDetailID"));
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



    // Thêm booking mới
    public static int newBooking(Booking b) {
        int id = -1;

        // Dùng bảng tạm để tránh lỗi trigger
        String sql = "DECLARE @output TABLE (BookingDetailID INT); "
                + "INSERT INTO BookingDetail (BookingID, CarID, CustID, EmployeeID, StartDate, EndDate, Slot) "
                + "OUTPUT INSERTED.BookingDetailID INTO @output "
                + "VALUES (?, ?, ?, ?, ?, ?, ?); "
                + "SELECT BookingDetailID FROM @output;";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, b.getBookingID());
            stmt.setInt(2, b.getCar().getCarID());
            stmt.setInt(3, b.getCustomer().getCustomerId());
            stmt.setInt(4, b.getEmployee().getEmployeeId());

            // Chuyển đổi String thành java.sql.Date
            stmt.setDate(5, java.sql.Date.valueOf(b.getStartDate()));
            stmt.setDate(6, java.sql.Date.valueOf(b.getEndDate()));

            stmt.setInt(7, b.getSlot());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("BookingDetailID");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingDetailDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    // Xóa booking theo ID
    public boolean deleteBooking(int bookingDetailID) {
        try (Connection conn = getConnect()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM BookingDetail WHERE BookingDetailID = ?");
            stmt.setInt(1, bookingDetailID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin booking
    public boolean updateBooking(Booking b) {
        String sql = "UPDATE BookingDetail SET BookingID=?, CarID=?, CustID=?, EmployeeID=?, StartDate=?, EndDate=?, Slot=? WHERE BookingDetailID=?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, b.getBookingID());
            stmt.setInt(2, b.getCar().getCarID());
            stmt.setInt(3, b.getCustomer().getCustomerId());
            stmt.setInt(4, b.getEmployee().getEmployeeId());

            // Chuyển từ LocalDate sang java.sql.Date
            if (b.getStartDate() != null) {
                stmt.setDate(5, java.sql.Date.valueOf(b.getStartDate()));
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }

            if (b.getEndDate() != null) {
                stmt.setDate(6, java.sql.Date.valueOf(b.getEndDate()));
            } else {
                stmt.setNull(6, java.sql.Types.DATE);
            }

            stmt.setInt(7, b.getSlot());
            stmt.setInt(8, b.getBookingID());

            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(BookingDetailDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Lấy thông tin booking theo ID
    public Booking getBookingById(int bookingDetailID) {
         Booking booking = new Booking();
        String sql = "SELECT b.BookingID, c.customerID, c.FullName AS CustomerName, b.BookingDate, b.Status, " +
                     "bd.StartDate, bd.EndDate, bd.Slot, car.CarID, car.carName, " +
                     "e.EmployeeID, e.FullName AS EmployeeName " +
                     "FROM Booking b " +
                     "JOIN BookingDetail bd ON b.BookingID = bd.BookingID " +
                     "JOIN Customer c ON b.customerID = c.customerID " +
                     "JOIN Car car ON bd.CarID = car.CarID " +
                     "JOIN Employee e ON bd.EmployeeID = e.EmployeeID " +
                     "WHERE bd.BookingDetailID = ?";

        try (Connection conn = getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookingDetailID);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customerID"));
                customer.setFullName(rs.getString("CustomerName"));

                Car car = new Car();
                car.setCarID(rs.getInt("CarID"));
                car.setCarName(rs.getString("carName"));

                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("EmployeeID"));
                employee.setFullName(rs.getString("EmployeeName"));

               
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setCustomer(customer);
                booking.setBookingDate(rs.getDate("BookingDate").toLocalDate());
                booking.setStatus(rs.getString("Status"));
                booking.setStartDate(rs.getDate("StartDate").toLocalDate());
                booking.setEndDate(rs.getDate("EndDate").toLocalDate());
                booking.setSlot(rs.getInt("Slot"));
                booking.setCar(car);
                booking.setEmployee(employee);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    
    }
}


