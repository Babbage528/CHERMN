package com.example.chermn.controller;

import com.example.chermn.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomepageController {
    @FXML
    protected void gotoProfile(ActionEvent event) {
        SceneLoader.swapScene(event, "profile.fxml", "Profile");
    }

    @FXML
    protected void gotoSettings(ActionEvent event) {
        SceneLoader.swapScene(event, "settings.fxml", "Settings");
    }

    @FXML
    protected void gotoQuiz(ActionEvent event) {
        SceneLoader.swapScene(event, "quiz.fxml", "Quiz");
    }

}
