package com.example.chermn.controller;


import com.example.chermn.controller.QuizBeginApiService;
import com.example.chermn.QuizBegin;
import com.example.chermn.model.TriviaQuestion;
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
import java.util.List;

public class QuizBeginController {

    @FXML
    private Button returnToHomepageButton;

    @FXML
    private Button beginQuizButton;

    // save the category and difficulty to pass later
    private String selectedCategory;
    private String selectedDifficulty;

    public void initCategory(String category){
        this.selectedCategory = category;
    }
    public void initDifficulty(String difficulty){
        this.selectedDifficulty = difficulty;
    }

    @FXML
    protected void returnToHomepageButtonClick() throws  IOException{
        Stage stage = (Stage) returnToHomepageButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(QuizBegin.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), QuizBegin.WIDTH, QuizBegin.HEIGHT);
        stage.setScene(scene);
    }


    @FXML
    protected void beginQuizButtonClick(ActionEvent event) throws IOException, JSONException {
        FXMLLoader loader = new FXMLLoader(QuizBegin.class.getResource("quiz-questions.fxml"));
        Parent root = loader.load();

        QuizQuestionsController controller = loader.getController();
        // Pass category + difficulty from THIS screen
        controller.initData(selectedCategory, selectedDifficulty);

        // Now load the scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, QuizBegin.WIDTH, QuizBegin.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }





}

