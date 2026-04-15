
package com.example.chermn.controller;


import com.example.chermn.QuizQuestions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

import java.awt.*;

public class QuizQuestionsController {
    @FXML
    private Pane container;

    @FXML
    private Button option1, option2, option3, option4;

    @FXML
    private Label explanation;

    @FXML
    private void AnswerSubmitted() {

        explanation.setText("Incorrect / Correct! Strawberries are better to plant in winter.");
        explanation.setStyle("-fx-background-color: #ECFCE3; -fx-font-size: 20px;");

        option1.setDisable(true);
        option3.setDisable(true);
        option4.setDisable(true);


    }
}
