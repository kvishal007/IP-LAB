# Exercise 12 - Display XML Data Using PHP (SimpleXML)

## 1. Exercise
Exercise No. **12** - Display XML Data Using PHP (SimpleXML)

## 2. Objective
Develop a PHP application that reads employee data from an XML file with SimpleXML and displays it in an HTML table, handling missing or invalid XML.

Key concepts: `simplexml_load_file()`, `libxml_use_internal_errors()`, `file_exists()`, iterating XML nodes.

## 3. Software Requirements
- XAMPP (PHP 7.4 or later, Apache only)
- Any web browser
- PHP `simplexml` extension (enabled by default in XAMPP)

## 4. Folder Structure
```
ZIP_12_PHP_XML_Display/
    README.md
    index.php
    css/
        style.css
    data/
        employees.xml
```

## 5. Setup Instructions
1. Copy this folder into `C:\xampp\htdocs\`.
2. Start **Apache** in XAMPP.

## 6. Database Instructions
Not required (data is in `data/employees.xml`).

## 7. How to Execute
Open the URL. To test error handling, rename or break `data/employees.xml` and refresh.

## 8. URL
`http://localhost/ZIP_12_PHP_XML_Display/`

## 9. Expected Output
1. A table with Employee ID, Name, Department and Salary for 5 employees.
2. A footer line: `Total employees: 5 | Total salary: Rs. 274,000.00`.
3. If the XML file is missing: `XML file not found: data/employees.xml`.
4. If the XML is malformed: `The XML file is invalid: ...` with the parser's message.

## 10. Common Errors and Solutions
| Problem | Solution |
|---|---|
| `Call to undefined function simplexml_load_file()` | Enable `extension=simplexml` (or install `php-xml` on Linux) and restart Apache. |
| Table is empty | Check that records are inside `<employee>` tags in `data/employees.xml`. |
| 404 error | Folder must be inside `htdocs`. |
