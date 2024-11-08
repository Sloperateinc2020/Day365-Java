package com.day365.online.service;

import com.day365.online.model.LatestServiceModel;
import com.day365.online.model.LocationBasedServiceModel;
import com.day365.online.model.TopServiceModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ServicesFirebaseService {

    @Autowired
    private  Firestore firestore;

    private static final String SERVICES_COLLECTION = "locationBasedServices";
    private static final String TOP_SERVICES_COLLECTION = "topServices";
    private static final String LATEST_SERVICES_COLLECTION = "latestServices";

    // save all LocationBasedServices objects into Firestore
    public void saveService(LocationBasedServiceModel service) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> collectionsApiFuture = firestore.collection(SERVICES_COLLECTION).add(service);
        collectionsApiFuture.get();
    }

    public List<LocationBasedServiceModel> getServicesByFilter(String city, String state, String pincode) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(SERVICES_COLLECTION)
                .whereEqualTo("city", city)
                .whereEqualTo("state", state)
                .whereEqualTo("pincode", pincode)
                .get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<LocationBasedServiceModel> services = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            services.add(document.toObject(LocationBasedServiceModel.class));
        }
        return services;
    }

    // save all TopServiceModel objects into Firestore
    public void saveTopService(TopServiceModel topService) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> future = firestore.collection(TOP_SERVICES_COLLECTION).add(topService);
        future.get();
    }

    public List<TopServiceModel> getAllTopServices() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(TOP_SERVICES_COLLECTION).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<TopServiceModel> topServices = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            topServices.add(document.toObject(TopServiceModel.class));
        }
        return topServices;
    }

    // save all LatestService objects into Firestore
    public void saveLatestService(LatestServiceModel latestService) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionsApiFuture = firestore.collection(LATEST_SERVICES_COLLECTION).add(latestService);
        collectionsApiFuture.get();
    }

    public List<LatestServiceModel> getAllLatestServices() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(LATEST_SERVICES_COLLECTION).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<LatestServiceModel> services = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            services.add(document.toObject(LatestServiceModel.class));
        }
        return services;
    }
}
