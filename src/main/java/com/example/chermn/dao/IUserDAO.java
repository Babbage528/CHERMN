package com.example.chermn.dao;
import java.util.List;

import com.example.chermn.model.Users;

public interface IUserDAO {
    public void createUser(Users user);
    public void updateUser(Users user);
    public void deleteUser(Users user);
    Users getUserByUsername(String username);
    List<Users> getAllUsers();
    Users login(String username, String password);
}