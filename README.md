# Exercise 13 - Asynchronous Client-Server Interaction Using XML and AJAX

## 1. Exercise
Exercise No. **13** - Asynchronous Client-Server Interaction Using XML and AJAX

## 2. Objective
Demonstrate asynchronous communication: JavaScript `XMLHttpRequest` requests XML from a PHP script and the page updates dynamically without reloading.

Key concepts: `XMLHttpRequest`, `onreadystatechange`, `readyState`/`status`, `responseXML`, DOM parsing of XML, `Content-Type: application/xml`.

## 3. Software Requirements
- XAMPP (PHP 7.4 or later, Apache only)
- Any web browser

## 4. Folder Structure
```
ZIP_13_AJAX_XML_Interaction/
    README.md
    get_students.php
    index.html
    css/
        style.css
    data/
        students.xml
    js/
        script.js
```

## 5. Setup Instructions
1. Copy this folder into `C:\xampp\htdocs\`.
2. Start **Apache** in XAMPP.

## 6. Database Instructions
Not required (data is in `data/students.xml`).

## 7. How to Execute
Open the URL and click **Load Student Data**.

## 8. URL
`http://localhost/ZIP_13_AJAX_XML_Interaction/`

## 9. Expected Output
1. A page with a **Load Student Data** button.
2. On click, a yellow `Loading data, please wait...` message appears (the server adds a 1-second delay for demo).
3. The table of students (ID, Name, Department, Year) is then shown **without reloading the page**.
4. If `data/students.xml` is missing, a red error message such as `Error 404: students.xml not found on the server.` is shown.
5. If Apache is stopped (or the page is opened with `file://`), `Error: could not reach the server...` is shown.

## 10. Common Errors and Solutions
| Problem | Solution |
|---|---|
| Error message on load | Open the page through `http://localhost/...`; AJAX will not work from `file://`. |
| Table does not appear | Check that `get_students.php` returns valid XML (open it directly in the browser). |
| 404 error | Folder name in `htdocs` must match the URL. |
