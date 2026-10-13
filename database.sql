-- Run in MySQL:  mysql -u root -p < sql/database.sql
CREATE DATABASE IF NOT EXISTS order_db;
USE order_db;

CREATE TABLE IF NOT EXISTS orders (
    order_id      INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100)  NOT NULL,
    product_name  VARCHAR(100)  NOT NULL,
    quantity      INT           NOT NULL,
    price         DECIMAL(10,2) NOT NULL,
    order_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO orders (customer_name, product_name, quantity, price) VALUES
('Arun Kumar', 'Laptop', 1, 55000.00),
('Priya Devi', 'Wireless Mouse', 2, 650.50),
('Karthik S', 'Keyboard', 1, 1200.00);
