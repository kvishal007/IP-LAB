# Exercise 8 - JSP Script to Display User Registration Details

## 1. Exercise
Exercise No. **8** - JSP Script to Display User Registration Details

## 2. Objective
Create an HTML registration form and a JSP page that retrieves the submitted values with `request.getParameter()` and displays them on a web page.

Key concepts: `request.getParameter()`, JSP scriptlets/expressions/declarations, server-side validation, HTML escaping.

## 3. Software Requirements
- Apache Tomcat 9.x (JSP is compiled automatically; no JDK compile step is needed)
- JDK 11 or 17
- Any web browser

## 4. Folder Structure
```
ZIP_8_JSP_Registration_Details/
    README.md
    web/
        index.jsp
        register.html
        result.jsp
        WEB-INF/
            web.xml
        css/
            style.css
```

## 5. Setup Instructions
1. Copy the **`web`** folder to `<TOMCAT>/webapps/` and rename it to `RegistrationApp`
   (or zip the contents of `web` as `RegistrationApp.war`: `cd web && jar -cvf ../RegistrationApp.war .`).
2. Start Tomcat (`<TOMCAT>/bin/startup.bat` or `startup.sh`).

## 6. Database Instructions
Not required.

## 7. How to Execute
Fill the form and press **Register**.

## 8. URL
`http://localhost:8080/RegistrationApp/`

## 9. Expected Output
1. A styled form with Name, Email, Phone, Gender (radio) and Course (drop-down).
2. After submit, `result.jsp` shows a table with all submitted details.
3. If data is invalid (e.g. phone not 10 digits), a red list of errors is shown instead.
4. **Back** returns to the form; **New Registration** opens a blank form.

## 10. Common Errors and Solutions
| Problem | Solution |
|---|---|
| HTTP 404 for `/RegistrationApp/` | Check the folder name in `webapps` and that Tomcat started without errors. |
| Garbled characters | The page already uses UTF-8; make sure your browser is not forcing another encoding. |
| `HTTP 500 - JSP compile error` | Use a JDK (not only a JRE) and set `JAVA_HOME` before starting Tomcat. |
