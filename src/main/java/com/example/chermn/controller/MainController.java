package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import com.example.chermn.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {

    @FXML private ListView<Users> userListView;
    @FXML private TextField firstNameField, lastNameField, usernameField, schoolField;
    @FXML private PasswordField passwordField;

    private MockUserDAO userDAO = new MockUserDAO();

    private void selectUser(Users user) {
        userListView.getSelectionModel().select(user);
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        schoolField.setText(user.getSchoolName());
        usernameField.setText(user.getUserName());
        passwordField.setText(user.getPassword());
    }

    private ListCell<Users> renderUserCell(ListView<Users> userListView) {
        return new ListCell<>() {
            private void onUserSelected(MouseEvent mouseEvent) {
                ListCell<Users> clickedCell = (ListCell<Users>) mouseEvent.getSource();
                Users selectedUser = clickedCell.getItem();
                if (selectedUser != null) {
                    selectUser(selectedUser);
                }
            }

            @Override
            protected void updateItem(Users user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                    setOnMouseClicked(this::onUserSelected);
                } else {
                    setText(user.getFirstName() + " " + user.getLastName());
                    setOnMouseClicked(this::onUserSelected);
                }
            }
        };
    }

    private void syncUsers() {
        userListView.getItems().clear();
        userListView.getItems().addAll(userDAO.getAllUsers());
    }

    @FXML
    public void initialize() {
        userListView.setCellFactory(this::renderUserCell);
        syncUsers();
    }

    @FXML
    private void onAdd() {
        Users newUser = new Student(0, "newuser", "New", "User", "123", "QUT", 0, 0, 0);
        userDAO.addUser(newUser);
        syncUsers();
        selectUser(newUser);
        firstNameField.requestFocus();
    }

    @FXML
    private void onEditConfirm() {
        Users selected = userListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setFirstName(firstNameField.getText());
            selected.setLastName(lastNameField.getText());
            selected.setPassword(passwordField.getText());

            userDAO.updateUser(selected);
            syncUsers();
        }
    }

    @FXML
    private void onDelete() {
        Users selected = userListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            userDAO.deleteUser(selected);
            syncUsers();
            firstNameField.clear();
            lastNameField.clear();
            usernameField.clear();
            passwordField.clear();
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource("login-screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT));
    }
}