package org.example.supermarketmanagementsystem;

public class productData {
    private String productID;
    private String brand;
    private String productName;
    private String status;
    private Double price;
    




    public String getProductID() {
        return productID;
    }

    public String getBrand() {
        return brand;
    }

    public String getProductName() {
        return productName;
    }

    public String getStatus() {
        return status;
    }

    public Double getPrice() {
        return price;
    }

    public productData(String productID, String brand, String productName, String status, Double price) {
        this.productID = productID;
        this.brand = brand;
        this.productName = productName;
        this.status = status;
        this.price = price;
    }

}
