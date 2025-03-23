/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import static repository.DatabaseInfo.DBURL;
import static repository.DatabaseInfo.DRIVERNAME;
import static repository.DatabaseInfo.PASSDB;
import static repository.DatabaseInfo.USERDB;

public class EmployeeRep implements DatabaseInfo {

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

    public static int getEmID(String acc) {
        String sql = "SELECT EmployeeID FROM Employee WHERE Email = ?";
        Connection con = getConnect();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, acc);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeRep.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static List<Employee> getAllActiveEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE Status = 'active'";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Email"),
                        rs.getString("Position"),
                        rs.getString("Status")
                );
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static void updateStatusEmployee(String emid) {
        String sql = "update Employee set Status = 'inactive' where EmployeeID = ?";
        try (Connection con = getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Employee getEmployeeById(int employeeId) {
        String sql = "SELECT * FROM Employee WHERE EmployeeID = ?";
        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Email"),
                        rs.getString("Position"),
                        rs.getString("Status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Employee getEmployeeByEmail(String email) {
        Connection conn = AccountRep.getConnect();
        String sql = "SELECT * FROM Employee WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Email"),
                        rs.getString("Position"),
                        "active"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE Employee SET FullName=?, Address=?, PhoneNumber=?, Email=? WHERE EmployeeID=?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, employee.getFullName());
            ps.setString(2, employee.getAddress());
            ps.setString(3, employee.getPhoneNumber());
            ps.setString(4, employee.getEmail());
            ps.setInt(5, employee.getEmployeeId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int newEmployee(Employee e) {
        int id = -1;
        String sql = "INSERT INTO Employee (FullName, Address, PhoneNumber, Email, Position, Status) "
                + "OUTPUT INSERTED.EmployeeID VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, e.getFullName());
            stmt.setString(2, e.getAddress());
            stmt.setString(3, e.getPhoneNumber());
            stmt.setString(4, e.getEmail());
            stmt.setString(5, e.getPosition());
            stmt.setString(6, e.getStatus());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return id;
    }

    // Xóa nhân viên theo ID
    public boolean deleteEmployee(int employeeID) {
        try (Connection conn = getConnect()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Employee WHERE EmployeeID = ?");
            stmt.setInt(1, employeeID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Employee> listAllEmployees() {
        ArrayList<Employee> list = new ArrayList<>();
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Employee");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Email"),
                        rs.getString("Position"),
                        rs.getString("Status")
                ));
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeRep.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        for (Employee e : getAllActiveEmployees()) {
            System.out.println(e);
        }
    }
}
