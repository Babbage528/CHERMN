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
    public String getSchoolName() {
        return schoolName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null){

            throw new IllegalArgumentException("First name must be entered.");
        }
        else if (firstName.contains(" ")){
            throw new IllegalArgumentException("Please only enter your first name.");
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null){

            throw new IllegalArgumentException("Last name must be entered.");
        }
        else if (lastName.contains(" ")){
            throw new IllegalArgumentException("Please only enter your last name.");
        }

        this.lastName = lastName;
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
        if (schoolName == null){

            throw new IllegalArgumentException("School name must be entered.");
        }
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
}
