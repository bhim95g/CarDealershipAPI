package com.pluralsight.dealership.DAO;

import com.pluralsight.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleDAO {
    private final BasicDataSource dataSource;

    public VehicleDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Vehicle> searchByPriceRange(double min, double max) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, min);
            stmt.setDouble(2, max);
            return mapVehicles(stmt.executeQuery());
        }
    }

    public List<Vehicle> searchByMakeModel(String make, String model) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, make);
            stmt.setString(2, model);
            return mapVehicles(stmt.executeQuery());
        }
    }

    public List<Vehicle> searchByYearRange(int min, int max) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, min);
            stmt.setInt(2, max);
            return mapVehicles(stmt.executeQuery());
        }
    }

    public List<Vehicle> searchByColor(String color) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE color = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, color);
            return mapVehicles(stmt.executeQuery());
        }
    }

    public List<Vehicle> searchByMileageRange(int min, int max) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, min);
            stmt.setInt(2, max);
            return mapVehicles(stmt.executeQuery());
        }
    }

    public List<Vehicle> searchByType(String type) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE type = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type);
            return mapVehicles(stmt.executeQuery());
        }
    }

    public void addVehicle(Vehicle v) throws SQLException {
        String sql = "INSERT INTO vehicles (vin, year, make, model, color, odometer, price, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getVin());
            stmt.setInt(2, v.getYear());
            stmt.setString(3, v.getMake());
            stmt.setString(4, v.getModel());
            stmt.setString(5, v.getColor());
            stmt.setInt(6, v.getOdometer());
            stmt.setDouble(7, v.getPrice());
            stmt.setString(8, v.getType());
            stmt.executeUpdate();
        }
    }

    public void updateVehicle(Vehicle v) throws SQLException {
        String sql = "UPDATE vehicles SET year=?, make=?, model=?, color=?, odometer=?, price=?, type=? WHERE vin=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, v.getYear());
            stmt.setString(2, v.getMake());
            stmt.setString(3, v.getModel());
            stmt.setString(4, v.getColor());
            stmt.setInt(5, v.getOdometer());
            stmt.setDouble(6, v.getPrice());
            stmt.setString(7, v.getType());
            stmt.setString(8, v.getVin());
            stmt.executeUpdate();
        }
    }

    public void deleteVehicle(String vin) throws SQLException {
        String sql = "DELETE FROM vehicles WHERE vin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vin);
            stmt.executeUpdate();
        }
    }

    private List<Vehicle> mapVehicles(ResultSet rs) throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        while (rs.next()) {
            vehicles.add(new Vehicle(
                    rs.getString("vin"), rs.getInt("year"), rs.getString("make"), rs.getString("model"),
                    rs.getString("color"), rs.getInt("odometer"), rs.getDouble("price"), rs.getString("type")));
        }
        return vehicles;
    }
}