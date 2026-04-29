package com.example.chermn.model;

public class Student extends Users {
    // vehicle
    private int vehicleLevel;
    private int animalLevel;
    private int natureLevel;

    public Student(int id, String userName, String firstName, String lastName, String password, String schoolName, int vechileLevel, int animalLevel, int natureLevel) {
        super(id, userName, firstName, lastName, password, schoolName);
        this.vehicleLevel = vechileLevel;
        this.animalLevel = animalLevel;
        this.natureLevel = natureLevel;
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

    public void setVehicleLevel(int level) {
        vehicleLevel = level;
    }

    public void setAnimalLevel(int level) {
        animalLevel = level;
    }

    public void setNatureLevel(int level) {
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
