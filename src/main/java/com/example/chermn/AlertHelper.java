package com.example.chermn;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Utility class for displaying customized JavaFX alert dialogs.
 * Provides styled pop-up windows for errors, success messages, and warnings
 * using a consistent visual theme.
 */
public class AlertHelper {

    /**
     * Displays a customized error alert with a red color theme.
     *
     * @param title The text to display in the alert header
     * @param message The detailed error message to display in the body
     */
    public static void showError(String title, String message) {
        showCustomAlert(title, message, "#ff6b6b");
    }

    /**
     * Displays a customized success alert with a green color theme.
     *
     * @param title The text to display in the alert header
     * @param message The detailed success message to display in the body
     */
    public static void showSuccess(String title, String message) {
        showCustomAlert(title, message, "#6bcB77");
    }

    /**
     * Displays a customized warning alert with a yellow/orange color theme.
     *
     * @param title The text to display in the alert header
     * @param message The detailed warning message to display in the body
     */
    public static void showWarning(String title, String message) {
        showCustomAlert(title, message, "#f7b731");
    }

    /**
     * Internal helper method to construct and display a modal stage with custom CSS styling.
     * Configures the layout, labels, and "OK" button based on the provided theme color.
     *
     * @param title The text for the title label
     * @param message The text for the description label
     * @param color The hex color code used for text and button styling
     */
    private static void showCustomAlert(String title, String message, String color) {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);

        // TITLE
        Label titleLabel = new Label(title);
        titleLabel.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " + color + ";"
        );

        // MESSAGE
        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-text-fill: #555555;"
        );

        // BUTTON
        Button okButton = new Button("OK");
        okButton.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 8 24 8 24;" +
                        "-fx-cursor: hand;"
        );

        okButton.setOnAction(e -> stage.close());

        // ROOT
        VBox root = new VBox(15);
        root.getChildren().addAll(titleLabel, messageLabel, okButton);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        root.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 20, 0, 0, 4);"
        );

        Scene scene = new Scene(root);
        scene.setFill(null);

        stage.setScene(scene);
        stage.showAndWait();
    }
}