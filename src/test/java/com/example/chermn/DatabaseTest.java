package com.example.chermn;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseTest {
    //verify establishment of conn to db
    @Test
    public void testConnection() {
        Connection conn = DatabaseConnection.connect();
        assertNotNull(conn);
    }
}