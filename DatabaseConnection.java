// package com.keyin.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class provides a connection to the database.
 */
public class DatabaseConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/HealthMonitoring";
    private static final String user = "nat";
    private static final String password = "supersecretconfidentialpassword";

    /**
     * Establishes a connection to the database.
     * 
     * @return A Connection object representing the database connection.
     */
    public static Connection getCon(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");     // For Postgres
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException  e) {
            e.printStackTrace();
        }
        return connection;
    }


}
