package com.example.chermn.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewLevelProgressController extends profileController {
    @FXML
    private Label animalLevel;
    @FXML
    private Label vehicleLevel;
    @FXML
    private Label natureLevel;

    @Override
    public void initialize() {
        super.initialize();

        // pulls the current user's stats/levels for the images
        fillUserLevels();
    }

    // Set the text fields with the current users information when the UI loads
    private void fillUserLevels() {
        // setting the labels to the corresponding levels relating to the current user
        animalLevel.setText(String.valueOf(currentUser.getAnimalLevel()));
        natureLevel.setText(String.valueOf(currentUser.getNatureLevel()));
        vehicleLevel.setText(String.valueOf(currentUser.getVehicleLevel()));
    }


}
