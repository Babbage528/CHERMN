package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the onboarding screen.
 * <p>
 * Handles navigation to login, registration, and role‑selection screens
 * when the user first opens the application.
 */
public class OnBoardingController extends BaseController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    /**
     * Default constructor for OnBoardingController.
     * Required for JavaFX controller instantiation.
     */
    public OnBoardingController() {}

    /**
     * Handles the click event for the login button.
     * Transitions the application scene to the login screen.
     *
     * @throws IOException if the login-screen.fxml file cannot be loaded
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
     * @throws IOException if the role-selection.fxml file cannot be loaded
     */
    @FXML
    protected void signUpButtonClick() throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("role-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }
}