package com.localbiz.model;

import java.time.Instant;

/**
 * Represents a Review given by a user to a business.
 */
public class Review {
    private String id;
    private String userId;
    private String businessId;
    private int rating; // 1 to 5
    private String comment;
    private Instant createdAt;

    public Review(String id, String userId, String businessId, int rating, String comment) {
        this.id = id;
        this.userId = userId;
        this.businessId = businessId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = Instant.now();
    }

    // Getters
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getBusinessId() { return businessId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public Instant getCreatedAt() { return createdAt; }
}
