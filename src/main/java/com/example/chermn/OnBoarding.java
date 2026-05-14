package com.example.chermn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main entry point for the JavaFX application.
 * This class handles the initial setup of the primary stage, defines application
 * window dimensions, and loads the starting onboarding screen.
 */
public class OnBoarding extends Application {

    /** The title of the primary application window */
    public static final String TITLE = "Login View";

    /** The default width for all application scenes */
    public static final int WIDTH = 1280;

    /** The default height for all application scenes */
    public static final int HEIGHT = 720;

    /**
     * Initializes and displays the primary stage with the onboarding FXML layout.
     *
     * @param stage The primary stage for this application
     * @throws Exception If the onboarding-screen.fxml file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("onboarding-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}