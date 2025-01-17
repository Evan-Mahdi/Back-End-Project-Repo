package com.example.back_end_project_repo;

import com.example.back_end_project_repo.services.EventServices;
import com.example.back_end_project_repo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class BackEndProjectRepoApplication implements CommandLineRunner {
    @Autowired
    UserServices userServices;
    @Autowired
    EventServices eventServices;

    public static void main(String[] args) {
        SpringApplication.run(BackEndProjectRepoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            eventServices.cleanUpEvebtTable();
            userServices.cleanUpUserTable();

            // Load JSON files from classpath (assets folder in resources)
            ClassPathResource userResources = new ClassPathResource("users.json");
            ClassPathResource eventResource = new ClassPathResource("events.json");

            // Check if the resources exist
            if (!userResources.exists()) {
                System.out.println("Device JSON file not found.");
                return;
            }
            if (!eventResource.exists()) {
                System.out.println("Cart JSON file not found.");
                return;
            }

            // Read the JSON data
            try (InputStream userStream = userResources.getInputStream()) {
                userServices.importJsonData(userStream);
            } catch (IOException e) {
                System.out.println("Error reading User JSON: " + e.getMessage());
            }

            try (InputStream eventStream = eventResource.getInputStream()) {
                eventServices.importJsonData(eventStream);
            } catch (IOException e) {
                System.out.println("Error reading event JSON: " + e.getMessage());
            }

            System.out.println("Data imported from JSON files successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred during the data import process: " + e.getMessage());
        }
    }
}
