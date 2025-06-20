package com.pluralsight.dealership.controller;

import com.pluralsight.dealership.DAO.VehicleDAO;
import com.pluralsight.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleDAO vehicleDAO;

    @Autowired
    public VehicleController(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    // GET with search filters
    @GetMapping
    public List<Vehicle> searchVehicles(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer minMiles,
            @RequestParam(required = false) Integer maxMiles,
            @RequestParam(required = false) String type
    ) throws SQLException {
        // Prioritize one filter at a time for simplicity
        if (minPrice != null && maxPrice != null) return vehicleDAO.searchByPriceRange(minPrice, maxPrice);
        if (make != null && model != null) return vehicleDAO.searchByMakeModel(make, model);
        if (minYear != null && maxYear != null) return vehicleDAO.searchByYearRange(minYear, maxYear);
        if (color != null) return vehicleDAO.searchByColor(color);
        if (minMiles != null && maxMiles != null) return vehicleDAO.searchByMileageRange(minMiles, maxMiles);
        if (type != null) return vehicleDAO.searchByType(type);

        return List.of(); // Empty if no valid filter
    }

    // POST - Add a new vehicle
    @PostMapping
    public void addVehicle(@RequestBody Vehicle vehicle) throws SQLException {
        vehicleDAO.addVehicle(vehicle);
    }

    // PUT - Update vehicle
    @PutMapping("/{vin}")
    public void updateVehicle(@PathVariable String vin, @RequestBody Vehicle vehicle) throws SQLException {
        vehicle.setVin(vin);
        vehicleDAO.updateVehicle(vehicle);
    }

    // DELETE - Delete vehicle
    @DeleteMapping("/{vin}")
    public void deleteVehicle(@PathVariable String vin) throws SQLException {
        vehicleDAO.deleteVehicle(vin);
    }
}
