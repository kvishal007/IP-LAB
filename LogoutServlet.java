package com.lab;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Destroys the session using invalidate(). */
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // removes session and all its attributes
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Logged Out</title>");
        out.println("<link rel='stylesheet' href='css/style.css'></head><body><div class='card'>");
        out.println("<h2>Logged Out</h2>");
        out.println("<p>You have been logged out successfully.</p>");
        out.println("<a class='btn' href='login.html'>Login Again</a>");
        out.println("</div></body></html>");
    }
}
