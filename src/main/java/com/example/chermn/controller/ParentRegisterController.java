package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Parent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ParentRegisterController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField unameField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> relationshipComboBox;
    @FXML private TextField studentNameField;
    @FXML private TextField schoolNameField;

    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleRegisterSubmit(ActionEvent event) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String username = unameField.getText().trim();
        String password = passwordField.getText();
        String relationship = relationshipComboBox.getValue();
        String studentName = studentNameField.getText().trim();
        String school = schoolNameField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() ||
                password.isEmpty() || studentName.isEmpty() || school.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all the fields");
            return;
        }

        if (password.length() < 5) {
            showAlert(Alert.AlertType.WARNING, "Password is weak", "Password has to be 5 characters minimum!");
            return;
        }

        try {
            int foundStudentId = userDAO.findStudentIdByName(studentName, school);

            if (foundStudentId == -1) {
                showAlert(Alert.AlertType.ERROR, "Registration Error",
                        "There is no student named '" + studentName + "' in " + school +
                                ". Please check, is it right or not?");
                return;
            }
            Parent p = new Parent(0, username, firstName, lastName, password, school, relationship, studentName);
            p.setStudentId(foundStudentId);
            userDAO.createParent(p);

            Session.setCurrentUser(p);
            showAlert(Alert.AlertType.INFORMATION, "Registration Success", "Account " + username + " successfully registered!");
            switchScene(event, "homepage.fxml");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            switchScene(event, "role-selection.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource(fxmlFile));
        javafx.scene.Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}