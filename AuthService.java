package com.localbiz.service;

import com.localbiz.model.User;
import com.localbiz.service.UserService;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Authentication service for users.
 */
public class AuthService implements ServiceInterface {
    private final UserService userService = new UserService();

    public boolean authenticate(String username, String password) throws SQLException {
        for (User u : userService.listAllUsers()) {
            if (u.getUsername().equals(username) && u.getPasswordHash().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
