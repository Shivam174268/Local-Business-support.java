package com.localbiz.service;

import com.localbiz.model.Business;
import com.localbiz.model.Category;
import com.localbiz.model.Location;
import com.localbiz.db.Database;
import com.localbiz.util.IDGenerator;
import com.localbiz.util.Validator;

import java.sql.*;
import java.util.*;

/**
 * Service class to handle business-related operations.
 * Demonstrates JDBC, exception handling, Collections, synchronization.
 */
public class BusinessService implements ServiceInterface {
    private final Map<String, Business> cache = Collections.synchronizedMap(new HashMap<>());
    private final Database db = Database.getInstance();

    public Business createBusiness(Business b) throws SQLException {
        if (b.getName() == null || b.getName().isBlank())
            throw new IllegalArgumentException("Business name required");
        if (b.getPhone() != null && !Validator.isValidPhone(b.getPhone()))
            throw new IllegalArgumentException("Invalid phone number");

        if (b.getId() == null) b = new Business(IDGenerator.generate(), b.getName(), b.getCategory(), b.getLocation());

        String sql = "INSERT INTO businesses(id,name,category,city,address,phone,description) VALUES(?,?,?,?,?,?,?)";
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.getId());
            ps.setString(2, b.getName());
            ps.setString(3, b.getCategory() != null ? b.getCategory().getName() : null);
            ps.setString(4, b.getLocation() != null ? b.getLocation().getCity() : null);
            ps.setString(5, b.getAddress());
            ps.setString(6, b.getPhone());
            ps.setString(7, b.getDescription());
            ps.executeUpdate();
            cache.put(b.getId(), b);
        }
        return b;
    }

    public Optional<Business> getBusinessById(String id) throws SQLException {
        if (cache.containsKey(id)) return Optional.of(cache.get(id));

        String sql = "SELECT * FROM businesses WHERE id=?";
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Business b = new Business(rs.getString("id"), rs.getString("name"),
                            new Category(rs.getString("category")), new Location(rs.getString("city")));
                    b.setAddress(rs.getString("address"));
                    b.setPhone(rs.getString("phone"));
                    b.setDescription(rs.getString("description"));
                    cache.put(id, b);
                    return Optional.of(b);
                }
            }
        }
        return Optional.empty();
    }

    public List<Business> listAllBusinesses() throws SQLException {
        String sql = "SELECT * FROM businesses";
        List<Business> result = new ArrayList<>();
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Business b = new Business(rs.getString("id"), rs.getString("name"),
                        new Category(rs.getString("category")), new Location(rs.getString("city")));
                b.setAddress(rs.getString("address"));
                b.setPhone(rs.getString("phone"));
                b.setDescription(rs.getString("description"));
                result.add(b);
                cache.put(b.getId(), b);
            }
        }
        return result;
    }
}
