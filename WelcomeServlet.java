package com.lab;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Shows the welcome page only if a valid session exists (session validation). */
public class WelcomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /** Escapes HTML special characters so user data is displayed safely. */
    private String escape(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;")
                   .replace(">", "&gt;").replace("\"", "&quot;");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // do not create a new one
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Session validation
        if (session == null || session.getAttribute("username") == null) {
            out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Session Invalid</title>");
            out.println("<link rel='stylesheet' href='css/style.css'></head><body><div class='card'>");
            out.println("<h2>Session Expired</h2>");
            out.println("<p class='error'>No active session. Please login again.</p>");
            out.println("<a class='btn' href='login.html'>Go to Login</a>");
            out.println("</div></body></html>");
            return;
        }

        String username = (String) session.getAttribute("username");
        String loginTime = (String) session.getAttribute("loginTime");

        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Welcome</title>");
        out.println("<link rel='stylesheet' href='css/style.css'></head><body><div class='card'>");
        out.println("<h2>Welcome, " + escape(username) + "!</h2>");
        out.println("<div class='info'>");
        out.println("Session ID: " + escape(session.getId()) + "<br>");
        out.println("Login time: " + escape(loginTime) + "<br>");
        out.println("Session timeout: " + session.getMaxInactiveInterval() + " seconds<br>");
        out.println("Is new session: " + session.isNew());
        out.println("</div>");
        out.println("<a class='btn' href='logout'>Logout</a>");
        out.println("</div></body></html>");
    }
}
