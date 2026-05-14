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
        nextButton.setStyle("-fx-background-color: grey; -fx-opacity: 0.6;");
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

    /**
     * Highlights the selected role card and enables the next button.
     *
     * @param activeCard The VBox that was clicked
     */
    private void highlightCard(VBox activeCard) {
        studentCard.setStyle("-fx-border-color: transparent;");
        teacherCard.setStyle("-fx-border-color: transparent;");
        parentCard.setStyle("-fx-border-color: transparent;");

        activeCard.setStyle("-fx-border-color: #3E7C2B; -fx-border-width: 3; -fx-border-radius: 20;");

        nextButton.setDisable(false);
        nextButton.setStyle("-fx-background-color: #3E7C2B; -fx-text-fill: white; -fx-opacity: 1;");
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
}