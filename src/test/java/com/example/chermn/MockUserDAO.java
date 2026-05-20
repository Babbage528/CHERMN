package com.example.chermn;

import java.util.List;
import java.util.ArrayList;

import com.example.chermn.dao.IUserDAO;
import com.example.chermn.model.Users;

/**
 * A simple in‑memory mock implementation of {@link IUserDAO} used for testing
 * and development without requiring a real database connection.
 *
 * <p>This mock stores {@link Users} objects in a local {@link ArrayList} and
 * simulates basic CRUD behaviour. It is primarily intended for unit tests and
 * integration tests where database access is unnecessary or undesirable.
 */
public class MockUserDAO implements IUserDAO {

    /** Internal list representing the mock user table. */
    private List<Users> users = new ArrayList<>();

    /**
     * Adds a new user to the in‑memory list.
     *
     * @param user the user to add
     */
    @Override
    public void createUser(Users user) {
        users.add(user);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username the username to search for
     * @return the matching {@link Users} object, or {@code null} if not found
     */
    @Override
    public Users getUserByUsername(String username) {
        for (Users u : users) {
            if (u.getUserName().equals(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Attempts to authenticate a user by matching both username and password.
     *
     * @param username the username provided
     * @param password the password provided
     * @return the authenticated {@link Users} object, or {@code null} if credentials do not match
     */
    @Override
    public Users login(String username, String password) {
        for (Users u : users) {
            if (u.getUserName().equals(username) &&
                    u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Returns all users stored in the mock DAO.
     *
     * @return a list of all {@link Users}
     */
    @Override
    public List<Users> getAllUsers() {
        return users;
    }

    /**
     * Mock update method.
     *
     * <p>This implementation does nothing because update behaviour is not
     * required for the current testing scope. Tests that require update
     * behaviour should be implemented in integration tests or extended mocks.
     *
     * @param user the user to update
     */
    @Override
    public void updateUser(Users user) {
        // No-op for this mock implementation
    }

    /**
     * Removes a user from the in‑memory list.
     *
     * @param user the user to remove
     */
    @Override
    public void deleteUser(Users user) {
        users.remove(user);
    }
}