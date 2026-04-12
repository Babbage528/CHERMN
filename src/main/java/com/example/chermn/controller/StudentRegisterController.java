package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class StudentRegisterController {

    @FXML private TextField fullNameField;
    @FXML private TextField schoolNameField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> gradeComboBox;

    @FXML
    private void handleRegisterSubmit(ActionEvent event) throws IOException {
        String name = fullNameField.getText();
        String school = schoolNameField.getText();
        String grade = gradeComboBox.getValue();
        String email = emailField.getText();

        if (name.isEmpty() || grade == null) {
            System.out.println("Please fill in all the field!");
            return;
        }

        System.out.println("Student Data: " + name + " From " + school + " (" + grade + ")");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }
}