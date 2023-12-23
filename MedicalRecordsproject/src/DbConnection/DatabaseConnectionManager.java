/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DbConnection;

/**
 *
 * @author WINDOWS
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static final String DB_URL = "jdbc:mysql://localhost/medicalrecords";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection connection;


    private DatabaseConnectionManager() {

    }

    /**
     * Get a connection to the database.
     *
     * @return The database connection
     * @throws SQLException If a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        // Create a new connection if it does not exist or is closed
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}
