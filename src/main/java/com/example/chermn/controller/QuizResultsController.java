package com.example.chermn.controller;

import com.example.chermn.controller.QuizQuestionsController;
import com.example.chermn.QuizBegin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizResultsController {

    @FXML
    private Label resultsLabel;

    @FXML
    private Button returnToHomepageButton;

    @FXML
    private Button beginQuizButton;

    public QuizResultsController() throws JSONException {
    }

    @FXML
    protected void returnToHomepageButtonClick() throws  IOException{
        Stage stage = (Stage) returnToHomepageButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(QuizBegin.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), QuizBegin.WIDTH, QuizBegin.HEIGHT);
        stage.setScene(scene);
        initialize();
    }

    int percentageScore = (QuizQuestionsController.score*10);

    public void initialize() {
        if (percentageScore >= 60) {
            resultsLabel.setText("You passed with " + percentageScore + "%");
        }
        else {
            resultsLabel.setText("You failed with " + percentageScore + "%");
        }

    }





}