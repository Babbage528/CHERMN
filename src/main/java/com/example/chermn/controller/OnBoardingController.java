package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the onboarding screen.
 * Provides the initial entry points for users to navigate to either
 * the login or registration flows.
 */
public class OnBoardingController extends BaseController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    /**
     * Handles the click event for the login button.
     * Transitions the application scene to the login screen.
     *
     * @throws IOException If the login-screen.fxml file cannot be loaded
     */
    @FXML
    protected void logInButtonClick() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("login-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Handles the click event for the sign-up button.
     * Transitions the application scene to the role selection screen.
     *
     * @throws IOException If the role-selection.fxml file cannot be loaded
     */
    @FXML
    protected void signUpButtonClick() throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("role-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }
}