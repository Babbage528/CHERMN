package com.example.chermn.model;

import java.util.Objects;

/**
 * A model class representing the user in the trivia application
 * with a username, first name, last name, password, school name,
 * and an id that is auto-incremented.
 */
public class Users {

    // user id is auto-incremented
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String schoolName;


    /**
     * Constructs a new User with the specified id, username
     * first name, last name, password, and school name.
     * Ensures that the inputted values are validated by calling
     * the setters to construct the User.
     * @param id the id of the user.
     * @param userName the username of the user.
     * @param firstName the first name of the user.
     * @param lastName the last name of the user.
     * @param password the password of the user.
     * @param schoolName the user's school name.
     */
    public Users(int id, String userName, String firstName, String lastName, String password, String schoolName) {
        this.id = id;
        // calls the setters to ensure validation is upheld when constructing user
        setUsername(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setSchoolName(schoolName);
    }


    /**
     * Constructs a new User with username, first name,
     * last name, password, and school name.
     * Leaves out the id since it is auto-incremented.
     * Ensures that the inputted values are validated by calling
     * the setters to construct the User.
     * @param userName the username of the user.
     * @param firstName the first name of the user.
     * @param lastName the last name of the user.
     * @param password the password of the user.
     * @param schoolName the user's school name.
     */
    public Users(String userName, String firstName, String lastName, String password, String schoolName)
    {
        // calls the setters to ensure validation is upheld
        setUsername(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setSchoolName(schoolName);
    }


    /**
     * Returns for the user's id.
     * @return the user's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns for the user's username.
     * @return the user's username.
     */
    public String getUserName() {

        return userName;
    }

    /**
     * Returns for the user's first name.
     * @return the user's first name.
     */
    public String getFirstName() {

        return firstName;
    }

    /**
     * Returns for the user's last name.
     * @return the user's last name.
     */
    public String getLastName() {

        return lastName;
    }

    /**
     * Returns for the user's password.
     * @return the user's password.
     */
    public String getPassword() {

        return password;
    }

    /**
     * Returns for the user's school name.
     * @return the user's school name.
     */
    public String getSchoolName()
    {

        return schoolName;
    }

    /**
     * Sets the user's first name after validating the input.
     * @param firstName the new first name for the user.
     */
    public void setFirstName(String firstName) {
        // validate the first name, ensuring only letters are entered
        validateName(firstName, "First name");
        // formats the first name to ensure consistency
        this.firstName = formatName(firstName);
    }

    /**
     * Sets the user's last name after validating the input.
     * @param lastName the new last name for the user.
     */
    public void setLastName(String lastName) {
        // validate the last name, ensuring only letters are entered
        validateName(lastName, "Last name");
        // formats the last name to ensure consistency
        this.lastName = formatName(lastName);
    }

    /**
     * Sets the user's password after validating the input.
     * @param password the new password for the user.
     */
    public void setPassword(String password)
    {
        if (password == null || password.length() < 5)
        {
            throw new IllegalArgumentException("Password must be a minimum of 5 chars");
        }
        this.password = password;
    }

    /**
     * Sets for the user's school name after validating the input.
     * @param schoolName the new name for the user.
     */
    public void setSchoolName(String schoolName)
    {
        validateName(schoolName, "School name");

        this.schoolName = formatSchoolName(schoolName);
    }


    /**
     * Sets the user's id, which is auto-incremented.
     * @param autoIncrementedId the new name for the user.
     */
    public void setId(int autoIncrementedId) {

        this.id = autoIncrementedId;
    }


    /**
     * Sets for the user's username after validating the input.
     * @param username the new username for the user.
     */
    public void setUsername(String username) {
        if ( (username == null) || username.trim().isEmpty())
        {
            throw new IllegalArgumentException("Username must not be left blank");
        }
        else
        {
            this.userName = username;
        }

    }


    /**
     * Formats the inputted name into standard format which has the first character
     * uppercased, with the rest of the name lowercase.
     * @param name the user's inputted name.
     * @return the user's formatted name.
     */
    private String formatName(String name) {
        name = name.trim().toLowerCase();

        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }


    /**
     * Validates the name inputs using regex to follow standard naming conventions,
     * depending on what name was entered.
     * Throws an {@link IllegalArgumentException} if validation for the name fails.
     * @param name the user's inputted name.
     * @param fieldName the field name to be displayed of the inputted name.
     */
    private void validateName(String name, String fieldName) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be empty.");
        }
        // checks whether the school name was entered
        if (Objects.equals(fieldName, "School name"))
        {
            // ensures that only letters or spaces are entered into the name fields
            if (!name.matches("^[a-zA-Z ]+$")) {
                throw new IllegalArgumentException(fieldName + " must contain only letters or spaces.");
            }
        }
        else // first or last name
        {
            // ensures that only letters are entered into the name fields
            if (!name.matches("^[a-zA-Z]+$")) {
                throw new IllegalArgumentException(fieldName + " must contain only letters.");
            }
        }

    }

    /**
     * Formats the school name inputted to allow multiple words.
     * Each word is converted to standard name form, with each word starts with
     * a capital letter, and the rest lowercase.
     * @param schoolName the user's inputted school name.
     * @return the formatted school name.
     */
    private String formatSchoolName(String schoolName) {

        // first split the schoolName into the different words stored in the array
        String[] splitStringArray = schoolName.split(" ");

        // the formatted string
        String formattedString = "";

        // iterate through the splitString array and format individual words
        // since school name, each word should be capitalised
        for(String splitString : splitStringArray)
        {
            // adds the formatted section to the string with a blank space after
            formattedString = formattedString + formatName(splitString) + " ";
        }

        // trims the final string before returning
        return formattedString.trim();

    }

    /**
     * Converts the User's details to a string format.
     * @return the user's details in a string sentence.
     */
    @Override
    public String toString() {
        return "User Information{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
