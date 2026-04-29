package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Node;

import javax.swing.*;
import javafx.event.ActionEvent;
import java.io.IOException;

public abstract class BaseProfileController {

    protected abstract StackPane getContainer();

    public void loadView(String fxml) {
        try {
            Parent view = FXMLLoader.load(
                    BaseProfileController.class.getResource("/com/example/chermn/" + fxml)
            );
            getContainer().getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void changePasswordButtonClick(ActionEvent event) {
        loadView("change-password.fxml");
    }

    @FXML
    protected void signOutButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("onboarding-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }



}
