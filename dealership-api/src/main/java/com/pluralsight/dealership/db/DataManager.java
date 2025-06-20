package com.pluralsight.dealership.db;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Basic DataManager class that serves as a simple middleman for getting data
 * from different tables. This demonstrates a rudimentary approach before
 * implementing proper DAO patterns.
 */
public class DataManager {
    private final BasicDataSource dataSource;

    /**
     * Constructor that initializes the BasicDataSource with database connection parameters
     */
    public DataManager(String url, String username, String password) {
        this.dataSource = new BasicDataSource();
        this.dataSource.setUrl(url);
        this.dataSource.setUsername(username);
        this.dataSource.setPassword(password);
    }

    /**
     * Get all products - simple approach
     */
    public void getAllVehicles() {
        String query = "SELECT Vin, Year, Make, Model, Color, Odometer, Price, Type FROM Vehicles";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet results = statement.executeQuery()) {

            System.out.printf("%-10s %-35s %-12s %-15s%n", "Vin", "Year", "Make", "Model", "Color", "Odometer", "Price", "Type");
            System.out.println("---------------------------------------------------------------------------------------------------");

            while (results.next()) {
                String vin = results.getString("Vin");
                int year = results.getInt("Year");
                String make = results.getString("Make");
                String model = results.getString("Model");
                String color = results.getString("Color");
                int odometer = results.getInt("Odometer");
                double price = results.getDouble("Price");
                String type = results.getString("Type");

                System.out.printf("%-10s %-35s %-12s %-15s%n", vin, year, make, model, color, odometer, price, type);
            }
        } catch (SQLException e) {
            System.err.println("Error getting vehicles: " + e.getMessage());
        }
    }


    /**
     * Get the BasicDataSource instance (useful for DAOs)
     *
     * @return BasicDataSource instance
     */
    public BasicDataSource getDataSource() {
        return dataSource;
    }

    /**
     * Close the data source and release all connections
     */
    public void close() throws SQLException {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}