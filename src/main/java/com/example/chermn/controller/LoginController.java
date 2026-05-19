package com.example.chermn.controller;

import java.io.IOException;
import java.util.Optional;

import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;
import com.example.chermn.AlertHelper;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * Controller class for managing the login process.
 * Handles user input, authentication logic, session-based navigation,
 * and password recovery.
 */
public class LoginController extends BaseController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    private UserDAO userDAO = new UserDAO();

    /**
     * Navigates the user to the role selection screen to begin the sign-up process.
     *
     * @param event The action event triggered by the sign-up link.
     * @throws IOException If the FXML file for the role selection screen is missing.
     */
    @FXML
    private void handleSignUpClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("role-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Authenticates the user credentials against the database.
     * Upon successful login, sets the global session and redirects to the appropriate dashboard
     * based on the user's role (Student or Teacher/Parent).
     *
     * @param event The action event triggered by the sign-in button.
     * @throws IOException If the destination homepage FXML files cannot be loaded.
     */
    @FXML
    private void handleSignIn(ActionEvent event) throws IOException {
        String inputUser = usernameField.getText().trim().toLowerCase();
        String inputPass = passwordField.getText();

        if (inputUser.isEmpty() || inputPass.isEmpty()) {
            AlertHelper.showWarning("Error", "Username and password cannot be empty!");
            return;
        }

        try {
            Users user = userDAO.login(inputUser, inputPass);

            if (user != null) {
                System.out.println("Login Success! Welcome, " + user.getUserName());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Session.setCurrentUser(user);

                if (user instanceof Student) {
                    FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
                    stage.setScene(new Scene(loader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT));
                } else {
                    FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource("teacher-parent-homescreen.fxml"));
                    stage.setScene(new Scene(loader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT));
                }
            } else {
                AlertHelper.showError("Login Failed", "Username or Password is incorrect!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.showError("Database Error", "Problem with DB connection");
        }
    }

    @FXML
    private void handleForgotPassword(ActionEvent event) {
        String identifier = usernameField.getText().trim();

        if (identifier.isEmpty()) {
            AlertHelper.showWarning("Required", "Please enter your username in the login field first!");
            return;
        }

        if (userDAO.isEmailTaken(identifier)) {
            String validatedPassword = AlertHelper.showResetPasswordDialog("Reset Your Password");

            if (validatedPassword != null) {
                if (userDAO.resetPassword(identifier, validatedPassword)) {
                    AlertHelper.showSuccess("Success", "Password updated! You can now login.");
                    passwordField.clear();
                } else {
                    AlertHelper.showError("Error", "Failed to update database.");
                }
            }
        } else {
            AlertHelper.showError("Not Found", "Account not found.");
        }
    }

    /**
     * Public constructor of login controller class.
     */
    public LoginController() {
    }
}