package com.localbiz.model;

/**
 * Admin extends User.
 */
public class Admin extends User {
    public Admin(String id, String username, String passwordHash) {
        super(id, username, passwordHash);
    }

    // Admin-specific operations can be added here
}
