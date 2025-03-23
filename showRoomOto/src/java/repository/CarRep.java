/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Car;



public class CarRep implements DatabaseInfo{
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
    
         public static ArrayList<Car> getall(){
        ArrayList<Car> ro = new ArrayList<>();
        try(java.sql.Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement("Select *  from Car ");
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Car car = new Car();
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
                ro.add(car);
            }
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(CarRep.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ro;
    }
    
        public static List<Car> getFilteredCars(String brand, String priceOrder) {
        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM Car WHERE 1=1"; // Base SQL
        if (brand != null && !brand.equalsIgnoreCase("allBrand")) {
            sql += " AND brand = ?";
        }

        if ("asc".equals(priceOrder)) {
            sql += " ORDER BY price ASC";
        } else if ("desc".equals(priceOrder)) {
            sql += " ORDER BY price DESC";
        }

        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            int paramIndex = 1;
            if (brand != null && !brand.equalsIgnoreCase("allBrand")) {
                ps.setString(paramIndex++, brand);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Car car = new Car();
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
                carList.add(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carList;
    }
         public Car getCarById(int carID) {
        Car car = null;
        String sql = "SELECT * FROM Car WHERE CarID = ?";

        try (Connection conn = getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, carID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    car = new Car(
                            rs.getInt("CarID"),
                            rs.getString("carName"),
                            rs.getString("type"),
                            rs.getString("brand"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("year_of_manufacture"),
                            rs.getDouble("weight"),
                            rs.getInt("StockQuantity"),
                            rs.getString("imageURL") // Thêm imageURL
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    public boolean updateCar(Car car) {
        String sql = "UPDATE Car SET carName=?, type=?, brand=?, description=?, price=?, year_of_manufacture=?, weight=?, StockQuantity=?, imageURL=? WHERE CarID=?";

        try (Connection conn = getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getCarName());
            stmt.setString(2, car.getType());
            stmt.setString(3, car.getBrand());
            stmt.setString(4, car.getDescription());
            stmt.setDouble(5, car.getPrice());
            stmt.setInt(6, car.getYearOfManufacture());
            stmt.setDouble(7, car.getWeight());
            stmt.setInt(8, car.getStockQuantity());
            stmt.setString(9, car.getImageURL()); // Thêm imageURL
            stmt.setInt(10, car.getCarID());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCar(int carID) {
        String sql = "DELETE FROM Car WHERE CarID=?";

        try (Connection conn = getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int newCar(Car car) {
        int id = -1;
        String sql = "INSERT INTO Car (carName, type, brand, description, price, year_of_manufacture, weight, StockQuantity, imageURL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, car.getCarName());
            stmt.setString(2, car.getType());
            stmt.setString(3, car.getBrand());
            stmt.setString(4, car.getDescription());
            stmt.setDouble(5, car.getPrice());
            stmt.setInt(6, car.getYearOfManufacture());
            stmt.setDouble(7, car.getWeight());
            stmt.setInt(8, car.getStockQuantity());
            stmt.setString(9, car.getImageURL()); // Thêm imageURL

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarRep.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public static void main(String[] args) {
        for(Car c:getall())
        {
            System.out.println(c);
        }
    }
}
