package com.pluralsight.dealership.models;

public class Vehicle {
    private String vin;
    private int year;
    private String make;
    private String model;
    private String color;
    private int odometer;
    private double price;
    private String type;

    //Constructor
    public Vehicle(String vin, int year, String make, String model, String color, int odometer, double price, String type) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
        this.type = type;
    }

    //Getters
    public int getVin() {
        return vin;
    }
    public int getYear() {
        return year;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public String getVehicleType() {
        return type;
    }
    public String getColor() {
        return color;
    }

    //Setters
    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVehicleType(String vehicleType) {
        this.type = vehicleType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOdometer() {
        return odometer;
    }
    public double getPrice() {
        return price;
    }

    //toString Method
    public String toString() {
        return vin + " " + year + " " + make + " " + model + " " + type + " " + color + " " + odometer + " " + price;
    }
}

