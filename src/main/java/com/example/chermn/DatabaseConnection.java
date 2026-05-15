package com.example.chermn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * The driver class for connection with the persistent database
 * Establishes the connection with the sqlite trivia database.
 */
public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:trivia.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}