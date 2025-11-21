package com.localbiz.servlet;

import com.localbiz.model.Notification;
import com.localbiz.service.NotificationService;
import com.localbiz.util.IDGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet to send notifications to users.
 */
@WebServlet("/notify")
public class NotificationServlet extends HttpServlet {
    private final NotificationService notificationService = new NotificationService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String message = request.getParameter("message");

        try {
            Notification n = new Notification(IDGenerator.generate(), userId, message);
            notificationService.sendNotification(n);
            response.getWriter().println("Notification sent to user: " + userId);
        } catch (SQLException e) {
            response.getWriter().println("Error sending notification: " + e.getMessage());
        }
    }
}
