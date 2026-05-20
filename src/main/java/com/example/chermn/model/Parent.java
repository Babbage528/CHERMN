package com.example.chermn.model;

/**
 * Represents a Parent user in the system.
 * <p>
 * A Parent account is linked to a specific student and includes
 * relationship information used for account association.
 */
public class Parent extends Users {

    /** The relationship of the parent to the student (e.g., Mother, Father). */
    private String relationship;

    /** The full name of the student associated with this parent. */
    private String studentName;

    /** The ID of the student associated with this parent. */
    private Integer studentId;

    /**
     * Creates a new Parent user.
     *
     * @param id the unique user ID
     * @param email the parent's email address (used as username)
     * @param firstName the parent's first name
     * @param lastName the parent's last name
     * @param password the parent's password
     * @param schoolName the school associated with the student
     * @param relationship the parent's relationship to the student
     * @param studentName the full name of the associated student
     */
    public Parent(int id, String email, String firstName, String lastName,
                  String password, String schoolName, String relationship,
                  String studentName) {
        super(id, email, firstName, lastName, password, schoolName);
        this.relationship = relationship;
        this.studentName = studentName;
    }

    /**
     * Returns the parent's relationship to the student.
     *
     * @return the relationship label
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * Sets the parent's relationship to the student.
     *
     * @param relationship the relationship label
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    /**
     * Returns the full name of the associated student.
     *
     * @return the student's name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets the full name of the associated student.
     *
     * @param studentName the student's name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Returns the ID of the associated student.
     *
     * @return the student ID
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * Sets the ID of the associated student.
     *
     * @param studentId the student ID
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}