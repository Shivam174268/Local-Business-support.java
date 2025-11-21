package com.localbiz.model;

import java.time.Instant;

/**
 * Notification sent to a user.
 */
public class Notification {
    private String id;
    private String userId;
    private String message;
    private Instant createdAt;
    private boolean read;

    public Notification(String id, String userId, String message) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.createdAt = Instant.now();
        this.read = false;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getMessage() { return message; }
    public boolean isRead() { return read; }
    public void markRead() { this.read = true; }
}
