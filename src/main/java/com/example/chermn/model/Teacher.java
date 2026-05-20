package com.example.chermn.model;

/**
 * Represents a teacher user in the application.
 * Intends to be registered with their name, id, password and school name.
 */
public class Teacher extends Users {

    /**
     * Constructor for a new teacher profile.
     *
     * @param id The unique database identifier for teachers.
     * @param username The account username for logging in.
     * @param firstName The teacher first name.
     * @param lastName The teacher last name.
     * @param password The password for login.
     * @param schoolName The school belonging to the teacher.
     */
    public Teacher(int id, String username, String firstName, String lastName, String password, String schoolName) {
        super(id, username, firstName, lastName, password, schoolName);

    }
}
