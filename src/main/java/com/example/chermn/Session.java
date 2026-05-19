package com.example.chermn;

import com.example.chermn.controller.SettingsController;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Users;

/**
 * Class for conveniently storing session information.
 * Stores the current session information regarding all current user information
 */
public class Session {

    // the current logged-in user
    private static Users currentUser;

    // logs whether the instructions have been shown yet
    private static boolean instructionsShown = false;

    /**
     * Sets the current user for the session as the user inputted
     * @param user the logged-in user
     */
    public static void setCurrentUser(Users user) {

        currentUser = user;
    }

    /**
     * Gets the current user that is logged in
     * @return the current user
     */
    public static Users getCurrentUser() {
        return currentUser;
    }

    /**
     * Clears the current user from the session memory when logging out
     */
    public static void clearCurrentUser() {
        currentUser = null;
    }

    /**
     * Returns whether the games instructions have been shown yet
     * @return a boolean dictating whether the game instructions have been shown
     */
    public static boolean isInstructionsShown() {
        return instructionsShown;
    }

    /**
     * Sets the instructions shown boolean
     * @param shown the boolean denoting whether the instructions have been shown
     */
    public static void setInstructionsShown(boolean shown) {
        instructionsShown = shown;
    }


}
