package com.example.chermn.controller;


import com.example.chermn.OnBoarding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class HomepageController {
    // defining the buttons used in the homepage screen
    @FXML
    private Button animalButton;

    @FXML
    private Button plantButton;

    @FXML
    private Button vecihleButton;

    @FXML
    private Button farmHouseButton;

    @FXML
    private Button settingsButton;


    // defining the associated actions associated with the above button variables

    @FXML
    protected void animalButtonClick() throws  IOException{
        Stage stage = (Stage) animalButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("animal-level-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    protected void plantButtonClick() throws  IOException{
        Stage stage = (Stage) plantButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("plant-level-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void vehicleButtonClick() throws IOException {
        Stage stage = (Stage) vecihleButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("vehicle-level-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void farmHouseButtonClick() throws IOException {
        Stage stage = (Stage) farmHouseButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("farmHouse-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void settingsButtonClick() throws IOException {
        Stage stage = (Stage) farmHouseButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("settings-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }
}
