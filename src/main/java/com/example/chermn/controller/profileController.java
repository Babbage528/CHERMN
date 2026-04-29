package com.example.chermn.controller;

// the profile will display the users information and allows them to update

import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.AbstractQueue;


public class profileController extends BaseProfileController{

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
    // protected Student currentUser;
    protected Users user;

    // initalizing variabels
    public void initialize() {
        user = Session.getCurrentUser();
        // setCurrentUser(user);
    }


    // defining the associated actions associated with the above button variables
    //public void setCurrentUser(Users user) {
    //        // check whether the user is a student
      //      if (user instanceof Student student) {
        //        this.currentUser = student;
          //  }
            //else {
              //  throw new IllegalArgumentException("Student profile requires a Student user");
            //}

        //}

    // assign the user to currentUser as a student


    @Override
    protected StackPane getContainer() {
        return contentPane;
    }

    @FXML
    private void userProfileButtonClick() {
        loadView("update-user-details.fxml");
    }


    @FXML
    private void levelProgressButtonClick() {
        loadView( "view-level-progress.fxml");

    }

    @FXML
    @Override
    protected void changePasswordButtonClick(ActionEvent event) {
        super.changePasswordButtonClick(event);
    }

    @FXML
    protected void closeButtonClick() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
        Scene scene = new Scene(loader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

//    @FXML
//    protected void signOutButtonClick() throws IOException {
//        Stage stage = (Stage) signOutButton.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("onboarding-screen.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
//        stage.setScene(scene);
//    }



    @Override
    protected void signOutButtonClick(ActionEvent event) throws IOException {
        super.signOutButtonClick(event);
    }



}