package com.example.chermn.controller;

import java.io.IOException;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for managing the login process.
 * Handles user input, authentication logic, and session-based navigation.
 */
public class LoginController extends BaseController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    private UserDAO userDAO = new UserDAO();

    /**
     * Navigates the user to the role selection screen to begin the sign-up process.
     *
     * @param event The action event triggered by the sign-up button
     * @throws IOException If the FXML file for the role selection screen is missing
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
     * @param event The action event triggered by the sign-in button
     * @throws IOException If the destination homepage FXML files cannot be loaded
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
                    // passes through the user into the homepage controller for students
                    FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
                    stage.setScene(new Scene(loader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT));

                } else {
                    // if user is teacher or parent, passes through the user to homepage controller for non-students
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
}