package com.pluralsight.model;

import java.time.LocalDate;

public class SalesContract {
    private int id;
    private String customerName;
    private LocalDate date;
    private double price;
    private double tax;
    private double fees;
    private double totalPrice;
    private boolean finance;
    private int vehicleId;

    // Constructors
    public SalesContract(int id, String customerName, LocalDate date, double price, double tax,
                         double fees, double totalPrice, boolean finance, int vehicleId) {
        this.id = id;
        this.customerName = customerName;
        this.date = date;
        this.price = price;
        this.tax = tax;
        this.fees = fees;
        this.totalPrice = totalPrice;
        this.finance = finance;
        this.vehicleId = vehicleId;
    }

    public SalesContract(String customerName, LocalDate date, double price, double tax,
                         double fees, double totalPrice, boolean finance, int vehicleId) {
        this(0, customerName, date, price, tax, fees, totalPrice, finance, vehicleId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}
