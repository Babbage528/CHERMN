package com.example.chermn;

import java.util.List;
import java.util.ArrayList;

import com.example.chermn.dao.IUserDAO;
import com.example.chermn.model.Users;

public class MockUserDAO implements IUserDAO {

    private List<Users> users = new ArrayList<>();

    @Override
    public void createUser(Users user) {
        users.add(user);
    }

    @Override
    public Users getUserByUsername(String username) {
        for (Users u : users) {
            if (u.getUserName().equals(username)) {
                return u;
            }
        }
        return null;
    }

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

    @Override
    public List<Users> getAllUsers() {
        return users;
    }

    @Override
    public void updateUser(Users user) {
        //basic mock. this is not needed for this checkpoint - also im testing this in the integration tests
    }

    @Override
    public void deleteUser(Users user) {
        users.remove(user);
    }
}