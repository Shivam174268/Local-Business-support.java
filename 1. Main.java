package com.localbiz;

import com.localbiz.model.*;
import com.localbiz.service.*;
import com.localbiz.thread.NotificationWorker;
import com.localbiz.util.IDGenerator;
import com.localbiz.db.Database;

/**
 * Main class to start the application.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Local Business Support App...");

        // Initialize database
        Database db = Database.getInstance();

        // Initialize services
        BusinessService businessService = new BusinessService();
        UserService userService = new UserService();
        NotificationService notificationService = new NotificationService();

        // Sample Business
        Business b = new Business(IDGenerator.generate(), "Demo Cafe", new Category("Cafe"), new Location("Patna"));
        b.setPhone("9876543210");
        b.setAddress("MG Road, Patna");

        // Sample User
        User u = new User(IDGenerator.generate(), "shivam", "password123");

        try {
            // Add data to database
            businessService.createBusiness(b);
            userService.createUser(u);
            System.out.println("Sample data created.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start Notification Worker (multithreading)
        NotificationWorker worker = new NotificationWorker(notificationService);
        worker.startWorker();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }

        worker.stopWorker();
        System.out.println("Application terminated.");
    }
}
