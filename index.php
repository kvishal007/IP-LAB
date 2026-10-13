<?php
/**
 * PRESENTATION LAYER
 * Shows the order form and the orders table. All logic is delegated to OrderService.
 */
require_once 'includes/OrderService.php';

function e(string $text): string
{
    return htmlspecialchars($text, ENT_QUOTES, 'UTF-8');
}

$errors = [];
$saved = false;
$orders = [];
$loadError = '';

try {
    $service = new OrderService();

    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $errors = $service->placeOrder($_POST);
        $saved = empty($errors);
    }
    $orders = $service->getOrders();
} catch (Exception $e) {
    $loadError = 'Could not connect to the database: ' . $e->getMessage()
               . ' (Is MySQL running and database.sql imported?)';
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Online Order Management</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="card wide">
    <h2>Online Order Management (PHP + MySQL)</h2>

    <?php if ($saved): ?><div class="success">Order saved successfully!</div><?php endif; ?>
    <?php foreach ($errors as $err): ?><div class="error"><?= e($err) ?></div><?php endforeach; ?>
    <?php if ($loadError !== ''): ?><div class="error"><?= e($loadError) ?></div><?php endif; ?>

    <form method="post" action="index.php">
        <label>Customer Name</label>
        <input type="text" name="customer" required>
        <label>Product Name</label>
        <input type="text" name="product" required>
        <label>Quantity</label>
        <input type="number" name="quantity" min="1" required>
        <label>Price (per unit)</label>
        <input type="number" name="price" min="0.01" step="0.01" required>
        <label>Order Date</label>
        <input type="date" name="order_date" value="<?= date('Y-m-d') ?>" required>
        <button type="submit">Place Order</button>
    </form>

    <h2 style="margin-top:30px">All Orders</h2>
    <?php if (empty($orders)): ?>
        <p>No orders found.</p>
    <?php else: ?>
    <table>
        <tr><th>ID</th><th>Customer</th><th>Product</th><th>Qty</th><th>Price</th><th>Total</th><th>Date</th></tr>
        <?php foreach ($orders as $o): ?>
        <tr>
            <td><?= (int) $o['order_id'] ?></td>
            <td><?= e($o['customer_name']) ?></td>
            <td><?= e($o['product_name']) ?></td>
            <td class="num"><?= (int) $o['quantity'] ?></td>
            <td class="num"><?= number_format((float) $o['price'], 2) ?></td>
            <td class="num"><?= number_format($o['quantity'] * $o['price'], 2) ?></td>
            <td><?= e($o['order_date']) ?></td>
        </tr>
        <?php endforeach; ?>
    </table>
    <?php endif; ?>
</div>
</body>
</html>
