# Exercise 11 - Online Order Management Using PHP and MySQL (Three-Tier)

## 1. Exercise
Exercise No. **11** - Online Order Management Using PHP and MySQL (Three-Tier)

## 2. Objective
Develop a three-tier PHP and MySQL application to store (customer, product, quantity, price, order date) and display online orders.

Key concepts: `mysqli` prepared statements (`prepare`, `bind_param`, `execute`), layered design with classes, input validation, `htmlspecialchars()`.

## 3. Software Requirements
- XAMPP (PHP 7.4 or later, Apache, MySQL/MariaDB, phpMyAdmin)
- Any web browser

## 4. Folder Structure
```
ZIP_11_PHP_MySQL_Online_Orders/
    README.md
    index.php
    config/
        database.php
    css/
        style.css
    includes/
        OrderRepository.php
        OrderService.php
    sql/
        database.sql
```

## 5. Setup Instructions
**Architecture**
- Presentation layer: `index.php`
- Business logic layer: `includes/OrderService.php`
- Database access layer: `includes/OrderRepository.php` (prepared statements) + `config/database.php`

**Steps**
1. Copy this folder into `C:\xampp\htdocs\`.
2. Start **Apache** and **MySQL** in the XAMPP Control Panel.
3. Create the database (see below).
4. If your MySQL root user has a password, edit `config/database.php`.

## 6. Database Instructions
1. Open http://localhost/phpmyadmin
2. Click the **Import** tab, choose `sql/database.sql` and press **Go**.
   (Or run `mysql -u root < sql/database.sql`.)

This creates database `order_db`, table `orders` and 3 sample orders.

## 7. How to Execute
Open the URL, fill in the order form and press **Place Order**.

## 8. URL
`http://localhost/ZIP_11_PHP_MySQL_Online_Orders/`

## 9. Expected Output
1. An order form with customer, product, quantity, price and order date.
2. An **All Orders** table (ID, customer, product, qty, price, total, date) with the sample rows.
3. After submitting, `Order saved successfully!` is shown and the new order appears at the top.
4. Invalid values show red error messages and nothing is saved.

## 10. Common Errors and Solutions
| Problem | Solution |
|---|---|
| `Could not connect to the database` | Start MySQL in XAMPP and import `sql/database.sql`. |
| `Access denied for user` | Fix `DB_USER` / `DB_PASS` in `config/database.php`. |
| `Unknown database 'order_db'` | Import `sql/database.sql`. |
| Blank page | Open via `http://localhost/`, not `file://`. |
