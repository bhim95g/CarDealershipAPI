package com.pluralsight.dealership.controller;

import com.pluralsight.dealership.DAO.SalesContractDAO;
import com.pluralsight.models.SalesContract;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/sales")
public class SalesContractController {

    private final SalesContractDAO dao;

    public SalesContractController(SalesContractDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/{id}")
    public SalesContract getById(@PathVariable int id) throws SQLException {
        return dao.getById(id);
    }

    @PostMapping
    public void add(@RequestBody SalesContract contract) throws SQLException {
        dao.add(contract);
    }
}