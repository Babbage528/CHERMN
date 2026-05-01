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

        // using setters to ensure validation is passing for each field
        setUsername(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setSchoolName(schoolName);
    }

    // Public constructor without id since it is auto-incremented
    public Users(String userName, String firstName, String lastName, String password, String schoolName)
    {
        // using setters to ensure user validation is passing for each field
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

    // sets the first name, ensuring it follows validation rules
    public void setFirstName(String firstName) {
        UserValidation.validateFirstLastName(firstName, "First name");
        this.firstName = UserValidation.formatName(firstName);
    }

    // sets the last name, ensuring follows validation rules
    public void setLastName(String lastName) {
        UserValidation.validateFirstLastName(lastName, "Last name");
        this.lastName = UserValidation.formatName(lastName);
    }

    // sets the password, ensuring follows validation rules
    public void setPassword(String password)
    {
        UserValidation.validatePassword(password);
        this.password = password;
    }

    // sets the school name following validation rules
    public void setSchoolName(String schoolName)
    {
        UserValidation.validateSchoolName(schoolName);
        this.schoolName = schoolName;
    }

    // converts user's information into a string
    @Override
    public String toString() {
        return "User Information{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", first name='" + firstName + '\'' +
                ", last name='" + lastName + '\'' +
                ", school name ='" + schoolName + '\'' +
                '}';
    }

    // sets the ID to be the auto incremented id
    public void setId(int autoIncrementedId) {
        this.id = autoIncrementedId;
    }

    // sets username field, using validation rules listed in UserValidation
    public void setUsername(String username) {
        UserValidation.validateUsername(username);
        this.userName = username.trim();

    }



}
