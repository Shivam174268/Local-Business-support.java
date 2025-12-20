package com.localbusiness.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.localbusiness.model.Business;
import com.localbusiness.util.BusinessStore;

public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        List<Business> result = BusinessStore.getBusinesses()
                .stream()
                .filter(b -> b.getName().toLowerCase().contains(keyword.toLowerCase())
                          || b.getCategory().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        request.setAttribute("results", result);
        request.getRequestDispatcher("view.jsp").forward(request, response);
    }
}
