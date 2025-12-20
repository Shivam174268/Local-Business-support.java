package com.localbusiness.models;

/**
 * Business model class
 */
public class Business {
    private String name;
    private String owner;
    private String category;
    private String address;
    private String phone;

    public Business(String name, String owner, String category, String address, String phone) {
        this.name = name;
        this.owner = owner;
        this.category = category;
        this.address = address;
        this.phone = phone;
    }

    public String getName() { return name; }
    public String getOwner() { return owner; }
    public String getCategory() { return category; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }

    // CSV line helper
    public String toCSV() {
        return escape(name) + "," + escape(owner) + "," + escape(category) + "," + escape(address) + "," + escape(phone);
    }

    public static Business fromCSV(String line) {
        String[] parts = line.split(",", -1);
        for (int i = 0; i < parts.length; i++) {
            parts[i] = unescape(parts[i]);
        }
        if (parts.length < 5) return null;
        return new Business(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace(",", "\,").replace("\n"," ");
    }

    private static String unescape(String s) {
        if (s == null) return "";
        return s.replace("\\,", ",").replace("\\\\","\\");
    }
}
