package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class teacherParentHomescreenController extends BaseProfileController {
    @FXML
    private Button signOutButton;
    @FXML
    private ImageView fullBackground;
    @FXML
    private StackPane contentArea;


    // creating a method to load the view of the profile depending on the button pressed
    //private void loadView(String fxml) {
    //    try {
     //       FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource(fxml));
      //      Parent view = loader.load();

       //     contentArea.getChildren().setAll(view);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}


    public void initialize() {
        // defining variable for current logged-in user
        Users user = Session.getCurrentUser();
    }

    @Override
    @FXML
    protected void signOutButtonClick(ActionEvent event) throws IOException {
        super.signOutButtonClick(event);
    }

    //@FXML
    //protected void signOutButtonClick() throws IOException {
    //    Stage stage = (Stage) signOutButton.getScene().getWindow();
    //    FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("onboarding-screen.fxml"));
    //    Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
    //    stage.setScene(scene);
    //}

    @FXML
    private void showDashboard(ActionEvent actionEvent) {
        // want to change opacity of background picture back to normal
        fullBackground.setOpacity(1);

    }


    @Override
    protected StackPane getContainer() {
        return contentArea;
    }

    @FXML
    private void showStudentsDetails(ActionEvent actionEvent) {
        // want to change opacity of background picture so that details displayed on top
        fullBackground.setOpacity(0.4);

        // assigning the container for load view
        // change to the view students details screen
        loadView( "student-details-page.fxml");

    }

    @FXML
    private void showUserDetails(ActionEvent actionEvent) {
        // changing opacity of background picture to display details over top
        fullBackground.setOpacity(0.4);

        // change to the user details screen
        loadView( "update-user-details.fxml");
    }


    @FXML
    @Override
    protected void changePasswordButtonClick(ActionEvent event) {
        super.changePasswordButtonClick(event);
    }


    }

