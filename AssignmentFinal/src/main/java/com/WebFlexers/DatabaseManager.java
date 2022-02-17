package com.WebFlexers;

import com.WebFlexers.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class DatabaseManager {
    // An attribute that keeps track of open connections
    private static int countOpenConnections = 0;
    private Connection connection;

    /**
     * Creates a new instance of DatabaseManager and connects to the database
     */
    public DatabaseManager() {
        createConnection();
        countOpenConnections++;
    }

    // Getters
    public Connection getConnection() { return connection; }
    public static int getOpenConnectionsCount() { return countOpenConnections; }

    // Methods
    public void createConnection() {
        try {
            String url = "jdbc:postgresql://ec2-63-33-239-176.eu-west-1.compute.amazonaws.com:5432/da2pcm0gjc5hrv";
            Properties props = new Properties();
            props.setProperty("user","egclbbfjnxflqx");
            props.setProperty("password","1595f9de3818f63fd7a8373098250bc31ff7803dd70dbfe9ba868a1e001655e6");

            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Closes the connection to the database
     */
    public void closeConnection() {
        try {
            connection.close();
            countOpenConnections--;
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to terminate the connection to the database");
        }
    }
}
