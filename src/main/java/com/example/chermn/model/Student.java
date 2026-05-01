package com.example.chermn.model;

public class Student extends Users {
    // vehicle
    private static int vehicleLevel;
    private static int animalLevel;
    private static int natureLevel;

    public Student(int id, String userName, String firstName, String lastName, String password, String schoolName, int vehicleLevel, int animalLevel, int natureLevel) {
        super(id, userName, firstName, lastName, password, schoolName);
        Student.vehicleLevel = vehicleLevel;
        Student.animalLevel = animalLevel;
        Student.natureLevel = natureLevel;
    }

    public int getVehicleLevel() {
        return vehicleLevel;
    }

    public int getAnimalLevel() {
        return animalLevel;
    }

    public int getNatureLevel() {
        return natureLevel;
    }

    public static void setVehicleLevel(int level) {
        vehicleLevel = level;
    }

    public static void setAnimalLevel(int level) {
        animalLevel = level;
    }

    public static void setNatureLevel(int level) {
        natureLevel = level;
    }


    @Override
    public String toString() {
        return super.toString() +
                ", vehicle category level: " + vehicleLevel +
                ", animal category level: " + animalLevel +
                ", nature category level: " + natureLevel +
                "}";
    }

}
