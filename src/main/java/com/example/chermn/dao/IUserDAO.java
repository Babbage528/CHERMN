package com.example.chermn.dao;
import java.util.List;

import com.example.chermn.model.Users;

/**
 * interface for UserDAO operations
 * defines all CRUD and login methods for users.
 */
public interface IUserDAO {

    /**
     * creates a new user.
     * @param user is the user to create
     */
    public void createUser(Users user);

    /**
     * Updates an existing user
     * @param user is the updated user object
     */
    public void updateUser(Users user);

    /**
     * deletes a user.
     * @param user is the user to delete
     */
    public void deleteUser(Users user);

    /**
     * Finds a user by username
     * @param username is the username to search for
     * @return matching user or null
     */
    Users getUserByUsername(String username);

    /**
     * returns all users from database
     * @return a list of users
     */
    List<Users> getAllUsers();

    /**
     * attempts login using username and password
     * @param username username input
     * @param password password input
     * @return matching user or returns null
     */
    Users login(String username, String password);
}