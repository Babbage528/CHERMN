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
        this.firstName = formatToTitleCase(firstName);
        this.lastName = formatToTitleCase(lastName);
        this.password = password;
        this.schoolName = formatToTitleCase(schoolName);
    }

    // Public constructor without id since it is auto-incremented
    public Users(String userName, String firstName, String lastName, String password, String schoolName)
    {
        this.userName = userName;
        this.firstName = formatToTitleCase(firstName);
        this.lastName = formatToTitleCase(lastName);
        this.password = password;
        this.schoolName = formatToTitleCase(schoolName);
    }

    private String formatToTitleCase(String input) {
        if (input == null || input.isEmpty()) return input;

        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                capitalizeNext = true;
                result.append(c);
            } else if (capitalizeNext) {
                result.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString().trim();
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
        else if (firstName.contains("0") || firstName.contains("1") || firstName.contains("2") || firstName.contains("3") || firstName.contains("4") || firstName.contains("5") || firstName.contains("6") || firstName.contains("7") || firstName.contains("8") || firstName.contains("9")) {
            throw new IllegalArgumentException("Please ensure first name does not contain digits.");
        }
        else if (firstName.contains("!") || firstName.contains("@") || firstName.contains("#") || firstName.contains("$") || firstName.contains("%") || firstName.contains("^") || firstName.contains("&") || firstName.contains("*") || firstName.contains("(") || firstName.contains(")") || firstName.contains("-") || firstName.contains("_") || firstName.contains("+") || firstName.contains("=") || firstName.contains("`") || firstName.contains("~") || firstName.contains(",") || firstName.contains("<") || firstName.contains(".") || firstName.contains(">") || firstName.contains("/") || firstName.contains("?") || firstName.contains(":") || firstName.contains(";") || firstName.contains("'") || firstName.contains("[") || firstName.contains("]") || firstName.contains("{") || firstName.contains("}")) {
            throw new IllegalArgumentException("Please ensure first name does not special characters.");
        }

        this.firstName = formatToTitleCase(firstName);
    }

    public void setLastName(String lastName) {
        if (lastName == null){

            throw new IllegalArgumentException("Last name must be entered.");
        }
        else if (lastName.contains(" ")){
            throw new IllegalArgumentException("Please only enter your last name.");
        }
        else if (lastName.contains("0") || lastName.contains("1") || lastName.contains("2") || lastName.contains("3") || lastName.contains("4") || lastName.contains("5") || lastName.contains("6") || lastName.contains("7") || lastName.contains("8") || lastName.contains("9")) {
            throw new IllegalArgumentException("Please ensure last name does not contain digits.");
        }
        else if (lastName.contains("!") || lastName.contains("@") || lastName.contains("#") || lastName.contains("$") || lastName.contains("%") || lastName.contains("^") || lastName.contains("&") || lastName.contains("*") || lastName.contains("(") || lastName.contains(")") || lastName.contains("-") || lastName.contains("_") || lastName.contains("+") || lastName.contains("=") || lastName.contains("`") || lastName.contains("~") || lastName.contains(",") || lastName.contains("<") || lastName.contains(".") || lastName.contains(">") || lastName.contains("/") || lastName.contains("?") || lastName.contains(":") || lastName.contains(";") || lastName.contains("'") || lastName.contains("[") || lastName.contains("]") || lastName.contains("{") || lastName.contains("}")) {
            throw new IllegalArgumentException("Please ensure last name does not special characters.");
        }

        this.lastName = formatToTitleCase(lastName);
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
        else if (schoolName.contains("0") || schoolName.contains("1") || schoolName.contains("2") || schoolName.contains("3") || schoolName.contains("4") || schoolName.contains("5") || schoolName.contains("6") || schoolName.contains("7") || schoolName.contains("8") || schoolName.contains("9")) {
            throw new IllegalArgumentException("Please ensure school name does not contain digits.");
        }

        this.schoolName = formatToTitleCase(schoolName);


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
