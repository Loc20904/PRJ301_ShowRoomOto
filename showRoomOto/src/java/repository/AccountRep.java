package repository;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public static ArrayList<Account> listAll() {
        ArrayList<Account> list = new ArrayList<>();
        try (Connection con = getConnect()) {
            if (con == null) {
                return list;
            }
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Account");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Account(
                        rs.getInt("accID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("authority"),
                        LocalDate.parse(rs.getString("regisDate")),
                        rs.getObject("customerID", Integer.class),
                        rs.getObject("employeeID", Integer.class)
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public boolean updateAccount(String usename, String email, String role, String authority, String password, int accID) {
        String sql = "UPDATE Account SET username=?, email=?, role=?, authority=?, password=? WHERE accID=?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usename);
            stmt.setString(2, email);
            stmt.setString(3, role);
            stmt.setString(4, authority);
            stmt.setString(5, password);

            stmt.setInt(6, accID);

            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static int newAccount(Account account) {
        int id = -1;
        String sql = "INSERT INTO Account (username, password, email, role, authority, regisDate, customerID, employeeID) "
                + "OUTPUT INSERTED.accID VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getPassword());
            stmt.setString(3, account.getEmail());
            stmt.setString(4, account.getRole());
            stmt.setString(5, account.getAuthority());

            if (account.getRegisDate() != null) {
                stmt.setString(6, account.getRegisDate().toString());
            } else {
                stmt.setNull(6, Types.DATE);
            }

            if ((Integer) account.getCustomerId() != null) {
                stmt.setInt(7, account.getCustomerId());
            } else {
                stmt.setNull(7, Types.INTEGER);
            }

            if ((Integer) account.getEmployeeId() != null) {
                stmt.setInt(8, account.getEmployeeId());
            } else {
                stmt.setNull(8, Types.INTEGER);
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return id;
    }

    public boolean deleteAccount(int accID) {
        try (Connection conn = getConnect()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Account WHERE accID = ?");
            stmt.setInt(1, accID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getAccountByEmail("jane@example.com").toString());
    }
}
