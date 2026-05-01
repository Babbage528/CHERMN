package com.example.chermn.controller;

import java.io.IOException;

import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.model.UserValidation;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;
import com.example.chermn.model.Teacher;

import com.example.chermn.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TeacherRegisterController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField schoolNameField;
    @FXML private TextField unameField;
    @FXML private PasswordField passwordField;

    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleRegisterSubmit(ActionEvent event) throws IOException {
        try {
            String firstName =firstNameField.getText();
            String lastName = lastNameField.getText();
            String school = schoolNameField.getText();
            String username = unameField.getText();
            String password = passwordField.getText();

            Teacher t = new Teacher(0, username, firstName, lastName, password, school);
            userDAO.createUser(t);
            System.out.println("Role: TEACHER");
            System.out.println("Registration successful for username: " + username + "FirstName: " + firstName + "LastName: " + lastName + "school: " + school);

            // fetch the actual saved user from DB (with real ID)
            Users savedUser = userDAO.getUserByUsername(username);

            // store in session
            Session.setCurrentUser(savedUser);
            // store in session
            Session.setCurrentUser(savedUser);

            // shows alert to the teacher that they have successfully registered
            showAlert(Alert.AlertType.INFORMATION, "Registration success", "Account " + username + " successfully registered!");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("teacher-parent-homescreen.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
            stage.show();
        }
        // if any of the fields inputs were incorrect
        catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", e.getMessage());
        }
        // any database/saving error
        catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "There is problem when saving to the DB.");
        }

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
