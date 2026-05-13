package com.example.chermn.controller;

import java.util.List;

import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class StudentDetailsController {

    @FXML
    private ListView<Student> studentList;

    private UserDAO userDAO = new UserDAO();

    @FXML
    public void initialize() {
        Users currentUser = Session.getCurrentUser();
        List<Student> students = userDAO.getStudentsBySchool(currentUser.getSchoolName());
        studentList.getItems().addAll(students);
    }
}
