package com.localbiz.model;

/**
 * Represents a business location.
 */
public class Location {
    private String city;

    public Location(String city) { this.city = city; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}
