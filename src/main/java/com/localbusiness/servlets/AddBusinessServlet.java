package com.localbusiness.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import com.localbusiness.model.Business;
import com.localbusiness.util.BusinessStore;

public class AddBusinessServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String name = req.getParameter("name");
        String category = req.getParameter("category");

        BusinessStore.addBusiness(new Business(name, category));
        res.sendRedirect("view.jsp");
    }
}
