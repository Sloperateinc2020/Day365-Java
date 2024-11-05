package com.day365.online.controller;

import com.day365.online.model.Booking;
import com.day365.online.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
		try {
			Booking createdBooking = bookingService.createBooking(booking);
			return ResponseEntity.ok(createdBooking);
		} catch (ExecutionException | InterruptedException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/completed")
	public ResponseEntity<List<Booking>> getCompletedBookings() {
		try {
			List<Booking> completedBookings = bookingService.getCompletedBookings();
			return ResponseEntity.ok(completedBookings);
		} catch (ExecutionException | InterruptedException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{bookingId}/cancel")
	public ResponseEntity<Booking> cancelBooking(@PathVariable String bookingId) {
		try {
			Booking cancelledBooking = bookingService.cancelBooking(bookingId);
			if (cancelledBooking != null) {
				return ResponseEntity.ok(cancelledBooking);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (ExecutionException | InterruptedException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{bookingId}/complete")
	public ResponseEntity<Booking> completeBooking(@PathVariable String bookingId) {
		try {
			Booking completedBooking = bookingService.completeBooking(bookingId);
			if (completedBooking != null) {
				return ResponseEntity.ok(completedBooking);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (ExecutionException | InterruptedException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Booking>> getUserBookings(@PathVariable String userId) {
		try {
			List<Booking> userBookings = bookingService.getAllBookingsByUser(userId);
			return ResponseEntity.ok(userBookings);
		} catch (ExecutionException | InterruptedException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/provider/{providerId}")
	public ResponseEntity<List<Booking>> getProviderBookings(@PathVariable String providerId) {
		try {
			List<Booking> providerBookings = bookingService.getAllBookingsByProvider(providerId);
			return ResponseEntity.ok(providerBookings);
		} catch (ExecutionException | InterruptedException e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}