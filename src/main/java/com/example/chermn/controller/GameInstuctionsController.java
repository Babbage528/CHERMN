package com.example.chermn.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * A public class that displays the game instructions to the screen for the user.
 */
public class GameInstuctionsController
{
    /**
     * Displays a help dialog with instructions and game objectives.
     */
    public static void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Instructions");
        alert.setHeaderText("Farmer Fred's Trivia Application: Instructions");

        Label content = new Label(
                "This is a long instruction message that will tell the user how to use the game with instructions and objectives. " +
                        "Could start off being like: there are a variety of trivia topics to choose from including ... " +
                        "they range in varying difficulty levels - easy to hard - that you can progress through. " +
                        "You can keep track of your progress through the progress bar at the top of the homepage screen " +
                        "or through your profile which you can access via clicking on the farmhouse."
        );

        content.setWrapText(true);
        content.setMaxWidth(Double.MAX_VALUE);
        alert.getDialogPane().setPrefWidth(800);
        alert.getDialogPane().setContent(content);
        content.setStyle("-fx-font-size: 14px;");

        alert.showAndWait();
    }

}
