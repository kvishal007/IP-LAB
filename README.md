# Exercise 10 - E-Learning Registration with PHP Validation

## 1. Exercise
Exercise No. **10** - E-Learning Registration with PHP Validation

## 2. Objective
Develop a registration form for an e-learning platform and a PHP script that validates the inputs on the server using regular expressions.

Key concepts: `preg_match()`, `$_POST`, `htmlspecialchars()`, sticky form values, separate validation functions.

## 3. Software Requirements
- XAMPP (PHP 7.4 or later, Apache only)
- Any web browser

## 4. Folder Structure
```
ZIP_10_PHP_Elearning_Validation/
    README.md
    index.php
    validation.php
    css/
        style.css
```

## 5. Setup Instructions
1. Copy this project folder (`ZIP_10_PHP_Elearning_Validation`) into `C:\xampp\htdocs\` (Linux: `/opt/lampp/htdocs/`).
2. Start **Apache** from the XAMPP Control Panel.

## 6. Database Instructions
Not required.

## 7. How to Execute
Open the URL and submit the form (try wrong values first to see the errors).

## 8. URL
`http://localhost/ZIP_10_PHP_Elearning_Validation/`

## 9. Expected Output
1. A registration form with Full Name, Email, Phone, Username, Password and Course.
2. Invalid fields get a red border and a clear message, e.g. `Phone must be 10 digits and start with 6, 7, 8 or 9.` Entered values are kept.
3. When all fields are valid, a green **Registration Successful** page with a summary table is shown (password hidden).

Validation rules (regular expressions in `validation.php`):
- Full name: letters/spaces, 3-50 chars
- Email: `name@domain.xx`
- Phone: 10 digits starting with 6-9
- Username: 5-15 chars, starts with a letter, letters/digits/underscore
- Password: 8+ chars with upper, lower, digit and special character
- Course: must be from the list

## 10. Common Errors and Solutions
| Problem | Solution |
|---|---|
| Blank page / PHP code visible | Open via `http://localhost/...`, not by double-clicking the file. |
| 404 Not Found | The folder must be inside `htdocs` and the name must match the URL. |
| Apache will not start | Port 80 is in use (Skype/IIS). Change Apache's port in XAMPP config. |
