package com.example.chermn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Users;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Integration tests for {@link UserDAO}, validating real database CRUD
 * operations and authentication behaviour.
 *
 * <p>Unlike mock‑based tests, these tests interact with the actual database
 * to ensure that SQL queries, hashing logic, and persistence behave correctly
 * in a production‑like environment.
 *
 * <p>Each test creates its own unique user (via timestamped usernames) to
 * avoid collisions and ensure isolation. Cleanup is performed where needed.
 */
public class UserDAOTest {

    /** The real DAO used for integration testing. */
    UserDAO userDAO = new UserDAO();

    /**
     * Verifies that a user can be created and retrieved from the database.
     */
    @Test
    void testCreateUser() {
        String username = "testuser_" + System.currentTimeMillis();
        Users user = new Users(0, username, "Test", "User", "12345", "QUT");

        userDAO.createUser(user);
        Users found = userDAO.getUserByUsername(username);

        assertNotNull(found);

        // cleanup
        userDAO.deleteUser(found);
    }

    /**
     * Ensures that login succeeds when valid credentials are provided.
     */
    @Test
    void testLoginSuccess() {
        String username = "loginuser_" + System.currentTimeMillis();
        Users user = new Users(0, username, "Test", "User", "password", "QUT");

        userDAO.createUser(user);
        Users loggedIn = userDAO.login(username, "password");

        assertNotNull(loggedIn);

        // cleanup
        userDAO.deleteUser(loggedIn);
    }

    /**
     * Ensures that login fails when the password is incorrect.
     */
    @Test
    void testLoginWrongPassword() {
        String username = "user_" + System.currentTimeMillis();
        Users user = new Users(0, username, "Test", "User", "correct", "QUT");

        userDAO.createUser(user);
        Users loggedIn = userDAO.login(username, "notcorrect");

        assertNull(loggedIn);

        // cleanup
        userDAO.deleteUser(userDAO.getUserByUsername(username));
    }

    /**
     * Verifies that updating a user's password results in correct
     * authentication behaviour.
     *
     * <p>Old credentials should fail, and new credentials should succeed.
     */
    @Test
    void testUpdateUser() {
        String username = "updateuser_" + System.currentTimeMillis();
        Users user = new Users(0, username, "Old", "Name", "12345", "QUT");

        userDAO.createUser(user);
        Users found = userDAO.getUserByUsername(username);

        found.setPassword("newpass");
        userDAO.updateUser(found);

        Users updated = userDAO.getUserByUsername(username);

        // Old password should fail
        assertNull(userDAO.login(username, "12345"));

        // New password should succeed
        assertNotNull(userDAO.login(username, "newpass"));

        // cleanup
        userDAO.deleteUser(updated);
    }

    /**
     * Ensures that login fails when both username and password are invalid.
     */
    @Test
    void testLoginFail() {
        Users loggedIn = userDAO.login("wronguser", "wrongpass");
        assertNull(loggedIn);
    }

    /**
     * Verifies that a user can be deleted and is no longer retrievable.
     */
    @Test
    void testDeleteUser() {
        String username = "deleteuser_" + System.currentTimeMillis();
        Users user = new Users(0, username, "Test", "User", "12345", "QUT");

        userDAO.createUser(user);
        Users found = userDAO.getUserByUsername(username);

        userDAO.deleteUser(found);

        Users deleted = userDAO.getUserByUsername(username);
        assertNull(deleted);
    }

    /**
     * Ensures that requesting a non‑existent user returns {@code null}.
     */
    @Test
    void testGetUserNotFound() {
        Users user = userDAO.getUserByUsername("does_not_exist_123");
        assertNull(user);
    }

    /**
     * Verifies that {@link UserDAO#getAllUsers()} returns a non‑null list.
     * <p>This does not assert size because the database may contain existing users.
     */
    @Test
    void testGetAllUsers() {
        assertNotNull(userDAO.getAllUsers());
    }
}