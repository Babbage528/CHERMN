package com.example.chermn.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class UpdateUserDetailsController extends ProfileController {


// the profile will display the students information and allows them to update

        @FXML
        private StackPane contentPane;
        @FXML
        private TextField firstNameField;
        @FXML
        private TextField lastNameField;
        @FXML
        private Label usernameLabel;
        @FXML
        private TextField emailField;
        @FXML
        private TextField schoolField;

        @FXML
        private Button updateButton;

    // defining variable for current logged-in user

    // private Users currentUser;

    @Override
    public void initialize() {
        super.initialize();

        // pulls the current user's stats/levels for the fields
        fillUserDetails();
    }





//    // Set the text fields with the current users information when the UI loads
        private void fillUserDetails() {

            firstNameField.setText(user.getFirstName());
            lastNameField.setText(user.getLastName());
            usernameLabel.setText(user.getUserName());
            // there is no email field for students as of yet
            // emailField.setText(currentUser.getEmail());
            schoolField.setText(user.getSchoolName());

        }


        @FXML
        private void updateButtonClick(ActionEvent actionEvent) throws IOException {

            // collect the data written in the text fields
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String schoolName = schoolField.getText().trim();


            // pass it through to update method
            updateUserDetails(firstName, lastName, schoolName);

            // updates the user in the database
            userDAO.updateUser(user);

            // update the text fields with current data stored in the database
            fillUserDetails();

        }

        private void updateUserDetails(String firstName, String lastName, String schoolName) {

            if (firstName != null && !firstName.equals(user.getFirstName())) {
                user.setFirstName(firstName);
            }

            if (lastName != null && !lastName.equals(user.getLastName())) {
                user.setLastName(lastName);
            }

            if(schoolName != null && !schoolName.equals(user.getSchoolName())) {
                user.setSchoolName(schoolName);
            }
        }

    }

