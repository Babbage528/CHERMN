package com.example.chermn;

import com.example.chermn.controller.QuizBeginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point for launching the Farmer Fred's Trivia JavaFX application.
 * <p>
 * Loads the initial onboarding screen and sets up the primary stage
 * with the application's default window size and title.
 */
public class QuizBegin extends Application {

    /** Window title for the application. */
    public static final String TITLE = "Farmer Fred's Trivia";

    /** Default window width. */
    public static final int WIDTH = 1280;

    /** Default window height. */
    public static final int HEIGHT = 720;

    /**
     * Default constructor for QuizBegin.
     * Required by JavaFX for application startup.
     */
    public QuizBegin() {}

    /**
     * Starts the JavaFX application by loading the onboarding screen.
     *
     * @param stage the primary stage provided by the JavaFX runtime
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizBegin.class.getResource("onboarding.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch();
    }
}