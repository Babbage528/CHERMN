package com.example.chermn.controller;


import com.example.chermn.OnBoarding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;


public class SettingsController {

    // defining the buttons and sliders used in the settings screen
    @FXML
    private Button closeButton;

    @FXML
    private Button signOutButton;

    @FXML
    private Button getHelpButton;

    // could expand on this later to include actions for when slider moved
    @FXML
    private Slider musicSlider;

    @FXML
    private Slider soundFXSlider;


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

    // shows the help dialogue for the game
    @FXML
    protected void getHelpButtonClick() throws IOException {
        showHelpDialog();
    }


    // Displays a help dialog with instructions.
    protected void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Farmer Fred's Trivia Application: Instructions");

        // put the instructions within a label as setContent doesn't wrap
        Label content = new Label(
                "This is a long instruction message that will tell the user how to use game with instructions and objectives. " +
                        "Could start off being like: there are a variety of trivia topics to choose from including ...." +
                        "they range in varying difficulty levels - easy to hard - that you can progress through " +
                        "you can keep track of your progress through the progress bar at the top of the homepage screen" +
                        "or through your profile which you can access via the clicking on the farm house"
        );

        // let the text wrap around, with a maximum box width of 350
        content.setWrapText(true);
        content.setMaxWidth(Double.MAX_VALUE); // allow it to shrink properly
        alert.getDialogPane().setPrefWidth(800);

        // set the content of the dialog pane to be the label
        alert.getDialogPane().setContent(content);

        // adjust font size
        content.setStyle("-fx-font-size: 14px;");

        alert.showAndWait();
    }


}




