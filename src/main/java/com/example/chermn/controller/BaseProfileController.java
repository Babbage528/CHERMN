package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Node;

import javax.swing.*;
import javafx.event.ActionEvent;
import java.io.IOException;

public abstract class BaseProfileController {

    protected abstract StackPane getContainer();

    public void loadView(String fxml) {
        try {
            Parent view = FXMLLoader.load(
                    BaseProfileController.class.getResource("/com/example/chermn/" + fxml)
            );
            getContainer().getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String setProfileType(Users user) {
        String fxml;
        if (userDAO.getUserByUsername(user.getUserName()) instanceof Student student)
        {
            fxml = "change-password.fxml";
        }
        else
        {
            fxml = "teacher-changePassword.fxml";
        }

        return fxml;
    }

    @FXML
    protected void changePasswordButtonClick(ActionEvent event) {
        loadView(setProfileType(user));
    }

    @FXML
    protected void signOutButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("onboarding-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }



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


}
