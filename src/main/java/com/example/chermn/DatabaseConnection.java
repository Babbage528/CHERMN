//package com.example.chermn;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DatabaseConnection {
//    private static Connection instance = null;
//
//    private DatabaseConnection() {
//        /// NEED TO HAVE THE DATABASE IN THE ROOT DIRECTORY - CHANGE REFERENCE NAME
//        String url = "jdbc:sqlite:database.db";
//        try {
//            instance = DriverManager.getConnection(url);
//        } catch (SQLException sqlEx) {
//            System.err.println(sqlEx);
//        }
//    }
//
//    public static Connection getInstance() {
//        if (instance == null) {
//            new DatabaseConnection();
//        }
//        return instance;
//    }
//}
//
