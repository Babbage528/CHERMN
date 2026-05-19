package com.example.chermn.controller;

import com.example.chermn.AlertHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 * Controller for the change user password screen.
 * Handles the functionality for updating the user's password.
 */
public class ChangePasswordController extends ProfileController {

    @FXML
    private PasswordField reconfirmPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private Button updatePasswordButton;

    /**
     * Handles the update password button click event.
     * Collects the data from the text fields, and checks that the two passwords entered match.
     * Updates the user's password in the database, and clears all text fields on screen.
     */
    @FXML
    private void updatePasswordButtonClick() {

        // collect the data written in the text fields
        String newPassword = newPasswordField.getText().trim();
        String reconfirmedPassword = reconfirmPasswordField.getText().trim();

        // check whether the two passwords match
        if (!newPassword.equals(reconfirmedPassword))
        {
            // error text appears on the screen for the user
            AlertHelper.showError("Unable to update password", "Passwords must match!");
        }
        else if (newPassword.length() < 5) {
            // error text appears on the screen for the user
            AlertHelper.showError("Unable to update password", "Password must be 5 characters or longer");
        }
        else {
            // updates the password in the current users details
            updatePasswordDetails(newPassword);

            // updates the user in the database
            userDAO.updateUser(user);

            // alerts user that password has been changed
            AlertHelper.showSuccess("Password Updated!", "Your password has been updated");

            // clears the password fields
            newPasswordField.clear();
            reconfirmPasswordField.clear();
        }
    }

    /**
     * Handles updating the password details for the current user.
     * Checks to ensure that the new password doesn't match the password currently
     * stored, before updating.
     * @param newPassword the new password that the user has entered
     */
    private void updatePasswordDetails(String newPassword) {

        if (newPassword != null && !newPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
        }
    }

    /**
     * Public constructor of change password controller class.
     */
    public ChangePasswordController() {
    }

}
