package com.example.chermn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Users;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAOTest {

    UserDAO userDAO = new UserDAO();

    //integration tests for UserDAO. below tests database CRUD operations 

    //creates a user and checks if it is saved in the database
    @Test
    void testCreateUser() {
        String username = "testuser_" + System.currentTimeMillis();
        Users user = new Users(0, username, "Test", "User", "12345", "QUT");
        userDAO.createUser(user);
        Users found = userDAO.getUserByUsername(username);
        assertNotNull(found);
        
        //clean
        userDAO.deleteUser(found);
    }

    //creates a user and checks if login works with correct credentials
    @Test
    void testLoginSuccess() {
        String username = "loginuser_" + System.currentTimeMillis();
        Users user = new Users(0, username, "Test", "User", "password", "QUT");
        userDAO.createUser(user);
        Users loggedIn = userDAO.login(username, "password");
        assertNotNull(loggedIn);

        //cleanup
        userDAO.deleteUser(loggedIn);
    }

    //tests wrong password results in not logged in (for excisting users)
    @Test
    void testLoginWrongPassword() {
        String username = "user_" + System.currentTimeMillis();
        Users user = new Users(0, username, "Test", "User", "correct", "QUT");
        userDAO.createUser(user);
        Users loggedIn = userDAO.login(username, "notcorrect");
        assertNull(loggedIn);

        //cleanup
        userDAO.deleteUser(userDAO.getUserByUsername(username));
    }

    //checks that login fails with wrong username or password
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

        //cleanup
        userDAO.deleteUser(updated);
    }

    //checks that login fails with wrong username or password
    @Test
    void testLoginFail() {
        Users loggedIn = userDAO.login("wronguser", "wrongpass");
        assertNull(loggedIn);
    }

    //creates a user, deletes it, and checks that it is removed
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

    //checks that a not existing user returns null if trying to get username
    @Test
    void testGetUserNotFound() {
        Users user = userDAO.getUserByUsername("does_not_exist_123");
        assertNull(user);
    }

    //checks that method returns a list 
    @Test
    void testGetAllUsers() {
        assertNotNull(userDAO.getAllUsers());
    }
}