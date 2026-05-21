package com.example.chermn.controller;

import java.util.List;

import com.example.chermn.Session;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Controller for the student details screen.
 */
public class StudentDetailsController extends BaseController{

    @FXML private ListView<Student> studentList;

    private UserDAO userDAO = new UserDAO();

    @FXML private Label animalLevel;
    @FXML private Label natureLevel;
    @FXML private Label vehicleLevel;


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
        // When a student is clicked, update the details panel
        studentList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                displayStudentDetails(newVal);
            }
        });
    }

    /**
     * Updates the student details panel with the selected student's progress.
     * <p>
     * This method is called when a student is selected from the list. It retrieves
     * the student's current progress values (such as animal, nature, and vehicle
     * levels) and displays them in the corresponding labels on the UI.
     *
     * @param student the selected {@link Student} whose progress details should be shown;
     *                must not be null
     */
    private void displayStudentDetails(Student student) {
        animalLevel.setText(String.valueOf(student.getAnimalLevel()));
        natureLevel.setText(String.valueOf(student.getNatureLevel()));
        vehicleLevel.setText(String.valueOf(student.getVehicleLevel()));
    }

    /**
     * Public constructor of student detilas controller class.
     */
    public StudentDetailsController() {
    }
}