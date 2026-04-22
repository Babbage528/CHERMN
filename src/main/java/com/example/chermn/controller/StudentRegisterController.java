package com.example.chermn.controller;

import java.io.IOException;
import com.example.chermn.OnBoarding;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;

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
        String username = unameField.getText();
        String password = passwordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all the field");
            return;
        }

        if (password.length() < 5) {
            showAlert(Alert.AlertType.WARNING, "Password Lemah", "Password harus minimal 5 karakter!");
            return;
        }

        try {

            Student s = new Student(0, username, firstName, lastName, password, school, 1, 1, 1);
            userDAO.createStudent(s);

            showAlert(Alert.AlertType.INFORMATION, "Registrasi Berhasil", "Akun " + username + " telah berhasil dibuat!");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Terjadi kesalahan saat menyimpan data ke database.");
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