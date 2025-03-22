package repository;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import model.Account;
import static repository.DatabaseInfo.DBURL;
import static repository.DatabaseInfo.DRIVERNAME;
import static repository.DatabaseInfo.PASSDB;
import static repository.DatabaseInfo.USERDB;

public class AccountRep implements DatabaseInfo {

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

    public static Account checkLogin(String email, String password) throws SQLException {
        String sql = "SELECT * FROM [Account] WHERE Email = ? AND Password = ?";
        Connection con = getConnect();
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

    public static boolean isUsernameExists(String email) {
        String query = "SELECT COUNT(*) FROM Account WHERE email = ?";

        try (Connection conn = getConnect()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Nếu COUNT > 0 nghĩa là username đã tồn tại
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean addAccount(String username, String email, String role, String authority, int customerID) {
        String query = "INSERT INTO Account (username,password,email, role, authority, customerID) VALUES (?,'google',?,?, ?, ?)";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, role);
            ps.setString(4, authority);
            ps.setInt(5, customerID);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm tài khoản: " + e);
        }
        return false;
    }

    public static Account getAccountByEmail(String email) {
        String query = "SELECT * FROM Account WHERE email = ?";
        Account account = null;

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account = new Account(
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
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy tài khoản: " + e);
        }

        return account;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getAccountByEmail("jane@example.com").toString());
    }
}