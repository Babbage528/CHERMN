package com.example.chermn.controller;

import com.example.chermn.AlertHelper;
import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Parent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Parent registration screen.
 * Handles user input validation, account creation, and linking parents to existing students.
 */
public class ParentRegisterController extends BaseController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField unameField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> relationshipComboBox;
    @FXML private TextField studentNameField;
    @FXML private TextField schoolNameField;

    private UserDAO userDAO = new UserDAO();

    /**
     * Processes the registration form submission.
     * Performs validation on student details, email format, and password strength
     * before creating a new Parent account in the database.
     *
     * @param event The action event triggered by clicking the register button
     */
    @FXML
    private void handleRegisterSubmit(ActionEvent event) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = unameField.getText().trim().toLowerCase();
        String password = passwordField.getText();
        String relationship = relationshipComboBox.getValue();
        String studentName = studentNameField.getText().trim();
        String school = schoolNameField.getText().trim();


        if (!studentName.contains(" ")) {
            AlertHelper.showError(
                    "Invalid Student Name",
                    "Please enter full name (First Last)");
            return;
        }

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                password.isEmpty() || studentName.isEmpty() || school.isEmpty()) {
            AlertHelper.showWarning("Form Error!", "Please fill in all the fields");
            return;
        }

        if (password.length() < 5) {
            AlertHelper.showWarning( "Password is weak", "Password has to be 5 characters minimum!");
            return;
        }
        if (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            AlertHelper.showWarning( "Invalid Email", "Please enter a valid email address (e.g., name@example.com)");
            return;
        }
        if (userDAO.isEmailTaken(email)) {
            AlertHelper.showError( "Registration Error",
                    "This email is already registered. Please use another email or login.");
            return;
        }
        try {
            Integer foundStudentId = userDAO.findStudentIdByName(studentName, school);

            if (foundStudentId == null) {
                AlertHelper.showError(
                        "Student Not Found",
                        "We couldn't find a student named '" + studentName +
                                "' in " + school + ". Please check spelling or school name.");
                return;
            }
            Parent p = new Parent(0, email, firstName, lastName, password, school, relationship, studentName);
            p.setStudentId(foundStudentId);
            userDAO.createParent(p);

            Session.setCurrentUser(p);
            AlertHelper.showSuccess( "Registration Success", "Account " + email + " successfully registered!");
            switchScene(event, "teacher-parent-homescreen.fxml");
        } catch (IllegalArgumentException e) {
            AlertHelper.showError( "Invalid Input", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.showError("Database Error", e.getMessage());
        }
    }

    /**
     * Handles the back button action to return to the role selection screen.
     *
     * @param event The action event triggered by clicking the back button
     */
    @FXML
    private void handleBack(ActionEvent event) {
        try {
            switchScene(event, "role-selection.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility method to switch the current stage to a different FXML scene.
     *
     * @param event The action event used to identify the current window
     * @param fxmlFile The name of the FXML file to load
     * @throws IOException If the specified FXML file cannot be found or loaded
     */
    private void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource(fxmlFile));
        javafx.scene.Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}