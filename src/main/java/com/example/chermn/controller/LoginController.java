package com.example.chermn.controller;

import java.io.IOException;

import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleSignUpClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("role-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleSignIn(ActionEvent event) throws IOException {
        String inputUser = usernameField.getText();
        String inputPass = passwordField.getText();

        if (inputUser.isEmpty() || inputPass.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Error", "Username and password cannot be empty!");
            return;
        }

        try {
            Users user = userDAO.login(inputUser, inputPass);

            if (user != null) {
                System.out.println("Login Success! Welcome, " + user.getUserName());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Session.setCurrentUser(user);

                if (user instanceof Student)
                {
                    // passes through the user into the homepage controller for students
                    FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
                    stage.setScene(new Scene(loader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT));

                }
                else
                {
                    // if user is teacher or parent, passes through the user to homepage controller for non-students
                    /// /////////////////////////////////////////////// FOR MOMENT JUST SET TO HOMEPAGE AS YET TO CREATE SEPARATE ONE
                    FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
                    stage.setScene(new Scene(loader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT));


                }


            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Username or Password is incorrect!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Problem with DB connection");
        }
    }
    //For the pop-up alert message
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}