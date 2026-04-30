package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class RoleSelectionController {

    private String selectedRole = "";

    @FXML
    private VBox studentCard, teacherCard, parentCard;

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

    private void highlightCard(VBox activeCard) {
        studentCard.setStyle(studentCard.getStyle() + "-fx-border-color: transparent;");
        teacherCard.setStyle(teacherCard.getStyle() + "-fx-border-color: transparent;");
        parentCard.setStyle(parentCard.getStyle() + "-fx-border-color: transparent;");

        activeCard.setStyle(activeCard.getStyle() + "-fx-border-color: #3E7C2B; -fx-border-width: 3; -fx-border-radius: 20;");
    }

    @FXML
    private void handleNext(ActionEvent event) throws IOException {
        if (selectedRole.isEmpty()) {
            System.out.println("Please choose your role!");
            return;
        }

        String fxmlFile = "";
        switch (selectedRole) {
            case "Student":
                fxmlFile = "register-student.fxml";
                break;
            case "Teacher":
                fxmlFile = "register-teacher.fxml";
                break;
            case "Parent":
                fxmlFile = "register-parent.fxml";
                break;
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource(fxmlFile));

        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("onboarding-screen.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}