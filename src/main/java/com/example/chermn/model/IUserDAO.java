package com.example.chermn.model;
import java.util.List;

public interface IUserDAO {
    public void addUser(Users user);
    public void updateUser(Users user);
    public void deleteUser(Users user);
    Users getUserByUsername(String username);
    List<Users> getAllUsers();
}