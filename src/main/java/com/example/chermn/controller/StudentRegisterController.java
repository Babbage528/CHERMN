package com.example.chermn.controller;

import java.io.IOException;

import com.example.chermn.AlertHelper;
import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;

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
 * Controller class for the Student registration screen.
 * Manages the creation of new student accounts, including input validation
 * and database persistence.
 */
public class StudentRegisterController extends BaseController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField schoolNameField;
    @FXML private TextField unameField;
    @FXML private PasswordField passwordField;

    private UserDAO userDAO = new UserDAO();

    /**
     * Processes the student registration form.
     * Validates that all fields are filled, checks password length, verifies email format,
     * and ensures the email is not already taken before saving the student to the database.
     *
     * @param event The action event triggered by the submit button
     * @throws IOException If the homepage FXML file cannot be loaded
     */
    @FXML
    private void handleRegisterSubmit(ActionEvent event) throws IOException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String school = schoolNameField.getText();
        String email = unameField.getText().trim().toLowerCase();
        String password = passwordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            AlertHelper.showWarning("Incomplete Form", "Please fill all fields!");
            return;
        }

        if (password.length() < 5) {
            AlertHelper.showWarning("Password is weak", "Password has to be 5 character min!");
            return;
        }
        if (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            AlertHelper.showWarning( "Invalid Email", "Please enter a valid email address (e.g., name@example.com)");
            return;
        }
        if (userDAO.isEmailTaken(email)) {
            AlertHelper.showError("Registration Error",
                    "This email is already registered. Please use another email or login.");
            return;
        }
        try {

            Student student = new Student(0, email, firstName, lastName, password, school, 1, 1, 1);
            userDAO.createStudent(student);

            // store in session
            Session.setCurrentUser(student);

            AlertHelper.showSuccess("Registration success", "Account " + email + " successfully registered!");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
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
     * Navigates the user back to the role selection screen.
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

    /**
     * Public constructor of student register controller class.
     */
    public StudentRegisterController() {
    }
}