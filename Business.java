package com.localbiz.model;

import java.util.*;

/**
 * Represents a business.
 */
public class Business {
    private String id;
    private String name;
    private Category category;
    private Location location;
    private String address;
    private String phone;
    private String description;
    private final List<Review> reviews = Collections.synchronizedList(new ArrayList<>());

    public Business(String id, String name, Category category, Location location) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Review> getReviews() { return reviews; }
    public void addReview(Review review) { reviews.add(review); }
}
