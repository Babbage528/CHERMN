package com.example.chermn.controller;

import java.io.IOException;

import com.example.chermn.AlertHelper;
import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;
import com.example.chermn.model.Teacher;

import com.example.chermn.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for the Teacher registration screen.
 * Handles the logic for creating teacher accounts, including input validation,
 * email verification, and database synchronization.
 */
public class TeacherRegisterController extends BaseController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField schoolNameField;
    @FXML private TextField unameField;
    @FXML private PasswordField passwordField;

    private UserDAO userDAO = new UserDAO();

    /**
     * Processes the teacher registration form submission.
     * Validates field completion, password length, and email format.
     * Checks for existing emails in the database before creating the new account
     * and navigating to the teacher home screen.
     *
     * @param event The action event triggered by the submit button
     * @throws IOException If the target FXML file cannot be loaded
     */
    @FXML
    private void handleRegisterSubmit(ActionEvent event) throws IOException {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String school = schoolNameField.getText().trim();
        String email = unameField.getText().trim().toLowerCase();
        String password = passwordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            AlertHelper.showError("Form Error!", "Please fill in all the field");
            return;
        }

        if (password.length() < 5) {
            AlertHelper.showWarning("Password is weak", "Password has to be 5 character min!");
            return;
        }
        if (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            AlertHelper.showWarning("Invalid Email", "Please enter a valid email address (e.g., name@example.com)");
            return;
        }
        if (userDAO.isEmailTaken(email)) {
            AlertHelper.showError("Registration Error",
                    "This email is already registered. Please use another email or login.");
            return;
        }
        try {
            Teacher t = new Teacher(0, email, firstName, lastName, password, school);
            userDAO.createUser(t);

            // fetch the actual saved user from DB (with real ID)
            Users savedUser = userDAO.getUserByUsername(email);

            // store in session
            Session.setCurrentUser(savedUser);
            System.out.println("Role: TEACHER");
            System.out.println("Registration successful for username: " + email + " FirstName: " + firstName + " LastName: " + lastName + " school: " + school);
            AlertHelper.showSuccess("Registration success", "Account " + email + " successfully registered!");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("teacher-parent-homescreen.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.showError("Database Error", "There is problem when saving to the DB.");
        }
    }

    /**
     * Handles the back button action to return to the previous role selection screen.
     *
     * @param event The action event triggered by the back button
     */
    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("role-selection.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}