/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Car;

public class CarRep implements DatabaseInfo {

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

    public static Car getCarByID(int carID) {
        Car car = null;
        Connection con = getConnect();
        if (con != null) {
            try {
                String sql = "SELECT * FROM Car WHERE CarID = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, carID);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    // Tạo đối tượng Car, map các cột vào
                    car = new Car();
                    car.setCarID(rs.getInt(1));               // CarID
                    car.setCarName(rs.getString(2));          // carName
                    car.setType(rs.getString(3));             // type
                    car.setBrand(rs.getString(4));            // brand
                    car.setDescription(rs.getString(5));      // description
                    car.setPrice(rs.getDouble(6));            // price
                    car.setYearOfManufacture(rs.getInt(7));   // year_of_manufacture
                    car.setWeight(rs.getDouble(8));           // Weight
                    car.setStockQuantity(rs.getInt(9));       // StockQuantity
                    car.setImageURL(rs.getString(10));        // imageURL

                }
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return car;
    }

    public static void main(String[] args) {
        System.out.println(getCarByID(1));
    }
}
