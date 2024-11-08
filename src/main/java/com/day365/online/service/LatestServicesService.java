package com.day365.online.service;

import com.day365.online.model.LatestServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LatestServicesService {

    @Autowired
    private ServicesFirebaseService servicesFirebaseService;

    // Save a latest service through the Firestore service
    public LatestServiceModel addLatestService(LatestServiceModel latestService) throws ExecutionException, InterruptedException {
        servicesFirebaseService.saveLatestService(latestService);
        return latestService;
    }

    // Retrieve all latest services
    public List<LatestServiceModel> getAllLatestServices() throws ExecutionException, InterruptedException {
        return servicesFirebaseService.getAllLatestServices();
    }
}
