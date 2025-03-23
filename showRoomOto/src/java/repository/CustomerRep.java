package repository;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE Customer SET FullName=?, Address=?, PhoneNumber=?, Email=? WHERE customerID=?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPhoneNumber());
            ps.setString(4, customer.getEmail());
            ps.setInt(5, customer.getCustomerId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     public static ArrayList<Customer> listAllCustomers() {
        ArrayList<Customer> list = new ArrayList<>();
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Customer");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Customer(
                        rs.getInt("customerID"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("email")
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public static int newCustomer(Customer c) {
        int id = -1;
        String sql = "INSERT INTO Customer (FullName, Address, PhoneNumber, Email) "
                + "OUTPUT INSERTED.customerID VALUES (?, ?, ?, ?)";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(sql)) {
            // Gán giá trị cho các tham số
            stmt.setString(1, c.getFullName());
            stmt.setString(2, c.getAddress());
            stmt.setString(3, c.getPhoneNumber());
            stmt.setString(4, c.getEmail());

            // Thực thi truy vấn và lấy ID của bản ghi vừa thêm
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return id;
    }

    public boolean deleteCustomer(int customerID) {
        try (Connection conn = getConnect()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Customer WHERE customerID = ?");
            stmt.setInt(1, customerID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi log lỗi ra console
            return false;
        }
    }


    public Customer getCustomerById(int customerID) {
        Customer customer = null;

        try (Connection conn = getConnect()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customer WHERE customerID = ?");
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("customerID"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi log lỗi ra console
        }
        return customer;
    }
}