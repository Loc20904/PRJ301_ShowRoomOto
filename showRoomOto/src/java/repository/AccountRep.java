/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package repository;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import model.Account;
import static repository.DatabaseInfo.DBURL;
import static repository.DatabaseInfo.DRIVERNAME;
import static repository.DatabaseInfo.PASSDB;
import static repository.DatabaseInfo.USERDB;


public class AccountRep implements DatabaseInfo{

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
    
      public static Account checkLogin( String email, String password) throws SQLException {
        String sql = "SELECT * FROM [Account] WHERE Email = ? AND Password = ?";
        Connection con=getConnect();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Nếu tìm thấy tài khoản, tạo đối tượng Account
            return new Account(
                rs.getInt("accId"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("role"),
                rs.getString("authority"),
                rs.getDate("regisDate").toLocalDate(),
                rs.getInt("customerId")
            );
        }
        return null; // Trả về null nếu đăng nhập thất bại
    }
      public static void main(String[] args) throws SQLException {
          System.out.println(checkLogin("john@example.com", "hashed_pass_123"));
    }
}
