package com.example.chermn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.chermn.DatabaseConnection;
import com.example.chermn.model.Users;

public class UserDAO implements IUserDAO {

    //creates new user and stores in db
    public void createUser(Users user) {
        String sql = "INSERT INTO USER (username, password_hash, email) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            //atm we're using username as email
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUserName()); 

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //finds user in db, based on username
    public Users getUserByUsername(String username) {
        String sql = "SELECT * FROM USER WHERE username = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Users(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        "", //only storing username and password for now
                        "", //same as above
                        rs.getString("password_hash"),
                        "" //same as above
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //returns user if login input is correct otherwise returns null
    public Users login(String username, String password) {
        Users user = getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    //returns alls users from db
    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();

        String sql = "SELECT * FROM USER";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(new Users(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        "",
                        "",
                        rs.getString("password_hash"),
                        ""
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    //deletes user
    public void deleteUser(Users user) {
        String sql = "DELETE FROM USER WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getid());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //updates user info, based on what they wish
    public void updateUser(Users user) {
        String sql = "UPDATE USER SET username = ?, password_hash = ? WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getid());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}