package com.example.chermn.controller;

// the profile will display the users information and allows them to update

import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.GestureEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;

import javafx.scene.control.Button;

public class profileController {

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


    // defining variable for current logged-in user
    private Student currentUser;

    // initalizing variabels
    public void initialize() {
        Users user = Session.getCurrentUser();
        setCurrentUser(user);
    }



    // defining the associated actions associated with the above button variables
    @FXML
    protected void closeButtonClick() throws IOException {
        // clears the data entered into the text fields
        clearUserDetails();

        Stage stage = (Stage) closeButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
        Scene scene = new Scene(loader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
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




    // defining variable for current logged-in user
    // private Student currentUser;

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
        usernameLabel.setText(currentUser.getUserName());
        // there is no email field for students as of yet
        // emailField.setText(currentUser.getEmail());
        schoolField.setText(currentUser.getSchoolName());

    }

    // Clear the text fields with the current users inputted information
    private void clearUserDetails() {
        firstNameField.clear();
        lastNameField.clear();
        // no email field at the moment
        // emailField.clear();
        schoolField.clear();
    }



    // instantiate userDAO to access
    private UserDAO userDAO = new UserDAO();


    @FXML
    private void updateButtonClick(ActionEvent actionEvent) throws IOException {

        // collect the data written in the text fields
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String schoolName = schoolField.getText().trim();


        // pass it through to update method
        updateUserDetails(firstName, lastName, schoolName);

        // updates the user in the database
        userDAO.updateUser(currentUser);

        // update the text fields with current data stored in the database
        fillUserDetails();


        // brings the user back to the homepage
        closeButtonClick();


    }

    private void updateUserDetails(String firstName, String lastName, String schoolName) {

        if (firstName != null && !firstName.equals(currentUser.getFirstName())) {
            currentUser.setFirstName(firstName);
        }

        if (lastName != null && !lastName.equals(currentUser.getLastName())) {
            currentUser.setLastName(lastName);
        }

        if(schoolName != null && !schoolName.equals(currentUser.getSchoolName())) {
            currentUser.setSchoolName(schoolName);
        }
    }
}