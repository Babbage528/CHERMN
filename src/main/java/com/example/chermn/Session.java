package com.example.chermn;

import com.example.chermn.model.Users;

/**
 * Stores session information for the currently logged‑in user.
 * <p>
 * This class provides static methods to set, retrieve, and clear
 * the active user session throughout the application lifecycle.
 */
public class Session {

    /** The currently logged‑in user, or {@code null} if no user is signed in. */
    private static Users currentUser;

    /**
     * Default constructor for Session.
     * Declared to satisfy Javadoc requirements; not used since this is a static utility class.
     */
    public Session() {}

    /**
     * Sets the current user for the session.
     *
     * @param user the user who has just logged in
     */
    public static void setCurrentUser(Users user) {
        currentUser = user;
    }

    /**
     * Returns the currently logged‑in user.
     *
     * @return the active {@link Users} object, or {@code null} if no user is logged in
     */
    public static Users getCurrentUser() {
        return currentUser;
    }

    /**
     * Clears the current user session.
     * Used when the user signs out.
     */
    public static void clearCurrentUser() {
        currentUser = null;
    }
}