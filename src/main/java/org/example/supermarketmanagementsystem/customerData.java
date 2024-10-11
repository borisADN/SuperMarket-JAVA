package org.example.supermarketmanagementsystem;

import java.sql.Date;

public class customerData {
    private int customerId;
    private String brand;
    private String productName;
    private  int quantity;
    private Double price;
    private Date date;

    public int getQuantity() {
        return quantity;
    }

    public customerData(int customerId, String brand, String productName, int quantity, Double price, Date date) {
        this.customerId = customerId;
        this.brand = brand;
        this.productName = productName;
        this.price = price;
        this.date = date;
        this.quantity=quantity;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getBrand() {
        return brand;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
}
