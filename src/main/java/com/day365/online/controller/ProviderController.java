package com.day365.online.controller;

import com.day365.online.model.Provider;
import com.day365.online.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

	@Autowired
	private FirebaseService firebaseService;

	@PostMapping("/register")
	public ResponseEntity<String> registerProvider(@RequestBody Provider provider) {
		try {
			String result = firebaseService.saveProvider(provider);
			return ResponseEntity.ok("Provider registered successfully: " + result);
		} catch (ExecutionException | InterruptedException e) {
			return ResponseEntity.internalServerError().body("Error registering provider: " + e.getMessage());
		}
	}

	@GetMapping("/{providerId}")
	public ResponseEntity<Provider> getProvider(@PathVariable String providerId) {
		try {
			Provider provider = firebaseService.getProvider(providerId);
			if (provider != null) {
				return ResponseEntity.ok(provider);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (ExecutionException | InterruptedException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/service/{serviceType}")
	public ResponseEntity<List<Provider>> getProvidersByService(@PathVariable String serviceType) {
		try {
			List<Provider> providers = firebaseService.getProvidersByService(serviceType);
			return ResponseEntity.ok(providers);
		} catch (ExecutionException | InterruptedException e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}