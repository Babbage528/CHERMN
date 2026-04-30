package com.example.chermn.controller;

import java.io.IOException;
import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;

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

public class StudentRegisterController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField schoolNameField;
    @FXML private TextField unameField;
    @FXML private PasswordField passwordField;

    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleRegisterSubmit(ActionEvent event) throws IOException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String school = schoolNameField.getText();
        String email = unameField.getText().trim().toLowerCase();;
        String password = passwordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all the field");
            return;
        }

        if (password.length() < 5) {
            showAlert(Alert.AlertType.WARNING, "Password is weak", "Password has to be 5 character min!");
            return;
        }
        if (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            showAlert(Alert.AlertType.WARNING, "Invalid Email", "Please enter a valid email address (e.g., name@example.com)");
            return;
        }
        if (userDAO.isEmailTaken(email)) {
            showAlert(Alert.AlertType.ERROR, "Registration Error",
                    "This email is already registered. Please use another email or login.");
            return;
        }
        try {

            Student student = new Student(0, email, firstName, lastName, password, school, 1, 1, 1);
            userDAO.createStudent(student);

            // store in session
            Session.setCurrentUser(student);

            showAlert(Alert.AlertType.INFORMATION, "Registration success", "Account " + email + " successfully registered!");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "There is problem when saving to the DB.");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("role-selection.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
