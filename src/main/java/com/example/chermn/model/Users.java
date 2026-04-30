package com.example.chermn.model;

import java.util.Objects;

public class Users {
    // user id is auto-incremented
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String schoolName;


    // Public constructor for User
    public Users(int id, String userName, String firstName, String lastName, String password, String schoolName) {
        this.id = id;
        // calls the setters to ensure validation is upheld when constructing user
        setUsername(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setSchoolName(schoolName);
    }

    // Public constructor without id since it is auto-incremented
    public Users(String userName, String firstName, String lastName, String password, String schoolName)
    {
        // calls the setters to ensure validation is upheld
        setUsername(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setSchoolName(schoolName);
    }


    public int getid() {
        return id;
    }

    public String getUserName() {

        return userName;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {

        return password;
    }

    public String getSchoolName()
    {
        return schoolName;
    }

    public void setFirstName(String firstName) {
        // validate the first name, ensuring only letters are entered
        validateName(firstName, "First name");
        // formats the first name to ensure consistency
        this.firstName = formatName(firstName);
    }

    public void setLastName(String lastName) {
        // validate the last name, ensuring only letters are entered
        validateName(lastName, "Last name");
        // formats the last name to ensure consistency
        this.lastName = formatName(lastName);
    }

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

    public void setSchoolName(String schoolName)
    {
        validateName(schoolName, "School name");

        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "User Information{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


    public void setId(int autoIncrementedId) {
        this.id = autoIncrementedId;
    }


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

        if (Objects.equals(fieldName, "School name"))
        {
            // ensures that only letters or spaces are entered into the name fields
            if (!name.matches("^[a-zA-Z ]+$")) {
                throw new IllegalArgumentException(fieldName + " must contain only letters or spaces.");
            }
        }
        else
        {
            // ensures that only letters are entered into the name fields
            if (!name.matches("^[a-zA-Z]+$")) {
                throw new IllegalArgumentException(fieldName + " must contain only letters.");
            }
        }

    }

}
