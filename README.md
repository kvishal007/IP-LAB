# Exercise 7 - Session Tracking Using Java Servlets

## 1. Exercise
Exercise No. **7** - Session Tracking Using Java Servlets

## 2. Objective
Develop a web application using Java Servlets to demonstrate session tracking: login, session creation, storing/reading session data, session validation, timeout and logout.

Key concepts: `HttpSession`, `getSession()`, `setAttribute()`, `getAttribute()`, `setMaxInactiveInterval()`, `<session-timeout>` in `web.xml`, `invalidate()`.

## 3. Software Requirements
- JDK 11 or 17 (JDK 8+ also works)
- Apache Tomcat 9.x (uses `javax.servlet`; do NOT use Tomcat 10+ for this code)
- Any web browser

## 4. Folder Structure
```
ZIP_7_Servlet_Session_Tracking/
    README.md
    build.bat
    build.sh
    src/
        com/
            lab/
                LoginServlet.java
                LogoutServlet.java
                WelcomeServlet.java
    web/
        index.html
        login.html
        WEB-INF/
            web.xml
        css/
            style.css
```

## 5. Setup Instructions
1. Open a terminal inside the project folder.
2. Build the WAR (replace the path with your Tomcat path):
   - Windows: `build.bat "C:\apache-tomcat-9.0.xx\lib\servlet-api.jar"`
   - Linux/Mac: `./build.sh /opt/tomcat/lib/servlet-api.jar`
3. Copy the generated `SessionDemo.war` file into `<TOMCAT>/webapps/`.
4. Start Tomcat (`<TOMCAT>/bin/startup.bat` or `startup.sh`).

## 6. Database Instructions
Not required (demo users are hard coded in `LoginServlet`).

## 7. How to Execute
After deploying, open the URL below. Login with `student / lab123` or `admin / admin123`.

## 8. URL
`http://localhost:8080/SessionDemo/`

## 9. Expected Output
1. **Login page** with username and password fields.
2. After a correct login, the **Welcome page** shows `Welcome, student!`, the session ID, login time and timeout (120 seconds).
3. Clicking **Logout** calls `session.invalidate()` and shows `Logged Out`.
4. Opening `/SessionDemo/welcome` after logout (or after 2 minutes idle) shows **Session Expired**.
5. Wrong credentials show **Login Failed**.

## 10. Common Errors and Solutions
| Problem | Solution |
|---|---|
| `package javax.servlet does not exist` | Pass the correct path of Tomcat's `lib/servlet-api.jar` to the build script. |
| HTTP 404 | Check that the WAR name matches the URL and that Tomcat finished deploying (see `logs/catalina.out`). |
| Tomcat 10 shows errors | Use Tomcat 9. Tomcat 10 uses `jakarta.*` instead of `javax.*`. |
| Port 8080 already in use | Stop the other program or change the port in `conf/server.xml`. |
