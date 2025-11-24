package com.localbiz.servlet;

import com.localbiz.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet to handle user authentication
 */
@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (authService.authenticate(username, password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                response.getWriter().println("Login successful. Welcome " + username);
            } else {
                response.getWriter().println("Invalid credentials");
            }
        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
