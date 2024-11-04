package com.day365.online.service;

import com.day365.online.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class BookingService {

    @Autowired
    private FirebaseService firebaseService;

    public Booking createBooking(Booking booking) throws ExecutionException, InterruptedException {
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setStatus("pending");
        firebaseService.saveBooking(booking);
        return booking;
    }

    public List<Booking> getCompletedBookings() throws ExecutionException, InterruptedException {
        return firebaseService.getBookingsByProvider("completed");
    }

    public Booking cancelBooking(String bookingId) throws ExecutionException, InterruptedException {
        Booking booking = firebaseService.getBooking(bookingId);
        if (booking != null && "pending".equals(booking.getStatus())) {
            firebaseService.updateBookingStatus(bookingId, "cancelled");
            booking.setStatus("cancelled");
        }
        return booking;
    }

    public Booking completeBooking(String bookingId) throws ExecutionException, InterruptedException {
        Booking booking = firebaseService.getBooking(bookingId);
        if (booking != null && "pending".equals(booking.getStatus())) {
            firebaseService.updateBookingStatus(bookingId, "completed");
            booking.setStatus("completed");
        }
        return booking;
    }

    public List<Booking> getAllBookingsByUser(String userId) throws ExecutionException, InterruptedException {
        return firebaseService.getBookingsByUser(userId);
    }

    public List<Booking> getAllBookingsByProvider(String providerId) throws ExecutionException, InterruptedException {
        return firebaseService.getBookingsByProvider(providerId);
    }
}