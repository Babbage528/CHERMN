package com.example.chermn.controller;

import com.example.chermn.AlertHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 * Controller for updating teacher password.
 */
public class ChangeTeacherPasswordController extends TeacherParentHomescreenController{


    @FXML
    private PasswordField reconfirmPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private Button updatePasswordButton;

    @FXML
    private void updatePasswordButtonClick() throws Exception {

        // collect the data written in the text fields
        String newPassword = newPasswordField.getText().trim();
        String reconfirmedPassword = reconfirmPasswordField.getText().trim();

        // check whether the two passwords match
        if (!newPassword.equals(reconfirmedPassword))
        {
            // error text appears on the screen for the user
            AlertHelper.showError("Unable to update password", "Passwords must match!");
        }
        else if (newPassword.length() < 5){
            // error text appears on the screen for the user
            AlertHelper.showError("Unable to update password", "Password must be a minimum of 5 characters");
        }
        else {
            // updates the password in the current users details
            updatePasswordDetails(newPassword);

            // updates the user in the database
            userDAO.updateUser(user);

            // alerts user that password has been changed
            AlertHelper.showSuccess( "Password Updated!", "Your password has been updated");

            // clears the password fields
            newPasswordField.clear();
            reconfirmPasswordField.clear();


        }
    }

    private void updatePasswordDetails(String newPassword) throws Exception {

        if (newPassword != null && !newPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
        }
    }

}
