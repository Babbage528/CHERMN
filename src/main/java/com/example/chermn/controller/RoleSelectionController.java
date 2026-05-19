package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the role selection screen.
 * Handles role selection logic (Student, Teacher, or Parent) and navigation.
 */
public class RoleSelectionController extends BaseController {

    private String selectedRole = "";

    @FXML
    private Button nextButton;

    @FXML
    private VBox studentCard, teacherCard, parentCard;

    /**
     * Initializes the controller, disabling the next button by default.
     */
    @FXML
    public void initialize() {
        nextButton.setDisable(true);
        nextButton.setStyle("-fx-background-color: grey; -fx-opacity: 0.6; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-font-size: 18px; -fx-background-radius: 15;");
    }

    @FXML
    private void selectStudent() {
        selectedRole = "Student";
        highlightCard(studentCard);
    }

    @FXML
    private void selectTeacher() {
        selectedRole = "Teacher";
        highlightCard(teacherCard);
    }

    @FXML
    private void selectParent() {
        selectedRole = "Parent";
        highlightCard(parentCard);
    }

    private String extractBackground(VBox card) {
        String style = card.getStyle();

        // If the card already has a background set inline, keep it
        if (style.contains("-fx-background-color")) {
            int start = style.indexOf("-fx-background-color");
            int end = style.indexOf(";", start) + 1;
            return style.substring(start, end);
        }
        // Otherwise, default to transparent (or whatever you want)
        return "-fx-background-color: transparent;";
    }


    /**
     * Highlights the selected role card and enables the next button.
     *
     * @param activeCard The VBox that was clicked
     */
    private void highlightCard(VBox activeCard) {

        // Reset all cards to their default background + no border
        studentCard.setStyle(
                "-fx-background-color: #D5E8D4;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-color: transparent;"
        );
        teacherCard.setStyle(
                "-fx-background-color: #FFF2CC;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-color: transparent;"
        );
        parentCard.setStyle(
                "-fx-background-color: #F8CECC;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-color: transparent;"
        );

        // Extract the card's current background colour
        String bg = extractBackground(activeCard);

        // Apply highlight while keeping background + rounded corners
        activeCard.setStyle(
                bg +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-color: #3E7C2B;" +
                        "-fx-border-width: 3;"
        );

        nextButton.setDisable(false);
        nextButton.setStyle("-fx-background-color: #6DBE45; -fx-text-fill: white;" +
                "-fx-font-weight: bold; -fx-font-size: 18px;-fx-background-radius: 15;");
    }


    /**
     * Navigates to the corresponding registration form based on the selected role.
     *
     * @param event The action event from the Next button
     * @throws IOException If the FXML file cannot be loaded
     */
    @FXML
    private void handleNext(ActionEvent event) throws IOException {
        if (selectedRole.isEmpty()) return;

        String fxmlFile = switch (selectedRole) {
            case "Student" -> "register-student.fxml";
            case "Teacher" -> "register-teacher.fxml";
            case "Parent" -> "register-parent.fxml";
            default -> "";
        };

        if (!fxmlFile.isEmpty()) {
            switchScene(event, fxmlFile);
        }
    }

    /**
     * Returns the user to the onboarding screen.
     *
     * @param event The action event from the Back button
     */
    @FXML
    private void handleBack(ActionEvent event) {
        try {
            switchScene(event, "onboarding-screen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility method to switch between scenes.
     */
    private void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource(fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Public constructor of role selection controller class.
     */
    public RoleSelectionController() {
    }
}