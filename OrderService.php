<?php
/**
 * BUSINESS LOGIC LAYER
 * Validates the form data and calls the repository.
 */
require_once __DIR__ . '/OrderRepository.php';

class OrderService
{
    private OrderRepository $repo;

    public function __construct()
    {
        $this->repo = new OrderRepository();
    }

    /** Validates input and saves the order. Returns an array of error messages (empty = success). */
    public function placeOrder(array $input): array
    {
        $errors = [];
        $customer = trim($input['customer'] ?? '');
        $product  = trim($input['product'] ?? '');
        $quantity = trim($input['quantity'] ?? '');
        $price    = trim($input['price'] ?? '');
        $date     = trim($input['order_date'] ?? '');

        if ($customer === '' || !preg_match('/^[A-Za-z .]{2,100}$/', $customer)) {
            $errors[] = 'Customer name must contain only letters (2-100 characters).';
        }
        if ($product === '' || strlen($product) > 100) {
            $errors[] = 'Product name is required (max 100 characters).';
        }
        if (!ctype_digit($quantity) || (int) $quantity < 1) {
            $errors[] = 'Quantity must be a whole number greater than 0.';
        }
        if (!is_numeric($price) || (float) $price <= 0) {
            $errors[] = 'Price must be a number greater than 0.';
        }
        $d = DateTime::createFromFormat('Y-m-d', $date);
        if (!$d || $d->format('Y-m-d') !== $date) {
            $errors[] = 'Order date is invalid.';
        }

        if (empty($errors)) {
            try {
                $this->repo->insert($customer, $product, (int) $quantity, (float) $price, $date);
            } catch (Exception $e) {
                $errors[] = 'Database error: ' . $e->getMessage();
            }
        }
        return $errors;
    }

    /** Returns all orders from the database. */
    public function getOrders(): array
    {
        return $this->repo->findAll();
    }
}
