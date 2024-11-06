package com.day365.online.controller;

import com.day365.online.model.LocationBasedServiceModel;
import com.day365.online.service.LocationBasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/services")
public class LocationBasedServiceController {

    @Autowired
    private LocationBasedService locationBasedService;

    // Endpoint to add a new location-based service
    @PostMapping
    public ResponseEntity<?> addService(@RequestBody LocationBasedServiceModel service) throws ExecutionException, InterruptedException {
    try {
        LocationBasedServiceModel savedService = locationBasedService.addService(service);
        return ResponseEntity.ok(savedService);
    }catch (ExecutionException | InterruptedException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred while fetching services.");
    }

    }
    // Endpoint to get services filtered by city, state, and pincode
    @GetMapping
    public ResponseEntity<?> getServices(
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String pincode) {
        try {
            List<LocationBasedServiceModel> services = locationBasedService.getServicesByFilter(city, state, pincode);
            if (services.isEmpty()) {
                // Return a custom message when no services are found
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No services available in this location.");
            } else {
                return ResponseEntity.ok(services);
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching services.");
        }
    }
}
