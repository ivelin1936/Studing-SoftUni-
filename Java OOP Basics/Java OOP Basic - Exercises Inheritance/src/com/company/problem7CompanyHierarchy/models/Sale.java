package com.company.problem7CompanyHierarchy.models;

import java.util.Calendar;
import java.util.Date;

public class Sale {
    private String productName;
    private Calendar date;
    private double price;

    public Sale(String productName, Calendar date, double price) {
        this.setProductName(productName);
        this.setDate(date);
        this.setPrice(price);
    }

    protected String getProductName() {
        return productName;
    }

    private void setProductName(String productName) {
        if (productName == null || productName.length() < 3) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.productName = productName;
    }

    protected Calendar getDate() {
        return date;
    }

    private void setDate(Calendar date) {
        this.date = date;
    }

    protected double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "productName='" + productName + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
