package model;

public class Car {
    private int carID;
    private String carName;
    private String type;
    private String brand;
    private String description;
    private double price;
    private int yearOfManufacture;
    private double weight;
    private int stockQuantity;
    private String imageURL;

    // Constructors
    public Car() {
    }

    public Car(int carID, String carName, String type, String brand, String description,
               double price, int yearOfManufacture, double weight, int stockQuantity, String imageURL) {
        this.carID = carID;
        this.carName = carName;
        this.type = type;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.yearOfManufacture = yearOfManufacture;
        this.weight = weight;
        this.stockQuantity = stockQuantity;
        this.imageURL = imageURL;
    }

    // Getters & Setters
    public int getCarID() {
        return carID;
    }
    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }
    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }
    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    // toString() (tùy chọn)
    @Override
    public String toString() {
        return "Car{" +
                "carID=" + carID +
                ", carName='" + carName + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", yearOfManufacture=" + yearOfManufacture +
                ", weight=" + weight +
                ", stockQuantity=" + stockQuantity +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
