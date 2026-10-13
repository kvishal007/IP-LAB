<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.lab.model.Order, com.lab.service.OrderService" %>
<%!
    /** Escapes HTML characters for safe output. */
    private String esc(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;").replace("<", "&lt;")
                   .replace(">", "&gt;").replace("\"", "&quot;");
    }
%>
<%
    // PRESENTATION LAYER: collects input, calls the service layer, shows results
    request.setCharacterEncoding("UTF-8");
    OrderService service = new OrderService();
    List<String> errors = new ArrayList<String>();
    boolean saved = false;

    if ("POST".equalsIgnoreCase(request.getMethod())) {
        errors = service.placeOrder(
            request.getParameter("customer"),
            request.getParameter("product"),
            request.getParameter("quantity") == null ? "" : request.getParameter("quantity"),
            request.getParameter("price") == null ? "" : request.getParameter("price"));
        saved = errors.isEmpty();
    }

    List<Order> orders = new ArrayList<Order>();
    String loadError = null;
    try {
        orders = service.getAllOrders();
    } catch (Exception e) {
        loadError = e.getMessage();
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Order Management</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="card xl">
  <h2>Online Order Management (JSP + JDBC)</h2>

  <% if (saved) { %><p class="ok">Order saved successfully!</p><% } %>
  <% if (!errors.isEmpty()) { %>
    <div class="errors"><ul>
      <% for (String err : errors) { %><li><%= esc(err) %></li><% } %>
    </ul></div>
  <% } %>

  <form action="index.jsp" method="post">
    <label>Customer Name</label>
    <input type="text" name="customer" required>
    <label>Product Name</label>
    <input type="text" name="product" required>
    <label>Quantity</label>
    <input type="text" name="quantity" required>
    <label>Price (per unit)</label>
    <input type="text" name="price" required>
    <button type="submit">Place Order</button>
  </form>

  <h2 style="margin-top:30px">All Orders</h2>
  <% if (loadError != null) { %>
    <p class="error">Could not load orders: <%= esc(loadError) %></p>
  <% } else if (orders.isEmpty()) { %>
    <p>No orders yet.</p>
  <% } else { %>
  <table>
    <tr><th>ID</th><th>Customer</th><th>Product</th><th>Qty</th><th>Price</th><th>Total</th><th>Date</th></tr>
    <% for (Order o : orders) { %>
    <tr>
      <td><%= o.getOrderId() %></td>
      <td><%= esc(o.getCustomerName()) %></td>
      <td><%= esc(o.getProductName()) %></td>
      <td class="num"><%= o.getQuantity() %></td>
      <td class="num"><%= String.format("%.2f", o.getPrice()) %></td>
      <td class="num"><%= String.format("%.2f", o.getTotal()) %></td>
      <td><%= o.getOrderDate() %></td>
    </tr>
    <% } %>
  </table>
  <% } %>
</div>
</body>
</html>
