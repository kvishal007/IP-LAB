<?php
/**
 * index.php - reads data/employees.xml with SimpleXML and shows it in an HTML table.
 * Handles a missing file and an invalid (badly formed) XML file.
 */
$xmlFile = __DIR__ . '/data/employees.xml';
$error = '';
$employees = null;

function e(string $text): string
{
    return htmlspecialchars($text, ENT_QUOTES, 'UTF-8');
}

if (!file_exists($xmlFile)) {
    $error = 'XML file not found: data/employees.xml';
} else {
    libxml_use_internal_errors(true);          // do not print raw PHP warnings
    $employees = simplexml_load_file($xmlFile);

    if ($employees === false) {
        $messages = [];
        foreach (libxml_get_errors() as $xmlError) {
            $messages[] = trim($xmlError->message) . ' (line ' . $xmlError->line . ')';
        }
        libxml_clear_errors();
        $error = 'The XML file is invalid: ' . implode('; ', $messages);
        $employees = null;
    } elseif (count($employees->employee) === 0) {
        $error = 'The XML file contains no employee records.';
    }
}

$total = 0;
?>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Employee Details from XML</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="card wide">
    <h2>Employee Details (Read from XML using SimpleXML)</h2>

    <?php if ($error !== ''): ?>
        <div class="error"><?= e($error) ?></div>
    <?php else: ?>
        <table>
            <tr><th>Employee ID</th><th>Name</th><th>Department</th><th>Salary (Rs.)</th></tr>
            <?php foreach ($employees->employee as $emp): ?>
                <?php $total += (float) $emp->salary; ?>
                <tr>
                    <td><?= e((string) $emp->id) ?></td>
                    <td><?= e((string) $emp->name) ?></td>
                    <td><?= e((string) $emp->department) ?></td>
                    <td class="num"><?= number_format((float) $emp->salary, 2) ?></td>
                </tr>
            <?php endforeach; ?>
        </table>
        <p>Total employees: <b><?= count($employees->employee) ?></b> &nbsp;|&nbsp;
           Total salary: <b>Rs. <?= number_format($total, 2) ?></b></p>
    <?php endif; ?>
</div>
</body>
</html>
