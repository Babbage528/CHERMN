package com.example.chermn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.chermn.dao.IUserDAO;
import com.example.chermn.model.Users;

public class MockUserDaoTest {
    private IUserDAO dao;

    @BeforeEach
    void setUp() {
        dao = new MockUserDAO();
    }

    //test creating user
    @Test
    void testCreateUser() {
        Users user = new Users(0, "nik", "N", "S", "pass123", "QUT");
        dao.createUser(user);
        Users found = dao.getUserByUsername("nik");
        assertNotNull(found);
    }

    //test if the created user can login
    @Test
    void testLoginSuccess() {
        Users user = new Users(0, "nik", "N", "S", "pass123", "QUT");
        dao.createUser(user);
        Users loggedIn = dao.login("nik", "pass123");
        assertNotNull(loggedIn);
    }

    //test if wrong password will results in null
    @Test
    void testLoginFail() {
        Users user = new Users(0, "nik", "N", "S", "pass123", "QUT");
        dao.createUser(user);
        Users loggedIn = dao.login("nik", "wrong");
        assertNull(loggedIn);
    }

    //test update user
    @Test
    void testUpdateUser() {
        Users user = new Users(0, "nik", "N", "S", "pass123", "QUT");
        dao.createUser(user);
        user.setPassword("newpass");
        dao.updateUser(user);

        // old password should fail
        assertNull(dao.login("nik", "pass123"));
        // new password should succeed
        assertNotNull(dao.login("nik", "newpass"));

        //Users updated = dao.getUserByUsername("nik");
        //assertEquals("newpass", updated.getPassword());
    }

    //test delete user
    @Test
    void testDeleteUser() {
        Users user = new Users(0, "nik", "N", "S", "pass123", "QUT");
        dao.createUser(user);
        dao.deleteUser(user);
        Users deleted = dao.getUserByUsername("nik");
        assertNull(deleted);
    }

    //test if user not found
    @Test
    void testGetUserNotFound() {
        Users user = dao.getUserByUsername("does_not_exist");
        assertNull(user);
    }

    //test get all users method will return all created users
    @Test
    void testGetAllUsers() {
        dao.createUser(new Users(0, "a", "A", "A", "12345", "QUT"));
        dao.createUser(new Users(0, "b", "B", "B", "12345", "QUT"));
        assertEquals(2, dao.getAllUsers().size());
    }
}