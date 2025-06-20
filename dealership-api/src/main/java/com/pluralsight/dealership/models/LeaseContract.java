package com.pluralsight.models;

import java.time.LocalDate;

public class LeaseContract {
    private int id;
    private String vin;
    private String customerName;
    private LocalDate leaseStart;
    private LocalDate leaseEnd;
    private double monthlyPayment;

    public LeaseContract(int id, String vin, String customerName, LocalDate leaseStart, LocalDate leaseEnd, double monthlyPayment) {
        this.id = id;
        this.vin = vin;
        this.customerName = customerName;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;
        this.monthlyPayment = monthlyPayment;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public LocalDate getLeaseStart() { return leaseStart; }
    public void setLeaseStart(LocalDate leaseStart) { this.leaseStart = leaseStart; }
    public LocalDate getLeaseEnd() { return leaseEnd; }
    public void setLeaseEnd(LocalDate leaseEnd) { this.leaseEnd = leaseEnd; }
    public double getMonthlyPayment() { return monthlyPayment; }
    public void setMonthlyPayment(double monthlyPayment) { this.monthlyPayment = monthlyPayment; }
}