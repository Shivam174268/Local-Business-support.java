package com.localbusiness.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import com.localbusiness.util.UserStore;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        if (UserStore.validate(user, pass)) {
            req.getSession().setAttribute("user", user);
            res.sendRedirect("add.jsp");
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}
