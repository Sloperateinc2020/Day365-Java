package com.day365.online.service;

import com.day365.online.model.LocationBasedServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LocationBasedService {

    @Autowired
    private LocationFirebaseService locationFirebaseService;

    public LocationBasedServiceModel addService(LocationBasedServiceModel service) throws ExecutionException, InterruptedException {
        locationFirebaseService.saveService(service);
        return service;
    }

    public List<LocationBasedServiceModel> getServicesByFilter(String city, String state, String pincode) throws ExecutionException, InterruptedException {
        // Retrieve services from Firestore by filtering using city, state, and pincode
        return locationFirebaseService.getServicesByFilter(city, state, pincode);
    }
}
