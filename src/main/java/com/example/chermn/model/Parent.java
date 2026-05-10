package com.example.chermn.model;

/**
 * A model class representing the parent in the trivia application
 * with a username, first name, last name, password, school name,
 * an id that is auto-incremented, a relationship to the student, the student id,
 * and the student name.
 * Extends the {@code Users} class.
 */
public class Parent extends Users {

    private String relationship;
    private int studentId;
    private String studentName;

    /**
     * Constructs a new Parent with user details, student's school name, relationship to student,
     * and the student's name.
     * Calls the superclass constructor.
     * @param id the parent's id.
     * @param username the parent's username.
     * @param firstName the parent's first name.
     * @param lastName the parent's last name.
     * @param password the parent's password.
     * @param schoolName the student's school name.
     * @param relationship the parent's relationship to the student.
     * @param studentName the student's name.
     */
    public Parent(int id, String username, String firstName, String lastName, String password,
                  String schoolName, String relationship, String studentName) {
        super(id, username, firstName, lastName, password, schoolName);
        this.relationship = relationship;
        this.studentName = studentName;
    }

    /**
     * Returns the parent's relationship to the linked student.
     * @return the parent's relationship to the linked student.
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * Returns the linked student's id.
     * @return the linked student's id.
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Sets the student's id.
     * @param studentId the student's id.
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Returns the linked student's name.
     * @return the linked student's name.
     */
    public String getStudentName() {
        return studentName;
    }
}