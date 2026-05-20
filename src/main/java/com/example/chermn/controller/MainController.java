package com.example.chermn.controller;

import java.io.IOException;

import com.example.chermn.OnBoarding;
import com.example.chermn.SpeechHelper;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Controller for the admin user management screen.
 * <p>
 * Handles displaying, selecting, creating, editing, and deleting users.
 */
public class MainController extends BaseController{

    @FXML private ListView<Users> userListView;
    @FXML private TextField firstNameField, lastNameField, usernameField, schoolField;
    @FXML private PasswordField passwordField;

    /** Data access object for retrieving and modifying user records. */
    private UserDAO userDAO = new UserDAO();

    /** The user currently selected in the list for viewing or editing. */
    private Users currentUser;

    /**
     * Public constructor for the MainController class.
     */
    public MainController() {

    }

    /**
     * Populates all text fields with the details of the selected user.
     *
     * @param user the user whose details should be displayed
     */
    private void selectUser(Users user) {
        userListView.getSelectionModel().select(user);
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        schoolField.setText(user.getSchoolName());
        usernameField.setText(user.getUserName());
        passwordField.setText(user.getPassword());
    }

    /**
     * Creates a custom ListCell for displaying users in the ListView.
     * Each cell shows the user's full name and supports click selection.
     *
     * @param userListView the ListView requesting the cell
     * @return a configured ListCell for displaying user entries
     */
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

    /** Refreshes the ListView by reloading all users from the database. */
    private void syncUsers() {
        userListView.getItems().clear();
        userListView.getItems().addAll(userDAO.getAllUsers());
    }

    /**
     * Initializes the controller after the FXML is loaded.
     * Sets up the ListView cell factory and loads all users.
     */
    @FXML
    public void initialize() {
        userListView.setCellFactory(this::renderUserCell);
        syncUsers();
    }

    /**
     * Creates a new default user and adds it to the database.
     * Selects the new user and focuses the first name field for editing.
     */
    @FXML
    private void onAdd() {
        Users newUser = new Student(0, "newuser", "New", "User", "123", "QUT", 0, 0, 0);
        userDAO.createUser(newUser);
        syncUsers();
        selectUser(newUser);
        firstNameField.requestFocus();
    }

    /**
     * Saves edits made to the selected user's details.
     *
     * @throws Exception if the update operation fails
     */
    @FXML
    private void onEditConfirm() throws Exception {
        Users selected = userListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setFirstName(firstNameField.getText());
            selected.setLastName(lastNameField.getText());
            selected.setPassword(passwordField.getText());

            userDAO.updateUser(selected);
            syncUsers();
        }
    }

    /**
     * Deletes the currently selected user from the database
     * and clears the input fields.
     */
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

    /**
     * Logs the user out and returns to the login screen.
     *
     * @param event the logout button click event
     * @throws IOException if the login screen cannot be loaded
     */
    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource("login-screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT));
    }

    /**
     * Sets the current user and displays their details in the UI.
     *
     * @param user the user whose information should be shown
     */
    public void setUser(Users user) {
        this.currentUser = user;

        //shows the data in the UI
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        usernameField.setText(user.getUserName());
        schoolField.setText(user.getSchoolName());
    }
}