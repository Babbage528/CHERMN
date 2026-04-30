package com.example.chermn.controller;

// the profile will display the users information and allows them to update

import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;



private class profileController {
    @FXML
    private StackPane contentPane;

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

    // instantiate userDAO to access
    protected UserDAO userDAO = new UserDAO();

    // defining variable for current logged-in user
    protected Student currentUser;

    // initalizing variabels
    public void initialize() {
        Users user = Session.getCurrentUser();
        setCurrentUser(user);
    }


    // defining the associated actions associated with the above button variables


    // assign the user to currentUser as a student
    public void setCurrentUser(Users user) {
        // check whether the user is a student
        if (user instanceof Student student) {
            this.currentUser = student;
        }
        else {
            throw new IllegalArgumentException("Student profile requires a Student user");
        }

    }


    // creating a method to load the view of the profile depending on the button pressed
    private void loadView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource(fxml));
            Parent view = loader.load();
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void userProfileButtonClick() {
        loadView("update-user-details.fxml");
    }


    @FXML
    private void levelProgressButtonClick() {
        loadView("view-level-progress.fxml");

    }

    @FXML
    private void changePasswordButtonClick() {
        loadView("change-password.fxml");
    }

    @FXML
    protected void closeButtonClick() throws IOException {
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


}