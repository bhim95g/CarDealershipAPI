package com.pluralsight.dealership.DAO;

import com.pluralsight.models.SalesContract;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SalesContractDAO {
    private final BasicDataSource dataSource;

    public SalesContractDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public SalesContract getById(int id) throws SQLException {
        String sql = "SELECT * FROM sales_contracts WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SalesContract(
                        rs.getInt("id"),
                        rs.getString("vin"),
                        rs.getString("customer_name"),
                        rs.getDate("date_of_sale").toLocalDate(),
                        rs.getDouble("sale_price")
                );
            }
        }
        return null;
    }

    public void add(SalesContract contract) throws SQLException {
        String sql = "INSERT INTO sales_contracts (vin, customer_name, date_of_sale, sale_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contract.getVin());
            stmt.setString(2, contract.getCustomerName());
            stmt.setDate(3, Date.valueOf(contract.getDateOfSale()));
            stmt.setDouble(4, contract.getSalePrice());
            stmt.executeUpdate();
        }
    }
}