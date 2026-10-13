<?php
/**
 * index.php - E-Learning registration form + server-side validation.
 * The form posts to this same page. If everything is valid, a success page is shown.
 */
require_once 'validation.php';

$courses = ['Web Technology', 'Python Programming', 'Data Structures', 'Database Management', 'Machine Learning'];

$values = ['fullname' => '', 'email' => '', 'phone' => '', 'username' => '', 'password' => '', 'course' => ''];
$errors = [];
$success = false;

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Read and clean the inputs
    foreach ($values as $field => $default) {
        $values[$field] = isset($_POST[$field]) ? trim((string) $_POST[$field]) : '';
    }

    // Run every validator
    $errors['fullname'] = validateFullName($values['fullname']);
    $errors['email']    = validateEmail($values['email']);
    $errors['phone']    = validatePhone($values['phone']);
    $errors['username'] = validateUsername($values['username']);
    $errors['password'] = validatePassword($values['password']);
    $errors['course']   = validateCourse($values['course'], $courses);

    // Keep only the fields that have an error message
    $errors = array_filter($errors, function ($msg) { return $msg !== ''; });
    $success = count($errors) === 0;
}

/** Returns the css class for an invalid field. */
function cls(array $errors, string $field): string
{
    return isset($errors[$field]) ? 'invalid' : '';
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>E-Learning Registration</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="card">
<?php if ($success): ?>
    <h2>Registration Successful</h2>
    <div class="success">Welcome, <?= e($values['fullname']) ?>! Your account has been created.</div>
    <table>
        <tr><th>Full Name</th><td><?= e($values['fullname']) ?></td></tr>
        <tr><th>Email</th><td><?= e($values['email']) ?></td></tr>
        <tr><th>Phone</th><td><?= e($values['phone']) ?></td></tr>
        <tr><th>Username</th><td><?= e($values['username']) ?></td></tr>
        <tr><th>Password</th><td>********** (hidden)</td></tr>
        <tr><th>Course</th><td><?= e($values['course']) ?></td></tr>
    </table>
    <a class="btn" href="index.php">Register Another User</a>
<?php else: ?>
    <h2>E-Learning Registration</h2>
    <form method="post" action="index.php" novalidate>
        <label for="fullname">Full Name</label>
        <input type="text" id="fullname" name="fullname" value="<?= e($values['fullname']) ?>" class="<?= cls($errors, 'fullname') ?>">
        <?php if (isset($errors['fullname'])): ?><div class="error"><?= e($errors['fullname']) ?></div><?php endif; ?>

        <label for="email">Email</label>
        <input type="text" id="email" name="email" value="<?= e($values['email']) ?>" class="<?= cls($errors, 'email') ?>">
        <?php if (isset($errors['email'])): ?><div class="error"><?= e($errors['email']) ?></div><?php endif; ?>

        <label for="phone">Phone Number</label>
        <input type="text" id="phone" name="phone" value="<?= e($values['phone']) ?>" class="<?= cls($errors, 'phone') ?>">
        <?php if (isset($errors['phone'])): ?><div class="error"><?= e($errors['phone']) ?></div><?php endif; ?>

        <label for="username">Username</label>
        <input type="text" id="username" name="username" value="<?= e($values['username']) ?>" class="<?= cls($errors, 'username') ?>">
        <?php if (isset($errors['username'])): ?><div class="error"><?= e($errors['username']) ?></div><?php endif; ?>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="<?= cls($errors, 'password') ?>">
        <div class="hint">Min 8 chars: uppercase, lowercase, digit, special character</div>
        <?php if (isset($errors['password'])): ?><div class="error"><?= e($errors['password']) ?></div><?php endif; ?>

        <label for="course">Course</label>
        <select id="course" name="course" class="<?= cls($errors, 'course') ?>">
            <option value="">-- Select Course --</option>
            <?php foreach ($courses as $c): ?>
                <option value="<?= e($c) ?>" <?= $values['course'] === $c ? 'selected' : '' ?>><?= e($c) ?></option>
            <?php endforeach; ?>
        </select>
        <?php if (isset($errors['course'])): ?><div class="error"><?= e($errors['course']) ?></div><?php endif; ?>

        <button type="submit">Register</button>
    </form>
<?php endif; ?>
</div>
</body>
</html>
