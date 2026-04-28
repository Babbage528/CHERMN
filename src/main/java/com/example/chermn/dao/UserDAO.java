package com.example.chermn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.chermn.DatabaseConnection;
import com.example.chermn.Session;
import com.example.chermn.model.Parent;
import com.example.chermn.model.Student;
import com.example.chermn.model.Teacher;
import com.example.chermn.model.Users;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO implements IUserDAO {

    private String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //creates new user and stores in db
    public void createUser(Users user) {
        String sql = "INSERT INTO USER (username, password_hash, email) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            //atm we're using username as email
            stmt.setString(1, user.getUserName());
            stmt.setString(2, hash(user.getPassword()));
            stmt.setString(3, user.getUserName()); 

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createStudent(Student s) {
        String sqlUser = "INSERT INTO USER (username, password_hash, email, first_name, last_name, school_name, role) VALUES (?, ?, ?, ?, ?, ?, 'student')";

        try (Connection conn = DatabaseConnection.connect()) {
            PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            stmtUser.setString(1, s.getUserName());
            stmtUser.setString(2, hash(s.getPassword()));
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

    public void createTeacher(Teacher t) {
        String sqlUser = "INSERT INTO USER (username, password_hash, email, first_name, last_name, school_name, role) VALUES (?, ?, ?, ?, ?, ?, 'teacher')";

        try (Connection conn = DatabaseConnection.connect()) {
            PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            stmtUser.setString(1, t.getUserName());
            stmtUser.setString(2, hash(t.getPassword()));
            stmtUser.setString(3, t.getUserName());
            stmtUser.setString(4, t.getFirstName());
            stmtUser.setString(5, t.getLastName());
            stmtUser.setString(6, t.getSchoolName());
            stmtUser.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createParent(Parent p) {
        String sqlUser = "INSERT INTO USER (username, password_hash, email, first_name, last_name, school_name, role) " +
                "VALUES (?, ?, ?, ?, ?, ?, 'parent')";
        String sqlDetail = "INSERT INTO PARENT_DETAILS (parent_user_id, relationship, student_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect()) {
            conn.setAutoCommit(false);
            try {
                PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
                stmtUser.setString(1, p.getUserName());
                stmtUser.setString(2, hash(p.getPassword()));
                stmtUser.setString(3, p.getUserName());
                stmtUser.setString(4, p.getFirstName());
                stmtUser.setString(5, p.getLastName());
                stmtUser.setString(6, p.getSchoolName());
                stmtUser.executeUpdate();

                ResultSet rs = stmtUser.getGeneratedKeys();
                if (rs.next()) {
                    int parentId = rs.getInt(1);
                    p.setId(parentId);

                    PreparedStatement stmtDetail = conn.prepareStatement(sqlDetail);
                    stmtDetail.setInt(1, parentId);
                    stmtDetail.setString(2, p.getRelationship());
                    stmtDetail.setInt(3, p.getStudentId());
                    stmtDetail.executeUpdate();
                }

                conn.commit();
                System.out.println("Parent and Details created successfully!");

            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (Exception e) {
            System.err.println("Error while creating parent account: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int findStudentIdByName(String fullName, String schoolName) {
        String sql = "SELECT user_id FROM USER WHERE (first_name || ' ' || last_name) = ? " +
                "AND school_name = ? AND role = 'student'";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, fullName);
            pstmt.setString(2, schoolName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean isEmailTaken(String email) {
        String sql = "SELECT COUNT(*) FROM USER WHERE LOWER(username) = LOWER(?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
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

                    // second SQL query to fetch the students progress
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
                                    break;
                                case 2:
                                    // assigning the level that the user is currently on in vehicle section
                                    vehicle = level;
                                    break;
                                case 3:
                                    // assigning the level that the user is currently on in nature level
                                    nature = level;
                                    break;
                            }
                        }
                    }

                    // returns the current user as a student with all information
                    return new Student(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("password_hash"),
                            rs.getString("school_name"),
                            vehicle,
                            animal,
                            nature
                    );
                }

                // else if parent or teacher - might need to edit later particularly if have different roles
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
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
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

    //updates user info, based on what they wish - updated so that student can update first, last, password and school
    public void updateUser(Users user) {
        String sql = "UPDATE USER SET first_name = ?, last_name = ?, school_name = ?, username = ?, password_hash = ? WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getSchoolName());
            stmt.setString(4, user.getUserName());
            stmt.setString(5, hash(user.getPassword()));
            stmt.setInt(6, user.getid());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}