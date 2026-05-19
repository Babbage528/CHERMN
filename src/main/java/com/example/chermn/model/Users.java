package com.example.chermn.model;

import java.util.Objects;

/**
 * Represents a base user in the application.
 * Acts as a base for other user classes to extend from.
 */
public class Users {

    // user id is auto-incremented
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String schoolName;


    // Public constructor for User

    /**
     * Public constructor for the user. Takes the user's id, username, first name, last name,
     * password and school name.
     * @param id the user's id
     * @param userName the user's username
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param password the user's password
     * @param schoolName the user's school name
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
     * Public constructor for the User without the id field since it is auto-incremented.
     * Takes the user's username, first name, last name, password and school name.
     * @param userName the user's username
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param password the user's password
     * @param schoolName the user's school name
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
     * Returns the user's id
     * @return the user's id
     */
    public int getid() {
        return id;
    }

    /**
     * Returns the user's username
     * @return the user's username
     */
    public String getUserName() {

        return userName;
    }

    /**
     * Returns the user's first name
     * @return the user's first name
     */
    public String getFirstName() {

        return firstName;
    }

    /**
     * Returns the user's last name
     * @return the user's last name
     */
    public String getLastName() {

        return lastName;
    }

    /**
     * Returns the user's password
     * @return the user's password
     */
    public String getPassword() {

        return password;
    }

    /**
     * Returns the user's school name
     * @return the user's school name
     */
    public String getSchoolName()
    {
        return schoolName;
    }

    /**
     * Sets the user's first name.
     * Validates ensuring that only letters are entered, and format to ensure consistency.
     * @param firstName the user's updated first name
     */
    public void setFirstName(String firstName) {
        // validate the first name, ensuring only letters are entered
        validateName(firstName, "First name");
        // formats the first name to ensure consistency
        this.firstName = formatName(firstName);
    }

    /**
     * Sets the user's last name.
     * Validates ensuring that only letters are entered, and format to ensure consistency.
     * @param lastName the user's updated last name
     */
    public void setLastName(String lastName) {
        // validate the last name, ensuring only letters are entered
        validateName(lastName, "Last name");
        // formats the last name to ensure consistency
        this.lastName = formatName(lastName);
    }

    /**
     * Sets the user's password.
     * Validates ensuring that the password has a minimum of 5 characters.
     * @param password the user's updated password
     */
    public void setPassword(String password)
    {
        if (password == null || password.length() < 5)
        {
            throw new IllegalArgumentException("Password must be a minimum of 5 chars");
        }
        else if (password.length() < 5)
        {
            throw new IllegalArgumentException("Password must be at least 5 characters");
        }
        this.password = password;
    }

    /**
     * Sets the user's school name.
     * Validates and formats the school name.
     * @param schoolName the user's school name
     */
    public void setSchoolName(String schoolName)
    {
        validateName(schoolName, "School name");

        this.schoolName = formatSchoolName(schoolName);
    }

    /**
     * Converts the user's details into a string format.
     * @return the user's details as a string
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

    /**
     * Sets the user's id.
     * @param autoIncrementedId the user's autoincrement id
     */
    public void setId(int autoIncrementedId) {

        this.id = autoIncrementedId;
    }


    /**
     * Sets the user's username.
     * Validates to ensure that it isn't empty.
     * @param username the user's username.
     */
    public void setUsername(String username) {
        if ((Objects.equals(username, "")) || (username == null))
        {
            throw new IllegalArgumentException("Username must not be left blank");
        }
        else
        {
            this.userName = username;
        }

    }

    // a private method to format the name into standard format
    private String formatName(String name) {
        name = name.trim().toLowerCase();

        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }


    // validate name method that uses regex
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

    // since school name can have spaces, and hence multiple words
    // have separte format method
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

}
