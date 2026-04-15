package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        System.out.println("User logging out...");

        FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource("login-screen.fxml"));
        Parent loginRoot = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(loginRoot, OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}