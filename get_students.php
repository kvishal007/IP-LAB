<?php
/**
 * get_students.php - server script called by AJAX.
 * It reads data/students.xml and sends the XML back to the browser.
 * ?delay=1 adds a 1-second pause so the "Loading..." message can be seen in the demo.
 */
$xmlFile = __DIR__ . '/data/students.xml';

if (isset($_GET['delay'])) {
    sleep(1);
}

if (!file_exists($xmlFile)) {
    http_response_code(404);
    header('Content-Type: text/plain; charset=UTF-8');
    echo 'students.xml not found on the server.';
    exit;
}

$xmlContent = file_get_contents($xmlFile);
if ($xmlContent === false) {
    http_response_code(500);
    header('Content-Type: text/plain; charset=UTF-8');
    echo 'Unable to read the XML file.';
    exit;
}

header('Content-Type: application/xml; charset=UTF-8');
header('Cache-Control: no-cache');
echo $xmlContent;
