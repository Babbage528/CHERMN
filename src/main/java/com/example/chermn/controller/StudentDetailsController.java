package com.example.chermn.controller;

import java.util.List;

import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * Controller for the student details screen.
 */
public class StudentDetailsController extends BaseController{

    private UpdateUserDetailsController controller;

    @FXML
    private ListView<Student> studentList;

    private UserDAO userDAO = new UserDAO();

    /**
     * Initialises the controller before the screen is displayed.
     * Gets the current logged-in user, and retrieves all the student's
     * with the same school name.
     * Adds all the students to the list.
     */
    @FXML
    public void initialize() {
        Users currentUser = Session.getCurrentUser();
        List<Student> students = userDAO.getStudentsBySchool(currentUser.getSchoolName());
        studentList.getItems().addAll(students);
    }

    /**
     * Public constructor of student detilas controller class.
     */
    public StudentDetailsController() {
    }
}