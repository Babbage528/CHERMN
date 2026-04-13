package com.example.chermn.controller;

import com.example.chermn.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ProfileController {
    // exit - go back to homepage
    @FXML
    protected void goBack(ActionEvent event) {
        SceneLoader.swapScene(event, "homepage.fxml", "Homepage");
    }

}
