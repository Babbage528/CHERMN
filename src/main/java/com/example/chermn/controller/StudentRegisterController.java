package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import com.example.chermn.model.Student;
import com.example.chermn.model.MockUserDAO;
import com.example.chermn.model.IUserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class StudentRegisterController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField schoolNameField;
    @FXML private TextField unameField;
    @FXML private PasswordField passwordField;

    private MockUserDAO userDAO = new MockUserDAO();

    @FXML
    private void handleRegisterSubmit(ActionEvent event) throws IOException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String school = schoolNameField.getText();
        String username = unameField.getText();
        String password = passwordField.getText();

        if (firstName.isEmpty() ||lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            System.out.println("Gagal: Harap isi semua field!");
            return;
        }

        Student s = new Student(0, username, firstName, lastName, password, school, 0, 0, 0);
        userDAO.addUser(s);

        System.out.println("Registrasi Berhasil untuk Username: " + username);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}