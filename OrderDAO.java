package com.lab.dao;

import com.lab.model.Order;
import com.lab.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** DATA ACCESS LAYER: the only class that runs SQL queries. */
public class OrderDAO {

    public void insertOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (customer_name, product_name, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, order.getCustomerName());
            ps.setString(2, order.getProductName());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getPrice());
            ps.executeUpdate();
        }
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<Order>();
        String sql = "SELECT order_id, customer_name, product_name, quantity, price, order_date "
                   + "FROM orders ORDER BY order_id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt("order_id"));
                o.setCustomerName(rs.getString("customer_name"));
                o.setProductName(rs.getString("product_name"));
                o.setQuantity(rs.getInt("quantity"));
                o.setPrice(rs.getDouble("price"));
                o.setOrderDate(rs.getTimestamp("order_date"));
                orders.add(o);
            }
        }
        return orders;
    }
}
