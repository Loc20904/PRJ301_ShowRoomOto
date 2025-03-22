package repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public interface DatabaseInfo {
    public static String DRIVERNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String DBURL = "jdbc:sqlserver://localhost;databaseName=CarBookingDB;encrypt=false;trustServerCertificate=false;loginTimeout=30;";
    public static String USERDB = "sa";
    public static String PASSDB = "123";

    public static Connection getConnection() {
        try {
            // Đăng ký driver
            Class.forName(DRIVERNAME);
            System.out.println("Driver đăng ký thành công!");
            // Tạo kết nối
            Connection conn = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            System.out.println("Kết nối database thành công!");
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy driver: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối database: " + e.getMessage());
            return null;
        }
    }
}