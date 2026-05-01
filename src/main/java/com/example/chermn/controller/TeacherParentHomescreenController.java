package com.example.chermn.controller;

import com.example.chermn.Session;
import com.example.chermn.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class TeacherParentHomescreenController extends BaseProfileController {
    @FXML
    private Button signOutButton;
    @FXML
    private ImageView fullBackground;
    @FXML
    private StackPane contentArea;


    @Override
    @FXML
    protected void signOutButtonClick(ActionEvent event) throws IOException {
        super.signOutButtonClick(event);
    }

    @FXML
    private void showDashboard(ActionEvent actionEvent) {
        // want to change opacity of background picture back to normal
        fullBackground.setOpacity(1);

        // remove any loaded sub-view to get the basic dashboard back again
        contentArea.getChildren().clear();
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
        loadView( "teacher-viewDetails.fxml");
    }


    @FXML
    @Override
    protected void changePasswordButtonClick(ActionEvent event) {
        // want to change opacity of background picture so that details displayed on top
        fullBackground.setOpacity(0.4);

        super.changePasswordButtonClick(event);
    }

}

