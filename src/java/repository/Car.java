/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.io.Serializable;

/**
 *
 * @author HUNG
 */
public class Car implements Serializable{
    int id;
    String name ;
    String type;
    String Brand;
    String description;
    float price;
    int year_of_manufacture;
    float Weight;
    int StockQuantity;
    String image;

    public Car() {
    }

    public Car(int id, String name, String type, String Brand, String description, float price, int year_of_manufacture, float Weight, int StockQuantity, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.Brand = Brand;
        this.description = description;
        this.price = price;
        this.year_of_manufacture = year_of_manufacture;
        this.Weight = Weight;
        this.StockQuantity = StockQuantity;
        this.image = image;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public int getYear_of_manufacture() {
        return year_of_manufacture;
    }

    public void setYear_of_manufacture(int year_of_manufacture) {
        this.year_of_manufacture = year_of_manufacture;
    }

    public int getStockQuantity() {
        return StockQuantity;
    }

    public void setStockQuantity(int StockQuantity) {
        this.StockQuantity = StockQuantity;
    }

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float Weight) {
        this.Weight = Weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", name=" + name + ", type=" + type + ", Brand=" + Brand + ", description=" + description + ", price=" + price + ", year_of_manufacture=" + year_of_manufacture + ", Weight=" + Weight + ", StockQuantity=" + StockQuantity + ", image=" + image + '}';
    }

   
    
}
