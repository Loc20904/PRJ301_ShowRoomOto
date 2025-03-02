/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package repository;


import com.sun.jdi.connect.spi.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CarRep implements DatabaseInfo{
      public static java.sql.Connection getConnect(){
        try{ 
            Class.forName(DRIVERNAME); 
	} catch(ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
	}
        try{            
            java.sql.Connection con = DriverManager.getConnection(DBURL,USERDB,PASSDB);
            return con;
        }
        catch(java.sql.SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
//
//    public static Car getCarByID(int carID) {
//        Car car = null;
//           java.sql.Connection con=getConnect();
//        if (con != null) {
//            try {
//                String sql = "SELECT * FROM Car WHERE CarID = ?";
//                PreparedStatement ps = con.prepareStatement(sql);
//                ps.setInt(1, carID);
//                ResultSet rs = ps.executeQuery();
//                if (rs.next()) {
//                    // Tạo đối tượng Car, map các cột vào
//                    car = new Car();
//                    car.setCarID(rs.getInt(1));               // CarID
//                    car.setCarName(rs.getString(2));          // carName
//                    car.setType(rs.getString(3));             // type
//                    car.setBrand(rs.getString(4));            // brand
//                    car.setDescription(rs.getString(5));      // description
//                    car.setPrice(rs.getDouble(6));            // price
//                    car.setYearOfManufacture(rs.getInt(7));   // year_of_manufacture
//                    car.setWeight(rs.getDouble(8));           // Weight
//                    car.setStockQuantity(rs.getInt(9));       // StockQuantity
//                    car.setImageURL(rs.getString(10));        // imageURL
//
//                }
//                rs.close();
//                ps.close();
//                con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return car;
//    }
     public static ArrayList<Car> getall(){
        ArrayList<Car> ro = new ArrayList<>();
        try(java.sql.Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement("Select *  from Car ");
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                
                ro.add(new Car(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getFloat(6),rs.getInt(6),rs.getFloat(7),rs.getInt(8),rs.getString(9)));
            }
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(CarRep.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ro;
    }
    public static void main(String[] args) {
        ArrayList<Car> ro = getall();
        for (Car car : ro) {
            System.out.println(car);
        }
//        System.out.println(getCarByID(1));
    }
}
