package com.localbiz.model;

/**
 * Represents an Admin user (inherits User)
 */
public class Admin extends User {
    public Admin(String id, String username, String passwordHash) {
        super(id, username, passwordHash);
    }
    // Admin-specific methods can be added here
}
