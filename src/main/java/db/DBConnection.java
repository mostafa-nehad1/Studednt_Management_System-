/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // 🔴 Change these to match your SQL Server setup
    private static final String URL =
            "jdbc:sqlserver://localhost:1433;databaseName=StudentManagement;encrypt=true;trustServerCertificate=true";

    private static final String USER = "sa";        // your SQL username
    private static final String PASSWORD = "your_password_here"; // your SQL password

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {

                // Load driver (important for older setups)
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database Connected Successfully ✅");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQL Server Driver not found ❌");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database Connection Failed ❌");
            e.printStackTrace();
        }
        return connection;
    }
}
