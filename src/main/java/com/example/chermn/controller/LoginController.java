package com.example.chermn.controller;

import java.io.IOException;

import com.example.chermn.OnBoarding;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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

        Users user = userDAO.login(inputUser, inputPass);

        if (user != null) {
            System.out.println("Login Success! Welcome, " + user.getUserName());

            FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
            Scene scene = new Scene(loader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);

            MainController controller = loader.getController();
            controller.setUser(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } else {
            System.out.println("Username or Password is incorrect!");
        }
    }
}