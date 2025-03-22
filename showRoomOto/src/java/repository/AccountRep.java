package repository;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import model.Account;
import org.mindrot.jbcrypt.BCrypt;
import static repository.DatabaseInfo.DBURL;
import static repository.DatabaseInfo.DRIVERNAME;
import static repository.DatabaseInfo.PASSDB;
import static repository.DatabaseInfo.USERDB;

public class AccountRep implements DatabaseInfo {
    public static Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e);
        }
        try {
            Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            System.out.println("Database connection successful");
            return con;
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e);
        }
        return null;
    }
    
    public static Account checkLogin(String email, String password) throws SQLException {
        System.out.println("Checking login for email: " + email + ", password: " + password);
        String sql = "SELECT * FROM Account WHERE email = ?";
        Connection con = getConnect();
        if (con == null) {
            System.err.println("Cannot connect to database");
            return null;
        }

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String hashedPassword = rs.getString("password");

            if (BCrypt.checkpw(password, hashedPassword)) {
                System.out.println("Password matched for email: " + email);
                Account account = new Account(
                    rs.getInt("accID"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getString("authority"),
                    rs.getDate("regisDate") != null ? rs.getDate("regisDate").toLocalDate() : null,
                    rs.getInt("customerID")
                );
                con.close();
                return account;
            } else {
                System.err.println("Password does not match for email: " + email);
                con.close();
                return null;
            }
        } else {
            System.err.println("Email not found: " + email);
        }
        con.close();
        return null;
    }

    // Kiểm tra email có tồn tại không
    public static boolean emailExists(String email) throws SQLException {
        Connection con = getConnect();
        if (con == null) {
            throw new SQLException("Cannot connect to database");
        }

        String sql = "SELECT 1 FROM Account WHERE email = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        boolean exists = rs.next();
        con.close();
        return exists;
    }

    // Lưu token đặt lại mật khẩu
    public static void savePasswordResetToken(String email, String token, java.util.Date expiryDate) throws SQLException {
        Connection con = getConnect();
        if (con == null) {
            throw new SQLException("Cannot connect to database");
        }

        // Xóa token cũ nếu có
        String deleteSql = "DELETE FROM PasswordResetToken WHERE email = ?";
        PreparedStatement deleteStmt = con.prepareStatement(deleteSql);
        deleteStmt.setString(1, email);
        deleteStmt.executeUpdate();

        // Lưu token mới
        String insertSql = "INSERT INTO PasswordResetToken (email, token, expiryDate) VALUES (?, ?, ?)";
        PreparedStatement insertStmt = con.prepareStatement(insertSql);
        insertStmt.setString(1, email);
        insertStmt.setString(2, token);
        insertStmt.setTimestamp(3, new java.sql.Timestamp(expiryDate.getTime()));
        insertStmt.executeUpdate();

        con.close();
    }

    // Xác minh token
    public static String verifyResetToken(String token) throws SQLException {
        Connection con = getConnect();
        if (con == null) {
            throw new SQLException("Cannot connect to database");
        }

        String sql = "SELECT email, expiryDate FROM PasswordResetToken WHERE token = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, token);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            java.sql.Timestamp expiryDate = rs.getTimestamp("expiryDate");
            if (expiryDate.getTime() > System.currentTimeMillis()) {
                String email = rs.getString("email");
                con.close();
                return email; // Token hợp lệ, trả về email
            } else {
                // Token đã hết hạn, xóa token
                String deleteSql = "DELETE FROM PasswordResetToken WHERE token = ?";
                PreparedStatement deleteStmt = con.prepareStatement(deleteSql);
                deleteStmt.setString(1, token);
                deleteStmt.executeUpdate();
            }
        }

        con.close();
        return null; // Token không hợp lệ
    }

    // Cập nhật mật khẩu mới
    public static void updatePassword(String email, String newPassword) throws SQLException {
        Connection con = getConnect();
        if (con == null) {
            throw new SQLException("Cannot connect to database");
        }

        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(10));
        String sql = "UPDATE Account SET password = ? WHERE email = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, hashedPassword);
        stmt.setString(2, email);
        stmt.executeUpdate();

        // Xóa token sau khi cập nhật mật khẩu
        String deleteSql = "DELETE FROM PasswordResetToken WHERE email = ?";
        PreparedStatement deleteStmt = con.prepareStatement(deleteSql);
        deleteStmt.setString(1, email);
        deleteStmt.executeUpdate();

        con.close();
    }
}