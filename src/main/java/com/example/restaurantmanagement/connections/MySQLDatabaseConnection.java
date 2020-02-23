package com.example.restaurantmanagement.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseConnection {
    private Connection connection;

    public static MySQLDatabaseConnection getInstance() {
        if(instance == null) {
            instance = new MySQLDatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private MySQLDatabaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant-management","root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static MySQLDatabaseConnection instance;
}
