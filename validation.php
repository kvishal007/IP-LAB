<?php
/**
 * validation.php - server-side validation functions using regular expressions.
 * Each function returns an error message (string) or "" when the value is valid.
 */

/** Full name: letters and spaces only, 3-50 characters. */
function validateFullName(string $name): string
{
    if ($name === '') {
        return 'Full name is required.';
    }
    if (!preg_match('/^[A-Za-z][A-Za-z .]{2,49}$/', $name)) {
        return 'Full name must be 3-50 letters (spaces and dots allowed).';
    }
    return '';
}

/** Email: standard pattern like name@domain.com. */
function validateEmail(string $email): string
{
    if ($email === '') {
        return 'Email is required.';
    }
    if (!preg_match('/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/', $email)) {
        return 'Enter a valid email address (example: name@example.com).';
    }
    return '';
}

/** Phone: exactly 10 digits, starting with 6, 7, 8 or 9 (Indian mobile numbers). */
function validatePhone(string $phone): string
{
    if ($phone === '') {
        return 'Phone number is required.';
    }
    if (!preg_match('/^[6-9][0-9]{9}$/', $phone)) {
        return 'Phone must be 10 digits and start with 6, 7, 8 or 9.';
    }
    return '';
}

/** Username: starts with a letter, 5-15 characters, letters/digits/underscore. */
function validateUsername(string $username): string
{
    if ($username === '') {
        return 'Username is required.';
    }
    if (!preg_match('/^[A-Za-z][A-Za-z0-9_]{4,14}$/', $username)) {
        return 'Username must be 5-15 characters, start with a letter, and use only letters, digits or underscore.';
    }
    return '';
}

/** Password: min 8 chars with uppercase, lowercase, digit and special character. */
function validatePassword(string $password): string
{
    if ($password === '') {
        return 'Password is required.';
    }
    if (!preg_match('/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$/', $password)) {
        return 'Password needs at least 8 characters with uppercase, lowercase, a digit and a special character.';
    }
    return '';
}

/** Course: must be one of the allowed courses. */
function validateCourse(string $course, array $allowed): string
{
    if ($course === '') {
        return 'Please select a course.';
    }
    if (!in_array($course, $allowed, true)) {
        return 'Selected course is not valid.';
    }
    return '';
}

/** Safe HTML output. */
function e(string $text): string
{
    return htmlspecialchars($text, ENT_QUOTES, 'UTF-8');
}
