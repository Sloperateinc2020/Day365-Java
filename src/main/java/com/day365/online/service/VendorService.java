package com.day365.online.service;

import com.day365.online.model.Provider;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class VendorService {

    private static final String COLLECTION_NAME = "vendors";

    public Provider createVendor(Provider vendor) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection(COLLECTION_NAME).document(vendor.getMobile()).set(vendor);
        return vendor;
    }

    public Provider getVendor(String mobile) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        return firestore.collection(COLLECTION_NAME).document(mobile).get().get().toObject(Provider.class);
    }
}