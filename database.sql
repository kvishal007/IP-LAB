-- Import in phpMyAdmin (Import tab) or run: mysql -u root < sql/database.sql
CREATE DATABASE IF NOT EXISTS order_db;
USE order_db;

CREATE TABLE IF NOT EXISTS orders (
    order_id      INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100)  NOT NULL,
    product_name  VARCHAR(100)  NOT NULL,
    quantity      INT           NOT NULL,
    price         DECIMAL(10,2) NOT NULL,
    order_date    DATE          NOT NULL
);

INSERT INTO orders (customer_name, product_name, quantity, price, order_date) VALUES
('Arun Kumar', 'Laptop', 1, 55000.00, '2026-09-10'),
('Priya Devi', 'Wireless Mouse', 2, 650.50, '2026-09-12'),
('Karthik S', 'Keyboard', 1, 1200.00, '2026-09-15');
