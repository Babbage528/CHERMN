package com.example.chermn.controller;

import com.example.chermn.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for the screen displaying the user's level progress.
 * Handles displaying the current user's level progress on the screen.
 */
public class ViewLevelProgressController extends ProfileController {
    @FXML
    private Label animalLevel;
    @FXML
    private Label vehicleLevel;
    @FXML
    private Label natureLevel;

    // the current user is a student for this screen
    private Student currentUser;


    /**
     * Initialises the controller before the screen is displayed.
     * Checks whether the current user is a Student, and assigns it
     * to the currentUser.
     * Displays the student's level progress on the screen.
     */
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


    /**
     * Pulls the current student's category levels progress from the database,
     * and sets the corresponding text fields.
     */
    private void fillUserLevels() {
        // setting the labels to the corresponding levels relating to the current user
        animalLevel.setText(String.valueOf(currentUser.getAnimalLevel()));
        natureLevel.setText(String.valueOf(currentUser.getNatureLevel()));
        vehicleLevel.setText(String.valueOf(currentUser.getVehicleLevel()));
    }


}
