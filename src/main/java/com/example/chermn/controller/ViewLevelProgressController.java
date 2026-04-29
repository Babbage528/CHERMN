package com.example.chermn.controller;

import com.example.chermn.model.Student;
import com.example.chermn.model.Users;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewLevelProgressController extends profileController {
    @FXML
    private Label animalLevel;
    @FXML
    private Label vehicleLevel;
    @FXML
    private Label natureLevel;

    private Student currentUser;

    @Override
    public void initialize() {
        super.initialize();
        // check whether the user is a student
        if (user instanceof Student student) {
            currentUser = student;
        }
        else {
            throw new IllegalArgumentException("Student profile requires a Student user");
        }

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
