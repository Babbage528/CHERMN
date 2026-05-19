package com.example.chermn.model;

/**
 * Represents a student user in the application.
 * Intends to be registered with their quiz levels, name, password, school name
 * and username/email.
 */
public class Student extends Users {

    // trivia levels
    private static int vehicleLevel;
    private static int animalLevel;
    private static int natureLevel;

    /**
     * Public constructor for the student user. Takes the student's id, username, first name,
     * last name, password, school name, vehicle trivia level, animal vehicle level, and nature trivia level.
     * @param id the student's id
     * @param userName the student's username
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param password the student's password
     * @param schoolName  the student's school name
     * @param vehicleLevel the student's vehicle trivia level
     * @param animalLevel the student's animal trivia level
     * @param natureLevel the student's nature trivia level
     */
    public Student(int id, String userName, String firstName, String lastName, String password, String schoolName, int vehicleLevel, int animalLevel, int natureLevel) {
        super(id, userName, firstName, lastName, password, schoolName);
        Student.vehicleLevel = vehicleLevel;
        Student.animalLevel = animalLevel;
        Student.natureLevel = natureLevel;
    }

    /**
     * Returns the student's current vehicle trivia level
     * @return the student's current vehicle level
     */
    public int getVehicleLevel() {
        return vehicleLevel;
    }

    /**
     * Returns the student's current animal trivia level
     * @return the student's current animal level
     */
    public int getAnimalLevel() {
        return animalLevel;
    }

    /**
     * Returns the student's current nature trivia level
     * @return the student's current nature level
     */
    public int getNatureLevel() {
        return natureLevel;
    }

    /**
     * Sets the student's vehicle trivia level
     * @param level the student's updated vehicle trivia level
     */
    public static void setVehicleLevel(int level) {
        vehicleLevel = level;
    }

    /**
     * Sets the student's animal trivia level
     * @param level the student's updated animal trivia level
     */
    public static void setAnimalLevel(int level) {
        animalLevel = level;
    }

    /**
     * Sets the student's nature trivia level
     * @param level the student's updated nature trivia level
     */
    public static void setNatureLevel(int level) {
        natureLevel = level;
    }

    /**
     * Converts the student's first and last name into one string
     * @return the student's full name
     */
    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }

}
