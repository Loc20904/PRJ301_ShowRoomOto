/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
}
