package com.pluralsight.dealership.DAO;

import com.pluralsight.models.LeaseContract;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class LeaseContractDAO {
    private final BasicDataSource dataSource;

    public LeaseContractDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public LeaseContract getById(int id) throws SQLException {
        String sql = "SELECT * FROM lease_contracts WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new LeaseContract(
                        rs.getInt("id"),
                        rs.getString("vin"),
                        rs.getString("customer_name"),
                        rs.getDate("lease_start").toLocalDate(),
                        rs.getDate("lease_end").toLocalDate(),
                        rs.getDouble("monthly_payment")
                );
            }
        }
        return null;
    }

    public void add(LeaseContract contract) throws SQLException {
        String sql = "INSERT INTO lease_contracts (vin, customer_name, lease_start, lease_end, monthly_payment) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contract.getVin());
            stmt.setString(2, contract.getCustomerName());
            stmt.setDate(3, Date.valueOf(contract.getLeaseStart()));
            stmt.setDate(4, Date.valueOf(contract.getLeaseEnd()));
            stmt.setDouble(5, contract.getMonthlyPayment());
            stmt.executeUpdate();
        }
    }
}
