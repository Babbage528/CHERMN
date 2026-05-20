package com.example.chermn;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Provides a connection to the application's persistent SQLite database.
 * <p>
 * This class centralizes database access by exposing a single static
 * method for establishing a connection to the trivia database.
 */
public class DatabaseConnection {

    /** JDBC URL for connecting to the local SQLite trivia database. */
    private static final String URL = "jdbc:sqlite:trivia.db";

    /**
     * Establishes and returns a connection to the SQLite database.
     *
     * @return a {@link Connection} object if successful, or {@code null} if the connection fails
     */
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}