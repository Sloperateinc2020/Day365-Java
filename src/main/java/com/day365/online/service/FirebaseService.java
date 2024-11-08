package com.day365.online.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.day365.online.model.Booking;
import com.day365.online.model.User;
import com.day365.online.model.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    private static final String USERS_COLLECTION = "users";
    private static final String PROVIDERS_COLLECTION = "providers";
    private static final String BOOKINGS_COLLECTION = "bookings";
    
    @Autowired
    private Firestore firestore;

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        
        ApiFuture<WriteResult> collectionsApiFuture = firestore.collection(USERS_COLLECTION).document(user.getId()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String saveProvider(Provider provider) throws ExecutionException, InterruptedException {
        
        ApiFuture<WriteResult> collectionsApiFuture = firestore.collection(PROVIDERS_COLLECTION).document(provider.getId()).set(provider);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String saveBooking(Booking booking) throws ExecutionException, InterruptedException {
        
        ApiFuture<WriteResult> collectionsApiFuture = firestore.collection(BOOKINGS_COLLECTION).document(booking.getBookingId()).set(booking);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public User getUser(String userId) throws ExecutionException, InterruptedException {
        
        DocumentReference documentReference = firestore.collection(USERS_COLLECTION).document(userId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        return document.exists() ? document.toObject(User.class) : null;
    }

    public Provider getProvider(String providerId) throws ExecutionException, InterruptedException {
        
        DocumentReference documentReference = firestore.collection(PROVIDERS_COLLECTION).document(providerId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        return document.exists() ? document.toObject(Provider.class) : null;
    }

    public Booking getBooking(String bookingId) throws ExecutionException, InterruptedException {
        
        DocumentReference documentReference = firestore.collection(BOOKINGS_COLLECTION).document(bookingId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        return document.exists() ? document.toObject(Booking.class) : null;
    }

    public List<Provider> getProvidersByService(String serviceType) throws ExecutionException, InterruptedException {
        
        ApiFuture<QuerySnapshot> future = firestore.collection(PROVIDERS_COLLECTION)
                .whereArrayContains("services", serviceType)
                .get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Provider> providers = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            providers.add(document.toObject(Provider.class));
        }
        return providers;
    }

    public List<Booking> getBookingsByUser(String userId) throws ExecutionException, InterruptedException {
        
        ApiFuture<QuerySnapshot> future = firestore.collection(BOOKINGS_COLLECTION)
                .whereEqualTo("userId", userId)
                .get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Booking> bookings = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            bookings.add(document.toObject(Booking.class));
        }
        return bookings;
    }

    public List<Booking> getBookingsByProvider(String providerId) throws ExecutionException, InterruptedException {
        
        ApiFuture<QuerySnapshot> future = firestore.collection(BOOKINGS_COLLECTION)
                .whereEqualTo("providerId", providerId)
                .get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Booking> bookings = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            bookings.add(document.toObject(Booking.class));
        }
        return bookings;
    }

    public String updateBookingStatus(String bookingId, String status) throws ExecutionException, InterruptedException {
        
        ApiFuture<WriteResult> collectionsApiFuture = firestore.collection(BOOKINGS_COLLECTION).document(bookingId)
                .update("status", status);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}
