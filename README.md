# Exercise 9 - Online Order Management Using JSP and JDBC (Three-Tier)

## 1. Exercise
Exercise No. **9** - Online Order Management Using JSP and JDBC (Three-Tier)

## 2. Objective
Develop a three-tier web application using JSP and JDBC that stores online order details in MySQL and displays all orders in a table.

Key concepts: JDBC `DriverManager`, `PreparedStatement`, `ResultSet`, try-with-resources, three-tier separation.

## 3. Software Requirements
- JDK 11 or 17 (JDK 8+ also works)
- Apache Tomcat 9.x (uses `javax.servlet`; do NOT use Tomcat 10+ for this code)
- Any web browser
- MySQL Server 5.7/8.x (or XAMPP's MySQL)
- MySQL Connector/J jar (`mysql-connector-j-8.x.jar`)

## 4. Folder Structure
```
ZIP_9_JSP_JDBC_Online_Orders/
    README.md
    build.bat
    build.sh
    sql/
        database.sql
    src/
        com/
            lab/
                dao/
                    OrderDAO.java
                model/
                    Order.java
                service/
                    OrderService.java
                util/
                    DBConnection.java
    web/
        index.jsp
        WEB-INF/
            web.xml
            lib/
                PUT_mysql-connector-j_JAR_HERE.txt
        css/
            style.css
```

## 5. Setup Instructions
**Architecture**
- Presentation layer: `web/index.jsp`
- Business logic layer: `service/OrderService.java`
- Data access layer: `dao/OrderDAO.java`, `util/DBConnection.java` (model: `model/Order.java`)

**Steps**
1. Create the database (see next section).
2. Download MySQL Connector/J from https://dev.mysql.com/downloads/connector/j/ and copy the `.jar` into `web/WEB-INF/lib/`.
3. If your MySQL has a password, edit `PASSWORD` in `src/com/lab/util/DBConnection.java`.
4. Build the WAR:
   - Windows: `build.bat "C:\apache-tomcat-9.0.xx\lib\servlet-api.jar"`
   - Linux/Mac: `./build.sh /opt/tomcat/lib/servlet-api.jar`
5. Copy `OrderApp.war` to `<TOMCAT>/webapps/` and start Tomcat.

## 6. Database Instructions
Start MySQL, then run:

```
mysql -u root -p < sql/database.sql
```
(or paste `sql/database.sql` into phpMyAdmin > SQL). This creates database `order_db`, table `orders` and 3 sample rows.

## 7. How to Execute
Open the URL, enter order details and press **Place Order**.

## 8. URL
`http://localhost:8080/OrderApp/`

## 9. Expected Output
1. The order form (customer, product, quantity, price) at the top.
2. Below it, an **All Orders** table with ID, customer, product, quantity, price, total and date.
3. After submitting, the message `Order saved successfully!` appears and the new row is shown first.
4. Invalid input (e.g. quantity `abc`) shows red validation messages.

## 10. Common Errors and Solutions
| Problem | Solution |
|---|---|
| `MySQL JDBC driver not found` | Copy the Connector/J jar into `web/WEB-INF/lib/` and rebuild the WAR. |
| `Access denied for user 'root'` | Set the correct password in `DBConnection.java` and rebuild. |
| `Unknown database 'order_db'` | Run `sql/database.sql` first. |
| `Communications link failure` | Start the MySQL server (port 3306). |
| `package javax.servlet does not exist` | Give the correct `servlet-api.jar` path to the build script. |
