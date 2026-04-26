package com.example.chermn.controller;

// the profile will display the users information and allows them to update

import com.example.chermn.OnBoarding;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.control.Button;

public class profileController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField schoolField;

    @FXML
    private Button updateButton;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Button levelProgressButton;
    @FXML
    private Button userProfileButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button signOutButton;


    // defining the associated actions associated with the above button variables
    @FXML
    protected void closeButtonClick() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void signOutButtonClick() throws IOException {
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("onboarding-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    private void userProfileButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) userProfileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("profile-screen.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private void levelProgressButtonClick(ActionEvent actionEvent) {

    }

    @FXML
    private void changePasswordButtonClick(ActionEvent actionEvent) {

    }

    @FXML
    private void updateButtonClick(ActionEvent actionEvent) {

    }




    // defining variable for current logged-in user
    private Student currentUser;

    // assign the user passed through the controller to assignedUser
    public void setCurrentUser(Users user) {
        // check whether the user is a student
        if (user instanceof Student student) {
            this.currentUser = student;
        }
        else {
            throw new IllegalArgumentException("Student profile requires a Student user");
        }
        // pulls the current user's stats/levels for the images
        fillUserDetails();
    }

    // Set the text fields with the current users information when the UI loads
    private void fillUserDetails() {

        firstNameField.setText(currentUser.getFirstName());
        lastNameField.setText(currentUser.getLastName());
        usernameField.setText(currentUser.getUserName());
        // there is no email field for students as of yet
        // emailField.setText(currentUser.getEmail());
        schoolField.setText(currentUser.getSchoolName());

    }
}