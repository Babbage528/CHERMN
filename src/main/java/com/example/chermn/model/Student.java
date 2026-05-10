package com.example.chermn.model;

/**
 * A model class representing the student in the trivia application
 * with a username, first name, last name, password, school name,
 * an id that is auto-incremented, and three different trivia category levels.
 * Extends the {@code Users} class.
 */
public class Student extends Users {

    private int vehicleLevel;
    private int animalLevel;
    private int natureLevel;

    /**
     * Constructs a new Student with user details and category levels.
     * Calls the superclass constructor.
     * @param id the student's id.
     * @param userName the student's username.
     * @param firstName the student's first name.
     * @param lastName the student's last name.
     * @param password the student's password.
     * @param schoolName the student's school name.
     * @param vehicleLevel the student's vehicle level.
     * @param animalLevel the student's animal level.
     * @param natureLevel the student's nature level.
     */
    public Student(int id, String userName, String firstName, String lastName, String password, String schoolName, int vehicleLevel, int animalLevel, int natureLevel) {
        super(id, userName, firstName, lastName, password, schoolName);
        this.vehicleLevel = vehicleLevel;
        this.animalLevel = animalLevel;
        this.natureLevel = natureLevel;
    }

    /**
     * Returns the student's vehicle level.
     * @return the student's current vehicle level.
     */
    public int getVehicleLevel() {
        return vehicleLevel;
    }

    /**
     * Returns the student's animal level.
     * @return the student's current animal level.
     */
    public int getAnimalLevel() {
        return animalLevel;
    }

    /**
     * Returns the student's nature level.
     * @return the student's current nature level.
     */
    public int getNatureLevel() {
        return natureLevel;
    }

    /**
     * Sets the student's vehicle level.
     * @param level the student's vehicle level.
     */
    public void setVehicleLevel(int level) {
        vehicleLevel = level;
    }

    /**
     * Sets the student's animal level.
     * @param level the student's animal level.
     */
    public void setAnimalLevel(int level) {
        animalLevel = level;
    }

    /**
     * Sets the student's nature level.
     * @param level the student's nature level.
     */
    public void setNatureLevel(int level) {
        natureLevel = level;
    }


    /**
     * Converts the Student's details to a string format.
     * @return the student's details in a string sentence.
     */
    @Override
    public String toString() {
        return super.toString() +
                ", vehicle category level: " + vehicleLevel +
                ", animal category level: " + animalLevel +
                ", nature category level: " + natureLevel +
                "}";
    }

}
