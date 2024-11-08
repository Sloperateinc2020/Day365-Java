package com.day365.online.service;

import com.day365.online.model.TopServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TopServicesService {

    @Autowired
    private ServicesFirebaseService servicesFirebaseService;

    public TopServiceModel addTopService(TopServiceModel topService) throws ExecutionException, InterruptedException {
        servicesFirebaseService.saveTopService(topService);
        return topService;
    }

    public List<TopServiceModel> getAllTopServices() throws ExecutionException, InterruptedException {
        return servicesFirebaseService.getAllTopServices();
    }
}
