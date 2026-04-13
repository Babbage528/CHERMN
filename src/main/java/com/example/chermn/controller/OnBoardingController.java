package com.example.chermn.controller;


import com.example.chermn.OnBoarding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OnBoardingController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

/*    @FXML
    protected void logInButtonClick() throws  IOException{
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("login-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }*/


    @FXML
    protected void logInButtonClick() throws IOException {
        // Get the current window (Stage) from the button that was clicked.
        Stage stage = (Stage) loginButton.getScene().getWindow();
        // Point an FXMLLoader to the FXML file you want to load.
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("login-screen.fxml"));
        // Load the FXML and create a new Scene with your app's standard width/height.
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        // Replace the current scene with the new one.
        stage.setScene(scene);
        // Show the updated window (required when changing scenes manually).
        stage.show();
    }


    @FXML
    protected void signUpButtonClick() throws  IOException{
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("role-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

}

