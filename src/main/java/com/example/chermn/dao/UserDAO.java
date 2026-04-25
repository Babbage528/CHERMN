package com.example.chermn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.chermn.DatabaseConnection;
import com.example.chermn.model.Student;
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


    public void createStudent(Student s) {
        String sqlUser = "INSERT INTO USER (username, password_hash, email, first_name, last_name, school_name) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect()) {
            PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            stmtUser.setString(1, s.getUserName());
            stmtUser.setString(2, s.getPassword());
            stmtUser.setString(3, s.getUserName());
            stmtUser.setString(4, s.getFirstName());
            stmtUser.setString(5, s.getLastName());
            stmtUser.setString(6, s.getSchoolName());
            stmtUser.executeUpdate();
            ResultSet rs = stmtUser.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                String sqlProgress = "INSERT INTO USER_PROGRESS (user_id, category_id, level_id, current_growth_stage) VALUES (?, ?, 1, 0)";
                PreparedStatement stmtProg = conn.prepareStatement(sqlProgress);

                for (int catId = 1; catId <= 3; catId++) {
                    stmtProg.setInt(1, newId);
                    stmtProg.setInt(2, catId);
                    stmtProg.executeUpdate();
                }
            }
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

                int userId = rs.getInt("user_id");

                String role = rs.getString("role");

                // checking to see whether user is student
                if ("student".equals(role))
                {
                    // default values
                    int animal = 1;
                    int vehicle = 1;
                    int nature = 1;

                    // SECOND QUERY: fetch progress
                    String progressSql = "SELECT category_id, level_id FROM USER_PROGRESS WHERE user_id = ?";

                    try (PreparedStatement ps = conn.prepareStatement(progressSql)) {
                        ps.setInt(1, userId);
                        ResultSet pr = ps.executeQuery();

                        while (pr.next()) {
                            int category = pr.getInt("category_id");
                            int level = pr.getInt("level_id");

                            // switching based off the category stored in the database
                            switch (category) {
                                case 1:
                                    // assigning the level that the user is currently on in animal section
                                    animal = level;
                                case 2:
                                    // assigning the level that the user is currently on in vehicle section
                                    vehicle = level;
                                case 3:
                                    // asssinging the level that the user is currently on in nature level
                                    nature = level;
                            }
                        }
                    }

                    return new Student(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            "", //only storing username and password for now
                            "", //same as above
                            rs.getString("password_hash"),
                            "", //same as above
                            vehicle,
                            animal,
                            nature
                    );
                }

                // else if parent or teacher
                else {
                    return new Users(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            "", //only storing username and password for now
                            "", //same as above
                            rs.getString("password_hash"),
                            "" //same as above
                    );
                }
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

            // checks to see whether the user is a student
            if (user instanceof Student) {
                return new Student(
                        user.getId(),
                        user.getUserName(),
                        user.getPassword(),
                        user.getAnimalLevel(),
                        user.getVehicleLevel(),
                        user.getNatureLevel()
                );
            }

            // else if teacher or parent - might have to change when creating homepage for parent/teacher view
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