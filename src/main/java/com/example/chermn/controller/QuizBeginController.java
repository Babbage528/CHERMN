package com.example.chermn.controller;


import com.example.chermn.DatabaseConnection;
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

import static com.example.chermn.Session.currentUser;

public class QuizBeginController {

    @FXML
    private Button returnToHomepageButton;

    @FXML
    private Button beginQuizButton;

    // save the category and difficulty to pass later
    @FXML
    private String selectedCategory;
    @FXML
    private String difficulty;

    public void initCategory(String category){
        this.selectedCategory = category;
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

        // 1. Determine difficulty based on category
        switch (selectedCategory.toLowerCase()) {
            case "animals":
                difficulty = String.valueOf(currentUser.getAnimalLevel());
                break;
            case "nature":
                difficulty = String.valueOf(currentUser.getNatureLevel());
                break;
            case "vehicles":
                difficulty = String.valueOf(currentUser.getVehicleLevel());
                break;
            default:
                difficulty = "1"; // fallback
        }

        // 2. Load quiz-questions.fxml
        FXMLLoader loader = new FXMLLoader(QuizBegin.class.getResource("quiz-questions.fxml"));
        Parent root = loader.load();

        // 3. Pass category + difficulty to QuizQuestionsController
        QuizQuestionsController controller = loader.getController();
        controller.initData(selectedCategory, difficulty);

        // 4. Switch scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, QuizBegin.WIDTH, QuizBegin.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}

