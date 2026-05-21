package com.example.chermn.controller;

import java.util.List;

import com.example.chermn.Session;
import com.example.chermn.dao.QuizAttemptDAO;
import com.example.chermn.dao.UserDAO;
import com.example.chermn.model.QuizAttempt;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * Controller for the student details screen.
 */
public class StudentDetailsController extends BaseController{

    @FXML private ListView<Student> studentList;
    @FXML private ListView<String> attemptList;

    @FXML private VBox detailsBox;

    private UserDAO userDAO = new UserDAO();
    private QuizAttemptDAO quizAttemptDAO = new QuizAttemptDAO();

    @FXML private Label animalLevel;
    @FXML private Label natureLevel;
    @FXML private Label vehicleLevel;


    /**
     * Initialises the controller before the screen is displayed.
     * <p>
     * Retrieves the currently logged-in user and loads all students who belong
     * to the same school. These students are added to the student list on the left
     * side of the screen.
     * <p>
     * A selection listener is also attached to the list so that when a student is
     * clicked, their progress details are displayed in the side panel.
     * The details panel is hidden until a valid student selection is made.
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
                detailsBox.setVisible(true);
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