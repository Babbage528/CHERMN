package com.example.chermn;

import com.example.chermn.controller.SettingsController;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Users;

// stores the current session information regarding all current user information
public class Session {

    // the current logged-in user
    private static Users currentUser;

    // the database
    private static UserDAO userDAO = new UserDAO();


    // sets the current user for the session
    public static void setCurrentUser(Users user) {
        currentUser = user;
    }

    // gets the current user for the session
    public static Users getCurrentUser() {
        return currentUser;
    }

    // clears the current user (for signing out)
    public static void clearCurrentUser() {
        currentUser = null;
    }


}
