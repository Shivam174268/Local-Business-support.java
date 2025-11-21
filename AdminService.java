package com.localbiz.service;

import com.localbiz.model.Admin;
import com.localbiz.db.Database;
import com.localbiz.util.IDGenerator;

import java.sql.*;
import java.util.*;

/**
 * Service class for Admin operations.
 */
public class AdminService implements ServiceInterface {
    private final Map<String, Admin> adminCache = Collections.synchronizedMap(new HashMap<>());
    private final Database db = Database.getInstance();

    public Admin createAdmin(Admin admin) throws SQLException {
        if (admin.getId() == null) admin = new Admin(IDGenerator.generate(), admin.getUsername(), admin.getPasswordHash());
        String sql = "INSERT INTO admins(id,username,password_hash) VALUES(?,?,?)";
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, admin.getId());
            ps.setString(2, admin.getUsername());
            ps.setString(3, admin.getPasswordHash());
            ps.executeUpdate();
            adminCache.put(admin.getId(), admin);
        }
        return admin;
    }

    public Optional<Admin> getAdminById(String id) throws SQLException {
        if (adminCache.containsKey(id)) return Optional.of(adminCache.get(id));
        String sql = "SELECT * FROM admins WHERE id=?";
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Admin a = new Admin(rs.getString("id"), rs.getString("username"), rs.getString("password_hash"));
                    adminCache.put(a.getId(), a);
                    return Optional.of(a);
                }
            }
        }
        return Optional.empty();
    }
}
