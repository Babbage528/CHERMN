package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import com.example.chermn.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private void handleSignUpClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("role-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);

        stage.setScene(scene);
        stage.show();
    }


    // make login button load homepage
    @FXML
    private void handleSignInClick(ActionEvent event) throws IOException {
        // TO DO: validation
        // for now im just going straight to homepage
        SceneLoader.swapScene(event, "homepage.fxml", "Homepage");
    }
}