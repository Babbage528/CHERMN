package com.example.chermn.model;

public class Users {
    // user id is auto-incremented
    private int id;
    private String firstName;
    private String lastName;
    private String password;

    // Public constructor without id since it is auto-incremented
    public Users(String firstName, String lastName, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
