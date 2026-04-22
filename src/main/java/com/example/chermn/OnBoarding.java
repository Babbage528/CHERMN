package com.example.chermn;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OnBoarding extends Application{
    public static final String TITLE = "Login View";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    @Override
    public void start(Stage stage) throws Exception {        
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("onboarding-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
