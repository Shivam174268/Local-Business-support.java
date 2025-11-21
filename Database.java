package com.localbiz.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton Database class to manage JDBC connections.
 */
public class Database {
    private static Database instance;
    private Connection connection;

    // JDBC configuration
    private final String URL = "jdbc:mysql://localhost:3306/localbiz";
    private final String USER = "root";
    private final String PASSWORD = "password";

    private Database() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to database");
        }
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
