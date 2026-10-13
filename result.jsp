<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%!
    /** Escapes HTML characters so that user input is displayed safely. */
    private String esc(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;").replace("<", "&lt;")
                   .replace(">", "&gt;").replace("\"", "&quot;");
    }
%>
<%
    request.setCharacterEncoding("UTF-8");

    // Retrieve form values
    String name   = request.getParameter("name");
    String email  = request.getParameter("email");
    String phone  = request.getParameter("phone");
    String gender = request.getParameter("gender");
    String course = request.getParameter("course");

    // Basic server-side validation
    java.util.List<String> errors = new java.util.ArrayList<String>();
    if (name == null || name.trim().isEmpty())
        errors.add("Name is required.");
    if (email == null || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
        errors.add("Enter a valid email address.");
    if (phone == null || !phone.matches("^[0-9]{10}$"))
        errors.add("Phone number must contain exactly 10 digits.");
    if (gender == null || gender.isEmpty())
        errors.add("Please select a gender.");
    if (course == null || course.trim().isEmpty())
        errors.add("Please select a course.");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Details</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="card wide">
<% if (!errors.isEmpty()) { %>
  <h2>Please Correct the Errors</h2>
  <div class="errors"><ul>
    <% for (String err : errors) { %>
      <li><%= esc(err) %></li>
    <% } %>
  </ul></div>
<% } else { %>
  <h2>Registration Details</h2>
  <table>
    <tr><th>Name</th><td><%= esc(name.trim()) %></td></tr>
    <tr><th>Email</th><td><%= esc(email.trim()) %></td></tr>
    <tr><th>Phone</th><td><%= esc(phone) %></td></tr>
    <tr><th>Gender</th><td><%= esc(gender) %></td></tr>
    <tr><th>Course</th><td><%= esc(course) %></td></tr>
  </table>
<% } %>
  <a class="btn" href="javascript:history.back()">Back</a>
  <a class="btn" href="register.html">New Registration</a>
</div>
</body>
</html>
