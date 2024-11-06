package com.day365.online.service;

import com.day365.online.model.LocationBasedServiceModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LocationFirebaseService {

    @Autowired
    private  Firestore firestore;

    private static final String SERVICES_COLLECTION = "locationBasedServices";

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
}
