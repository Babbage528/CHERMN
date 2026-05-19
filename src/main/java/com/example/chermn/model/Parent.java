package com.example.chermn.model;

/**
 Represents a parent user in the application.
 * Stores the parent's relationship to a student,
 * along with the corresponding student's name and ID.
 */
public class Parent extends Users {

    private String relationship;
    private int studentId;
    private String studentName;

    /**
     * Public constructor for the parent, takes the parent's id, username, first name, last name, password,
     * student's school name, relationship to student, and the student's full name
     * @param id the parent's id
     * @param username the parent's username
     * @param firstName the parent's first name
     * @param lastName the parent's last name
     * @param password the parent's password
     * @param schoolName the student's school name
     * @param relationship the parent's relationship to corresponding student
     * @param studentName the corresponding student's full name
     */
    public Parent(int id, String username, String firstName, String lastName, String password,
                  String schoolName, String relationship, String studentName) {
        super(id, username, firstName, lastName, password, schoolName);
        this.relationship = relationship;
        this.studentName = studentName;
    }

    /**
     * Returns the relationship of the parent to corresponding student
     * @return the relationship between parent and student
     */
    public String getRelationship() { return relationship; }

    /**
     * Returns the corresponding student's id to the parent
     * @return the corresponding student's id
     */
    public int getStudentId() { return studentId; }

    /**
     * Sets the corresponding student's id to the parent
     * @param studentId the corresponding student's id
     */
    public void setStudentId(int studentId) { this.studentId = studentId; }

    /**
     * Returns the corresponding student's full name to the parent
     * @return the corresponding student's full name
     */
    public String getStudentName() { return studentName; }

}