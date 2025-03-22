package repository;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.Customer;
import static repository.DatabaseInfo.DBURL;
import static repository.DatabaseInfo.DRIVERNAME;
import static repository.DatabaseInfo.PASSDB;
import static repository.DatabaseInfo.USERDB;

public class CustomerRep implements DatabaseInfo {

    public static java.sql.Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
        }
        try {
            java.sql.Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static Customer getCustomerByID(int customerID) {
        Customer customer = null;
        String sql = "SELECT * FROM Customer WHERE customerID = ?";
        try (Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(customerID);
                customer.setFullName(rs.getString("FullName"));
                customer.setAddress(rs.getString("Address"));
                customer.setPhoneNumber(rs.getString("PhoneNumber"));
                customer.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public static int addCustomer(String fullName, String address, String phoneNumber, String email) {
        String query = "INSERT INTO Customer (FullName, Address, PhoneNumber, email) VALUES (?, ?, ?, ?)";
        int customerID = -1; // Giá trị mặc định nếu không lấy được ID

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, fullName);
            ps.setString(2, address);
            ps.setString(3, phoneNumber);
            ps.setString(4, email);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet rs = ps.getGeneratedKeys(); // Lấy khóa chính được tạo tự động
                if (rs.next()) {
                    customerID = rs.getInt(1);
                    System.out.println("Thêm khách hàng thành công! ID: " + customerID);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm khách hàng: " + e);
        }

        return customerID;
    }

}