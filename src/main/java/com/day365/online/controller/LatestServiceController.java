package com.day365.online.controller;

import com.day365.online.model.LatestServiceModel;
import com.day365.online.service.LatestServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/latestServices")
public class LatestServiceController {

    @Autowired
    private LatestServicesService latestServicesService;

    // Endpoint to add a new latest service
    @PostMapping
    public ResponseEntity<LatestServiceModel> addLatestService(@RequestBody LatestServiceModel latestService) {
        try {
            LatestServiceModel createdService = latestServicesService.addLatestService(latestService);
            return ResponseEntity.ok(createdService);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Endpoint to retrieve all latest services
    @GetMapping
    public ResponseEntity<List<LatestServiceModel>> getAllLatestServices() {
        try {
            List<LatestServiceModel> latestServices = latestServicesService.getAllLatestServices();
            return ResponseEntity.ok(latestServices);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
