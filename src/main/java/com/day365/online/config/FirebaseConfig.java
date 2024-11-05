package com.day365.online.config;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class FirebaseConfig {

	@Value("${firebase.database.url}")
    private String databaseUrl;

    @Value("${firebase.credentials.path}")
    private String credentialsPath;
    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(new ClassPathResource(credentialsPath).getInputStream());

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .setDatabaseUrl(databaseUrl)
                    .build();

            return FirebaseApp.initializeApp(options);
        } else {
            return FirebaseApp.getInstance();
        }
    }

    @Bean
    public Firestore firestore() {
        try {
            FirebaseApp firebaseApp = initializeFirebase();
            Firestore firestore = FirestoreClient.getFirestore(firebaseApp);
            log.info("Firestore initialized successfully.");
            return firestore;
        } catch (IOException e) {
            log.error("Error initializing Firestore: ", e);
            throw new RuntimeException("Failed to initialize Firestore", e);
        }
    }
}