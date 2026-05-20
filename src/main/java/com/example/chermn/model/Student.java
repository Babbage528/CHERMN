package com.example.chermn.model;

/**
 * Represents a student user in the application.
 * <p>
 * A student has individual quiz progress levels for each category
 * (vehicle, animal, and nature), along with their standard user details.
 */
public class Student extends Users {

    /** The student's current level in the vehicle category. */
    private static int vehicleLevel;

    /** The student's current level in the animal category. */
    private static int animalLevel;

    /** The student's current level in the nature category. */
    private static int natureLevel;

    /**
     * Creates a new Student user with associated quiz progress levels.
     *
     * @param id the unique user ID
     * @param userName the student's username or email
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param password the student's password
     * @param schoolName the name of the student's school
     * @param vehicleLevel the student's vehicle category level
     * @param animalLevel the student's animal category level
     * @param natureLevel the student's nature category level
     */
    public Student(int id, String userName, String firstName, String lastName,
                   String password, String schoolName,
                   int vehicleLevel, int animalLevel, int natureLevel) {

        super(id, userName, firstName, lastName, password, schoolName);
        Student.vehicleLevel = vehicleLevel;
        Student.animalLevel = animalLevel;
        Student.natureLevel = natureLevel;
    }

    /**
     * Returns the student's vehicle category level.
     *
     * @return the vehicle level
     */
    public int getVehicleLevel() {
        return vehicleLevel;
    }

    /**
     * Returns the student's animal category level.
     *
     * @return the animal level
     */
    public int getAnimalLevel() {
        return animalLevel;
    }

    /**
     * Returns the student's nature category level.
     *
     * @return the nature level
     */
    public int getNatureLevel() {
        return natureLevel;
    }

    /**
     * Sets the student's vehicle category level.
     *
     * @param level the new vehicle level
     */
    public static void setVehicleLevel(int level) {
        vehicleLevel = level;
    }

    /**
     * Sets the student's animal category level.
     *
     * @param level the new animal level
     */
    public static void setAnimalLevel(int level) {
        animalLevel = level;
    }

    /**
     * Sets the student's nature category level.
     *
     * @param level the new nature level
     */
    public static void setNatureLevel(int level) {
        natureLevel = level;
    }

    /**
     * Returns the student's full name for display purposes.
     *
     * @return the student's first and last name
     */
    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }

    /**
     * Returns a detailed string representation of the student,
     * including their quiz progress levels.
     *
     * @return a debug string containing student details and levels
     */
    public String debugString() {
        return super.toString() +
                ", vehicle category level: " + vehicleLevel +
                ", animal category level: " + animalLevel +
                ", nature category level: " + natureLevel +
                "}";
    }
}