package com.example.chermn;

import com.example.chermn.controller.QuizBeginApiService;
import com.example.chermn.controller.QuizQuestionsController;
import com.example.chermn.controller.QuizSessionController;
import com.example.chermn.model.TriviaQuestion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

/**
 * Entry point for quizzes.
 * This class defines the startup of quizzes and window formatting.
 */
public class QuizBegin extends Application{

    /** Title for the actual window of the application. **/
    public static final String TITLE = "Farmer Fred's Trivia";

    /** Width constant for the actual window of the application. **/
    public static final int WIDTH = 1280;

    /** Height constant for the actual window of the application. **/
    public static final int HEIGHT = 720;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizBegin.class.getResource("quiz-begin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();


    }

    /**
     * The main method that launches Javafx UI for the quiz begin scene.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Public constructor for the QuizBegin class.
     */
    public QuizBegin() {

    }
}
