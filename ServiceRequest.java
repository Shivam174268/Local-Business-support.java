package com.localbiz.model;

import java.time.Instant;

/**
 * Represents a service request made by a user.
 */
public class ServiceRequest {
    private String id;
    private String userId;
    private String businessId;
    private String description;
    private Instant createdAt;
    private boolean fulfilled;

    public ServiceRequest(String id, String userId, String businessId, String description) {
        this.id = id;
        this.userId = userId;
        this.businessId = businessId;
        this.description = description;
        this.createdAt = Instant.now();
        this.fulfilled = false;
    }

    // Getters & setters
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getBusinessId() { return businessId; }
    public String getDescription() { return description; }
    public Instant getCreatedAt() { return createdAt; }
    public boolean isFulfilled() { return fulfilled; }
    public void setFulfilled(boolean fulfilled) { this.fulfilled = fulfilled; }
}
