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

public class QuizResultsController {

    @FXML
    private Label congratsLabel;

    @FXML
    private Label resultsLabel;

    @FXML
    private Button returnToHomepageButton;

    @FXML
    private Button beginQuizButton;

    public QuizResultsController() throws JSONException {
    }

    /** Protected void 'returnToHomepageButtonClick' defines the actions that occur when the return to homepage ui element
     * is selected. The function returns the user to the homepage screen.
     */
    @FXML
    protected void returnToHomepageButtonClick() throws  IOException{
        Stage stage = (Stage) returnToHomepageButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(QuizBegin.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), QuizBegin.WIDTH, QuizBegin.HEIGHT);
        stage.setScene(scene);
    }

    int percentageScore = (QuizQuestionsController.score*10);

    // Initialise the user logged in
    Users currentStudent = Session.getCurrentUser();

    Student student;

    // Check if logged in user is a student
    public void setCurrentUser() {
        // check whether the user is a student
        if (currentStudent instanceof Student) {
            student = (Student) currentStudent;
        }
        else {
            throw new IllegalArgumentException("Quiz requires a Student user");
        }

    }

    // Intended for 10 question quizzes only
    public void initialize() {
        if (percentageScore >= 80) {
            resultsLabel.setText("You passed with " + percentageScore + "%");
            congratsLabel.setText("Congratulations!");
            setCurrentUser();
            if (student.getAnimalLevel() == 3)  {
                Student.setAnimalLevel(3);
            }
            else if (HomepageController.getCategorySelection() == 1) {
                Student.setAnimalLevel(student.getAnimalLevel() + 1);
            }
            else if (HomepageController.getCategorySelection() == 2) {
                Student.setVehicleLevel(student.getVehicleLevel() + 1);
            }
            else if (HomepageController.getCategorySelection() == 3) {
                Student.setNatureLevel(student.getNatureLevel() + 1);
            }

        }
        else {
            resultsLabel.setText("You failed with " + percentageScore + "%");
            congratsLabel.setText("Better luck next time!");
            setCurrentUser();
        }

    }







}