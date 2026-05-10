package com.example.chermn.model;

/**
 * A model class representing the teacher in the trivia application
 * with a username, first name, last name, password, school name,
 * and an id that is auto-incremented.
 * Extends the {@code Users} class.
 */
public class Teacher extends Users {
    public Teacher(int id, String username, String firstName, String lastName, String password, String schoolName) {
        super(id, username, firstName, lastName, password, schoolName);

    }
}
