package de;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/online_exam"
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=UTC";

    private static final String USER = "root";

    private static final String PASSWORD = "test@123";

    public static Connection getConnection()
            throws SQLException {

        System.out.println();
        System.out.println("========================================");
        System.out.println("       MYSQL CONNECTION STARTED");
        System.out.println("========================================");

        try {

            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );

            System.out.println(
                    "MySQL Driver: SUCCESS"
            );

        } catch (ClassNotFoundException e) {

            System.out.println(
                    "MySQL Driver: FAILED"
            );

            throw new SQLException(
                    "MySQL Connector/J not found!",
                    e
            );
        }

        try {

            Connection con =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println(
                    "MySQL Connection: SUCCESS"
            );

            System.out.println(
                    "Database: online_exam"
            );

            System.out.println(
                    "========================================"
            );

            return con;

        } catch (SQLException e) {

            System.out.println(
                    "MySQL Connection: FAILED"
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );

            System.out.println(
                    "SQL State: " + e.getSQLState()
            );

            System.out.println(
                    "Error Code: " + e.getErrorCode()
            );

            System.out.println(
                    "========================================"
            );

            throw e;
        }
    }
}
