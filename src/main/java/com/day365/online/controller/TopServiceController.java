package com.day365.online.controller;

import com.day365.online.model.TopServiceModel;
import com.day365.online.service.TopServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/topServices")
public class TopServiceController {

    @Autowired
    private TopServicesService topServicesService;

    @PostMapping
    public ResponseEntity<TopServiceModel> addTopService(@RequestBody TopServiceModel topService) {
        try {
            TopServiceModel createdService = topServicesService.addTopService(topService);
            return ResponseEntity.ok(createdService);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TopServiceModel>> getAllTopServices() {
        try {
            List<TopServiceModel> topServices = topServicesService.getAllTopServices();
            return ResponseEntity.ok(topServices);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
