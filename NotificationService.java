package com.localbiz.service;

import com.localbiz.model.Notification;
import com.localbiz.db.Database;
import com.localbiz.util.IDGenerator;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Service for handling notifications.
 */
public class NotificationService implements ServiceInterface {
    private final Database db = Database.getInstance();
    private final Queue<Notification> notificationQueue = new ConcurrentLinkedQueue<>();

    public void sendNotification(Notification notification) throws SQLException {
        if (notification.getId() == null)
            notification = new Notification(IDGenerator.generate(), notification.getUserId(), notification.getMessage());

        String sql = "INSERT INTO notifications(id,user_id,message) VALUES(?,?,?)";
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, notification.getId());
            ps.setString(2, notification.getUserId());
            ps.setString(3, notification.getMessage());
            ps.executeUpdate();
        }
        notificationQueue.add(notification);
    }

    public List<Notification> getNotificationsForUser(String userId) throws SQLException {
        List<Notification> result = new ArrayList<>();
        String sql = "SELECT * FROM notifications WHERE user_id=?";
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new Notification(rs.getString("id"), rs.getString("user_id"), rs.getString("message")));
                }
            }
        }
        return result;
    }

    public Queue<Notification> getNotificationQueue() { return notificationQueue; }
}
