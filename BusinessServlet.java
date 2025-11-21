package com.localbiz.servlet;

import com.localbiz.model.Business;
import com.localbiz.service.BusinessService;
import com.localbiz.util.IDGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet to handle business operations: create and list businesses.
 */
@WebServlet("/business")
public class BusinessServlet extends HttpServlet {
    private final BusinessService businessService = new BusinessService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h2>All Businesses</h2>");
            for (Business b : businessService.listAllBusinesses()) {
                out.println("<p>" + b.getName() + " - " + b.getCategory().getName() + " - " + b.getLocation().getCity() + "</p>");
            }
        } catch (SQLException e) {
            throw new ServletException("Error fetching businesses", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String city = request.getParameter("city");
        try {
            Business b = new Business(IDGenerator.generate(), name, new com.localbiz.model.Category(category), new com.localbiz.model.Location(city));
            businessService.createBusiness(b);
            response.getWriter().println("Business created successfully: " + name);
        } catch (SQLException | IllegalArgumentException e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
