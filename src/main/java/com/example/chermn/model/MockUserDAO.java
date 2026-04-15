package com.example.chermn.model;

import java.util.ArrayList;
import java.util.List;

public class MockUserDAO implements IUserDAO {
    private static final ArrayList<Users> users = new ArrayList<>();
    private static int autoIncrementedId = 1;

    public MockUserDAO() {
        if (users.isEmpty()) {
            addUser(new Teacher(autoIncrementedId, "fred", "Fred", "Farmer", "fred123", "Fred Farm"));
            addUser(new Student(autoIncrementedId, "abc", "abc", "def", "abc123", "QUT", 0, 0, 0));
        }
    }

    @Override
    public void addUser(Users user) {
        user.setId(autoIncrementedId);
        autoIncrementedId++;
        users.add(user);
    }

    @Override
    public void updateUser(Users user) {

    }

    @Override
    public void deleteUser(Users user) {

    }

    @Override
    public Users getUserByUsername(String username) {
        for (Users u : users) {
            if (u.getUserName().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<Users> getAllUsers() {
        return new ArrayList<>(users);
    }

    public Users login(String username, String password) {
        Users u = getUserByUsername(username);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
}