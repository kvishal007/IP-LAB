<?php
/**
 * DATABASE ACCESS LAYER
 * Only this file talks to MySQL. All queries use prepared statements.
 */
require_once __DIR__ . '/../config/database.php';

class OrderRepository
{
    private mysqli $conn;

    public function __construct()
    {
        mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT); // throw exceptions on errors
        $this->conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
        $this->conn->set_charset('utf8mb4');
    }

    /** Insert one order using a prepared statement. */
    public function insert(string $customer, string $product, int $quantity, float $price, string $orderDate): void
    {
        $sql = 'INSERT INTO orders (customer_name, product_name, quantity, price, order_date) VALUES (?, ?, ?, ?, ?)';
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param('ssids', $customer, $product, $quantity, $price, $orderDate);
        $stmt->execute();
        $stmt->close();
    }

    /** Fetch all orders (newest first). */
    public function findAll(): array
    {
        $stmt = $this->conn->prepare(
            'SELECT order_id, customer_name, product_name, quantity, price, order_date FROM orders ORDER BY order_id DESC'
        );
        $stmt->execute();
        $result = $stmt->get_result();
        $rows = $result->fetch_all(MYSQLI_ASSOC);
        $stmt->close();
        return $rows;
    }
}
