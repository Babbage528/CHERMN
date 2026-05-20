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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.Node;

/**
 * Utility class for displaying customized JavaFX alert dialogs.
 * Provides styled pop-up windows for errors, success messages, and warnings
 * using a consistent visual theme.
 */
public class AlertHelper {

    public static void showError(String title, String message) {
        showCustomAlert(title, message, "#ff6b6b");
    }

    public static void showSuccess(String title, String message) {
        showCustomAlert(title, message, "#6bcB77");
    }

    public static void showWarning(String title, String message) {
        showCustomAlert(title, message, "#f7b731");
    }

    private static void showCustomAlert(String title, String message, String color) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: " + color + ";");

        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555;");

        Button okButton = new Button("OK");
        okButton.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 12; -fx-padding: 8 24 8 24; -fx-cursor: hand;");
        okButton.setOnAction(e -> stage.close());

        VBox root = new VBox(15, titleLabel, messageLabel, okButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-border-radius: 20; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 20, 0, 0, 4);");

        Scene scene = new Scene(root);
        scene.setFill(null);
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * Displays a custom reset password dialog with a dark overlay (shadow background).
     *
     * @param triggerNode The UI element (like the button clicked) that triggered this event
     * @param title Dialog title
     * @return The confirmed new password, or null if cancelled
     */
    public static String showResetPasswordDialog(Node triggerNode, String title) {
        // Dynamic generation: Find the main screen stage based on the button clicked
        Stage parentStage = (Stage) triggerNode.getScene().getWindow();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initOwner(parentStage); // Bind ownership to parent window

        // DARK OVERLAY
        VBox overlay = new VBox();
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        overlay.prefWidthProperty().bind(parentStage.widthProperty());
        overlay.prefHeightProperty().bind(parentStage.heightProperty());

        // POP UP BOX
        VBox root = new VBox(15);
        root.setMaxWidth(350);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: white; -fx-background-radius: 25; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 30, 0, 0, 10);");

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #8cc63f;");

        String labelStyle = "-fx-font-size: 13px; -fx-text-fill: #666666;";
        String fieldStyle = "-fx-background-color: #f1f3f1; -fx-background-radius: 12; -fx-padding: 12; -fx-text-fill: #333333;";

        Label newPassLabel = new Label("New Password*");
        newPassLabel.setStyle(labelStyle);
        PasswordField newPassField = new PasswordField();
        newPassField.setStyle(fieldStyle);

        Label confirmPassLabel = new Label("Reconfirm Password*");
        confirmPassLabel.setStyle(labelStyle);
        PasswordField confirmPassField = new PasswordField();
        confirmPassField.setStyle(fieldStyle);

        Label errorMsg = new Label("");
        errorMsg.setStyle("-fx-text-fill: #ff6b6b; -fx-font-size: 12px;");
        errorMsg.setVisible(false);

        final String[] finalPassword = {null};

        Button updateBtn = new Button("Update Password");
        updateBtn.setCursor(javafx.scene.Cursor.HAND);
        updateBtn.setStyle("-fx-background-color: #8cc63f; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-padding: 10 25;");

        updateBtn.setOnAction(e -> {
            String p1 = newPassField.getText();
            String p2 = confirmPassField.getText();

            if (p1.isEmpty() || p2.isEmpty()) {
                errorMsg.setText("Fields cannot be empty!");
                errorMsg.setVisible(true);
            } else if (!p1.equals(p2)) {
                newPassField.clear();
                confirmPassField.clear();
                errorMsg.setText("Passwords do not match! Try again.");
                errorMsg.setVisible(true);
            } else if (p1.length() < 5) {
                newPassField.clear();
                confirmPassField.clear();
                errorMsg.setText("Password must be 5 characters or longer.");
                errorMsg.setVisible(true);
            } else {
                finalPassword[0] = p1;
                stage.close();
            }
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setCursor(javafx.scene.Cursor.HAND);
        cancelBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #999999;");
        cancelBtn.setOnAction(e -> stage.close());

        HBox actionBox = new HBox(15, cancelBtn, updateBtn);
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().addAll(
                titleLabel,
                new VBox(5, newPassLabel, newPassField),
                new VBox(5, confirmPassLabel, confirmPassField),
                errorMsg,
                actionBox
        );

        overlay.getChildren().add(root);

        Scene scene = new Scene(overlay);
        scene.setFill(null);
        stage.setScene(scene);

        stage.setX(parentStage.getX());
        stage.setY(parentStage.getY());

        stage.showAndWait();
        return finalPassword[0];
    }

    public static void showInstructions(String title, String headerText, String message) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #6bcB77;");

        Label headerLabel = new Label(headerText);
        headerLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #6bcB77;");

        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555;");
        messageLabel.setMaxHeight(800);

        VBox contentBox = new VBox(10, titleLabel, headerLabel, messageLabel);
        contentBox.setPadding(new Insets(20));

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(400);
        scrollPane.setStyle("-fx-background: white; -fx-border-color: transparent;");

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> stage.close());
        okButton.setStyle("-fx-background-color: #6bcB77; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-padding: 8 24 8 24;");

        VBox root = new VBox(15, scrollPane, okButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 20, 0, 0, 4);");

        Scene scene = new Scene(root);
        scene.setFill(null);

        stage.setMaxHeight(800);
        stage.setMaxWidth(800);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private AlertHelper() {
    }
}