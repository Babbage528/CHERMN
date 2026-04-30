package com.example.chermn.controller;


import com.example.chermn.QuizBegin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.json.JSONException;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class QuizBeginController {

    @FXML
    private Button returnToHomepageButton;

    @FXML
    private Label categoryLabel;

    @FXML
    private Button beginQuizButton;

    /** Protected void 'returnToHomepageButtonClick' defines the actions that occur when the return to homepage ui element
     * is selected. The function returns the user to the homepage screen.
     */
    @FXML
    protected void returnToHomepageButtonClick() throws  IOException{
        Stage stage = (Stage) returnToHomepageButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(QuizBegin.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), QuizBegin.WIDTH, QuizBegin.HEIGHT);
        stage.setScene(scene);
    }

    /** Protected void 'beginQuizButtonClick' defines the actions that occur when the begin quiz button
     * is selected. The function continues the user onto the quiz questions scene screen.
     */
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

    /** Public void 'setCategoryText' Acts as a setter for the fxml category text label. Used by home page controller.
     */
    @FXML
    public void setCategoryText(String text) {
        categoryLabel.setText(text);
    }
}

