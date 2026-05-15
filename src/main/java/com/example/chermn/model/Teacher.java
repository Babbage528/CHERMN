package com.example.chermn.model;

/**
 * Represents a teacher user in the application.
 * Intends to be registered with their name, id, password and school name.
 */
public class Teacher extends Users {
    public Teacher(int id, String username, String firstName, String lastName, String password, String schoolName) {
        super(id, username, firstName, lastName, password, schoolName);

    }
}
