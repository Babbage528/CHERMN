package com.example.chermn.model;

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
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.schoolName = schoolName;
    }

    // Public constructor without id since it is auto-incremented
    public Users(String userName, String firstName, String lastName, String password, String schoolName)
    {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.schoolName = schoolName;
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
