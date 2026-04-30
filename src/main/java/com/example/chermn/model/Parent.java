package com.example.chermn.model;

public class Parent extends Users {
    private String relationship;
    private int studentId;
    private String studentName;

    public Parent(int id, String username, String firstName, String lastName, String password,
                  String schoolName, String relationship, String studentName) {
        super(id, username, firstName, lastName, password, schoolName);
        this.relationship = relationship;
        this.studentName = studentName;
    }

    public String getRelationship() { return relationship; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
}