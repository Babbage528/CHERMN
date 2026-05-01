package com.example.chermn.model;


// contains all of the validation methods for users information/class
// validation classes return an error message to be displayed to the user
// otherwise null if no error
public class UserValidation {

    // validate first and last name method that uses regex - returns an error message otherwise null if no error
    public static String validateFirstLastName(String name, String fieldName) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be empty.");
        }

        // ensures that only letters are entered into the name fields
        if (!name.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException(fieldName + " must contain only letters.");
        }
        // formats the name returned
        return formatName(name);
    }

    // format the name to generic form
    public static String formatName(String name) {
        // formats the name into standard format
        name = name.trim().toLowerCase();

        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    // format the school name to generic form
    public static String formatSchoolName(String name) {
        String[] splitString;
        // splits the string at each space and adds to array
        splitString = name.split(" ");

        // formatted string
        String formattedString = "";

        // for each word in the split string
        for (String word : splitString) {
            if (!word.isEmpty())
            {
                // adds the formatted word to the formatted string
                formattedString = formattedString + formatName(word) + " ";
            }
        }

        return formattedString.trim();
    }


        // validate school name using regex
        public static void validateSchoolName(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("School name must not be empty.");
            }

            // ensures that only letters or spaces are entered into the name fields
            if (!name.matches("^[a-zA-Z ]+$")) {
                throw new IllegalArgumentException("School name must contain only letters or spaces.");
            }
     }


    // password validation - ensuring minimum of 5 characters
    public static void validatePassword(String password) {
        if (password == null || password.length() < 5)
        {
            throw new IllegalArgumentException("Password must be a minimum of 5 characters");
        }
    }


    // username validation - ensuring minimum of 5 characters
    public static void validateUsername(String username) {
        if (username == null || username.length() < 5)
        {
            throw new IllegalArgumentException("Username must be a minimum of 5 characters");
        }
    }



}
