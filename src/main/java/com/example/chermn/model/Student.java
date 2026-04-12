package com.example.chermn.model;

public class Student extends Users {
    private int vechileLevel;
    private int animalLevel;
    private int natureLevel;

    public Student(int id, String userName, String firstName, String lastName, String password, String schoolName, int vechileLevel, int animalLevel, int natureLevel)
    {
        super(id, userName, firstName, lastName, password, schoolName);
        this.vechileLevel = vechileLevel;
        this.animalLevel = animalLevel;
        this.natureLevel = natureLevel;
    }

    public int getVechileLevel() {
        return vechileLevel;
    }

    public int getAnimalLevel() {
        return animalLevel;
    }

    public int getNatureLevel() {
        return natureLevel;
    }

    public void setVechileLevel(int level) {
        vechileLevel = level;
    }

    public void setAnimalLevel(int level) {
        animalLevel = level;
    }

    public void setNatureLevel(int level) {
        natureLevel = level;
    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + getid() +
                ", username='" + getUserName() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                '}';
    }

}
