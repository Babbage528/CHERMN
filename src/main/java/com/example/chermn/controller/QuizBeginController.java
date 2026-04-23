package com.example.chermn.controller;


import com.example.chermn.OnBoarding;
import com.example.chermn.QuizBegin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.json.JSONException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import javax.imageio.IIOParam;
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
    protected void beginQuizButtonClick(ActionEvent event) throws IOException, JSONException {
        FXMLLoader loader = new FXMLLoader(QuizBegin.class.getResource("quiz-questions.fxml"));
        Parent root = loader.load();
        QuizQuestionsController scene2Controller = loader.getController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, QuizBegin.WIDTH, QuizBegin.HEIGHT);
        stage.setScene(scene);
        stage.show();
        scene2Controller.getQuestions();


    }





}

