package com.example.chermn;

import java.sql.Connection;
import java.sql.DriverManager;

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