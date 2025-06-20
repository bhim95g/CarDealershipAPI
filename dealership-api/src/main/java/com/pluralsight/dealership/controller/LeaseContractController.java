package com.pluralsight.dealership.controller;

import com.pluralsight.dealership.DAO.LeaseContractDAO;
import com.pluralsight.models.LeaseContract;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/leases")
public class LeaseContractController {

    private final LeaseContractDAO dao;

    public LeaseContractController(LeaseContractDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/{id}")
    public LeaseContract getById(@PathVariable int id) throws SQLException {
        return dao.getById(id);
    }

    @PostMapping
    public void add(@RequestBody LeaseContract contract) throws SQLException {
        dao.add(contract);
    }
}
