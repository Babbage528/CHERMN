package com.example.chermn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.chermn.dao.IUserDAO;
import com.example.chermn.model.Users;

/**
 * Unit tests for {@link MockUserDAO}, verifying that the in‑memory
 * mock implementation behaves consistently with expected DAO behaviour.
 *
 * <p>These tests ensure that user creation, retrieval, authentication,
 * deletion, and list operations work correctly without requiring a
 * real database connection.
 */
public class MockUserDaoTest {

    /** The DAO instance used for each test, reset before every run. */
    private IUserDAO dao;

    /**
     * Creates a fresh {@link MockUserDAO} before each test to ensure
     * isolation and prevent state leakage between test cases.
     */
    @BeforeEach
    void setUp() {
        dao = new MockUserDAO();
    }

    /**
     * Verifies that a newly created user is stored and can be retrieved
     * by username.
     */
    @Test
    void testCreateUser() {
        Users user = new Users(0, "nik", "N", "S", "pass123", "QUT");
        dao.createUser(user);
        Users found = dao.getUserByUsername("nik");
        assertNotNull(found);
    }

    /**
     * Ensures that a user with valid credentials can successfully log in.
     */
    @Test
    void testLoginSuccess() {
        Users user = new Users(0, "nik", "N", "S", "pass123", "QUT");
        dao.createUser(user);
        Users loggedIn = dao.login("nik", "pass123");
        assertNotNull(loggedIn);
    }

    /**
     * Ensures that login fails when the password does not match.
     */
    @Test
    void testLoginFail() {
        Users user = new Users(0, "nik", "N", "S", "pass123", "QUT");
        dao.createUser(user);
        Users loggedIn = dao.login("nik", "wrong");
        assertNull(loggedIn);
    }

    /**
     * Tests the update behaviour of the DAO.
     *
     * <p>Although {@link MockUserDAO#updateUser(Users)} is a no‑op,
     * this test verifies that the updated user object itself reflects
     * the new password and that authentication behaves accordingly.
     */
    @Test
    void testUpdateUser() {
        Users user = new Users(0, "nik", "N", "S", "pass123", "QUT");
        dao.createUser(user);

        user.setPassword("newpass");
        dao.updateUser(user);

        // Old password should fail
        assertNull(dao.login("nik", "pass123"));

        // New password should succeed
        assertNotNull(dao.login("nik", "newpass"));
    }

    /**
     * Verifies that a user can be deleted and is no longer retrievable.
     */
    @Test
    void testDeleteUser() {
        Users user = new Users(0, "nik", "N", "S", "pass123", "QUT");
        dao.createUser(user);
        dao.deleteUser(user);

        Users deleted = dao.getUserByUsername("nik");
        assertNull(deleted);
    }

    /**
     * Ensures that requesting a non‑existent user returns {@code null}.
     */
    @Test
    void testGetUserNotFound() {
        Users user = dao.getUserByUsername("does_not_exist");
        assertNull(user);
    }

    /**
     * Verifies that {@link MockUserDAO#getAllUsers()} returns all
     * users that have been created.
     */
    @Test
    void testGetAllUsers() {
        dao.createUser(new Users(0, "a", "A", "A", "12345", "QUT"));
        dao.createUser(new Users(0, "b", "B", "B", "12345", "QUT"));
        assertEquals(2, dao.getAllUsers().size());
    }
}