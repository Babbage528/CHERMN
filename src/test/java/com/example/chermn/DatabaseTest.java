package com.example.chermn;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for verifying database connectivity.
 *
 * <p>This test class ensures that the application's database layer is able
 * to successfully establish a connection using {@link DatabaseConnection#connect()}.
 * A non-null {@link Connection} indicates that the database driver is available
 * and the connection parameters are valid.
 */
public class DatabaseTest {
    /**
     * Verifies that a connection to the database can be successfully established.
     *
     * <p>The test passes if {@code DatabaseConnection.connect()} returns a non-null
     * {@link Connection} object. No queries are executed; this test only checks
     * that the connection is created without throwing an exception.
     */
    @Test
    public void testConnection() {
        Connection conn = DatabaseConnection.connect();
        assertNotNull(conn);
    }
}