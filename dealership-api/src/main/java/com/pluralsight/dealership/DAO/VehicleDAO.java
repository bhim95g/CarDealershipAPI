package com.pluralsight.dealership.DAO;

import java.sql.*;
import com.pluralsight.model.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;
import java.util.ArrayList;
import java.util.List;

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
            try (ResultSet rs = stmt.executeQuery()) {
                return mapVehicles(rs);
            }
        }
    }

    public List<Vehicle> searchByMakeModel(String make, String model) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, make);
            stmt.setString(2, model);
            try (ResultSet rs = stmt.executeQuery()) {
                return mapVehicles(rs);
            }
        }
    }

    public List<Vehicle> searchByYearRange(int start, int end) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, start);
            stmt.setInt(2, end);
            try (ResultSet rs = stmt.executeQuery()) {
                return mapVehicles(rs);
            }
        }

    }

    public List<Vehicle> searchByColor(String color) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE color = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, color);
            try(ResultSet rs = stmt.executeQuery()) {
                return mapVehicles(rs);
            }
        }
    }

    public List<Vehicle> searchByMileageRange(int min, int max) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, min);
            stmt.setInt(2, max);
            try (ResultSet rs = stmt.executeQuery()) {
                return mapVehicles(rs);
            }
        }
    }

    public List<Vehicle> searchByType(String type) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE vehicle_type = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);
            try (ResultSet rs = stmt.executeQuery()) {
                return mapVehicles(rs);
            }
        }
    }


    private List<Vehicle> mapVehicles(ResultSet rs) throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        while (rs.next()) {
            Vehicle v = new Vehicle(
                    rs.getString("vin"),
                    rs.getInt("year"),
                    rs.getString("make"),
                    rs.getString("model"),
                    rs.getString("color"),
                    rs.getInt("odometer"),
                    rs.getDouble("price"),
                    rs.getString("type")
            );
            vehicles.add(v);
        }
        return vehicles;
    }

}
