package com.lab;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Validates the user and creates a new HttpSession. */
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /** Checks the demo credentials (a real project would use a database). */
    private boolean isValidUser(String username, String password) {
        return ("student".equals(username) && "lab123".equals(password))
            || ("admin".equals(username) && "admin123".equals(password));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null && isValidUser(username.trim(), password)) {
            // Invalidate any old session, then create a fresh one (prevents session fixation)
            HttpSession old = request.getSession(false);
            if (old != null) {
                old.invalidate();
            }
            HttpSession session = request.getSession(true);

            // Store data in the session
            session.setAttribute("username", username.trim());
            session.setAttribute("loginTime", new java.util.Date().toString());
            session.setMaxInactiveInterval(2 * 60); // timeout = 2 minutes

            response.sendRedirect("welcome");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Login Failed</title>");
            out.println("<link rel='stylesheet' href='css/style.css'></head><body><div class='card'>");
            out.println("<h2>Login Failed</h2>");
            out.println("<p class='error'>Invalid username or password.</p>");
            out.println("<a class='btn' href='login.html'>Try Again</a>");
            out.println("</div></body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
