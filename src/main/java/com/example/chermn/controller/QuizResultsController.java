package com.example.chermn.controller;

import com.example.chermn.controller.QuizQuestionsController;
import com.example.chermn.controller.HomepageController;
import com.example.chermn.Session;
import com.example.chermn.QuizBegin;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Quiz controller for quiz results.
 * This class acts on the results from the previous quiz question controller.
 * Controls the computation and presentation of the user's results and level.
 */
public class QuizResultsController extends BaseController {

    @FXML
    private Label congratsLabel;

    @FXML
    private Label resultsLabel;

    @FXML
    private Button returnToHomepageButton;

    @FXML
    private Button beginQuizButton;

    /**
     * Default constructor for the QuizResultsController.
     * Required by JavaFX for controller instantiation.
     *
     * @throws JSONException if JSON processing fails during setup
     */
    public QuizResultsController() throws JSONException {
    }

    /**
     * Returns the user to the homepage screen.
     * Loads the homepage FXML and updates the current stage.
     *
     * @throws IOException if the homepage FXML cannot be loaded
     */
    @FXML
    protected void returnToHomepageButtonClick() throws  IOException{
        Stage stage = (Stage) returnToHomepageButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(QuizBegin.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), QuizBegin.WIDTH, QuizBegin.HEIGHT);
        stage.setScene(scene);
    }

    /** The user's quiz score expressed as a percentage (0–100). */
    int percentageScore = (QuizQuestionsController.score * 10);

    /** The user currently logged into the session. */
    Users currentStudent = Session.getCurrentUser();

    /** Strongly typed reference to the current user once validated as a Student. */
    Student student;

    /**
     * Retrieves and validates the current session user as a Student.
     * Throws an exception if the logged-in user is not a Student.
     */
    public void setCurrentUser() {
        // check whether the user is a student
        if (currentStudent instanceof Student) {
            student = (Student) currentStudent;
        }
        else {
            throw new IllegalArgumentException("Quiz requires a Student user");
        }

    }

    /**
     * Initialises the results screen.
     * <p>
     * Displays the user's score, determines pass/fail status, and updates
     * the student's level progression based on the quiz category completed.
     */
    public void initialize() {
        if (percentageScore >= 80) {
            resultsLabel.setText("You passed with " + percentageScore + "%");
            congratsLabel.setText("Congratulations!");
            setCurrentUser();
            if (HomepageController.getCategorySelection() == 1) {
                if (student.getAnimalLevel() == 3) {
                    Student.setAnimalLevel(3);
                }
                else {
                    Student.setAnimalLevel(student.getAnimalLevel() + 1);
                }
            }
            else if (HomepageController.getCategorySelection() == 2) {
                if (student.getVehicleLevel() == 3) {
                    Student.setVehicleLevel(3);
                }
                else {
                    Student.setVehicleLevel(student.getVehicleLevel() + 1);
                }
            }
            else if (HomepageController.getCategorySelection() == 3) {
                if (student.getNatureLevel() == 3) {
                    Student.setNatureLevel(3);
                }
                else  {
                    Student.setNatureLevel(student.getNatureLevel() + 1);
                }
            }

        }
        else {
            resultsLabel.setText("You failed with " + percentageScore + "%");
            congratsLabel.setText("Better luck next time!");
            setCurrentUser();
        }
    }
}