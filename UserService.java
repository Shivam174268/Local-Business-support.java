package com.localbiz.service;

import com.localbiz.model.User;
import com.localbiz.db.Database;
import com.localbiz.util.IDGenerator;
import com.localbiz.util.Validator;

import java.sql.*;
import java.util.*;

/**
 * Service class for user-related operations.
 */
public class UserService implements ServiceInterface {
    private final Map<String, User> userCache = Collections.synchronizedMap(new HashMap<>());
    private final Database db = Database.getInstance();

    public User createUser(User user) throws SQLException {
        if (user.getUsername() == null || user.getUsername().isBlank())
            throw new IllegalArgumentException("Username required");

        if (user.getPasswordHash() == null || user.getPasswordHash().length() < 6)
            throw new IllegalArgumentException("Password must be at least 6 characters");

        if (user.getId() == null) user = new User(IDGenerator.generate(), user.getUsername(), user.getPasswordHash());

        String sql = "INSERT INTO users(id,username,password_hash) VALUES(?,?,?)";
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPasswordHash());
            ps.executeUpdate();
            userCache.put(user.getId(), user);
        }
        return user;
    }

    public Optional<User> getUserById(String id) throws SQLException {
        if (userCache.containsKey(id)) return Optional.of(userCache.get(id));
        String sql = "SELECT * FROM users WHERE id=?";
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User(rs.getString("id"), rs.getString("username"), rs.getString("password_hash"));
                    userCache.put(u.getId(), u);
                    return Optional.of(u);
                }
            }
        }
        return Optional.empty();
    }

    public List<User> listAllUsers() throws SQLException {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User u = new User(rs.getString("id"), rs.getString("username"), rs.getString("password_hash"));
                result.add(u);
                userCache.put(u.getId(), u);
            }
        }
        return result;
    }
}
