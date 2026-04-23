package com.example.chermn.controller;


import com.example.chermn.QuizBegin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class QuizBeginController {

    @FXML
    private Button returnToHomepageButton;

    @FXML
    private Button beginQuizButton;

    @FXML
    protected void returnToHomepageButtonClick() throws  IOException{
        Stage stage = (Stage) returnToHomepageButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(QuizBegin.class.getResource("home-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), QuizBegin.WIDTH, QuizBegin.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    protected void beginQuizButtonClick() throws  IOException{
        Stage stage = (Stage) beginQuizButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(QuizBegin.class.getResource("quiz-questions.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), QuizBegin.WIDTH, QuizBegin.HEIGHT);
        stage.setScene(scene);
    }





}

