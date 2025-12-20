package com.localbusiness.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ViewBusinessServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        req.getRequestDispatcher("view.jsp").forward(req, res);
    }
}
