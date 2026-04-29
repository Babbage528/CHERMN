
package com.example.chermn.controller;

import com.example.chermn.controller.QuizSessionController;
import com.example.chermn.OnBoarding;
import com.example.chermn.QuizQuestions;
import com.example.chermn.model.TriviaQuestion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Collections;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.sql.Array;
import java.util.Arrays;
public class QuizQuestionsController {

    public static int score = 0;
    private String category;
    private String difficulty;

    private QuizSessionController session;
    private int answerIndex = 1;
    private String correctAnswer;

    @FXML
    private Button option1, option2, option3, option4,  questionbutton, Next;

    @FXML
    private Label explanation, newLabel;

    public static final String TITLE = "Farmer Fred's Trivia";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public void initData(String category, String difficulty){
        this.category = category;
        this.difficulty = difficulty;

        QuizBeginApiService api = new QuizBeginApiService();
        List<TriviaQuestion> questions = api.fetchQuestions(category, difficulty);

        session = new QuizSessionController(questions);

        // load first questions now that session exists
        getQuestions();
    }

    /** public 'getQuestions' retries the api response from QuizBegin, including the question, correct answers and incorrect answer.
     * It adds the correct answers and incorrect answers into a list and randomizes the list so that the answers are not always in the same place.
     * The Text on the questions and answer buttons in the fxml are then set to the question and answers retrieved from the api, with appropriate formatting such as 'a), b)...' and 'Q1.'.
     *
     */
    @FXML
    public void getQuestions() {
        TriviaQuestion q = session.getCurrentQuestion();

        List<String> answers = new ArrayList<>();
        correctAnswer = q.getCorrectAnswer();
        answers.add(correctAnswer);
        answers.addAll(q.getIncorrectAnswers());

        /// randomize answers so correct answer isn't always same position
        Collections.shuffle(answers);

        /// Display Question and answers
        questionbutton.setText("Q" + answerIndex + ". " + q.getQuestion());
        option1.setText("a) " + answers.get(0));
        option2.setText("b) " + answers.get(1));
        option3.setText("c) " + answers.get(2));
        option4.setText("d) " + answers.get(3));

        }

    /** Public void 'Answer Submitted' controls the UI and score when a user clicks an answer button.
     * @param actionEvent is used to check for any of the buttons submitted (as users can select correct or incorrect answer and the same code block needs to run - DRY code).
     * Once a button clicking event has been registered, the incorrect option buttons are disabled.
     * There is then a conditional statement to check if the user submitted the correct answer which updates the score, gives an appropriate message and sets the label colour to green.
     * If the user submits an incorrect answer, the score is not updated, an appropriate message is displayed and the label is coloured red.
     */
    @FXML
    public void AnswerSubmitted(javafx.event.ActionEvent event) {
        Button userAnswer = (Button) event.getSource();
        Next.setDisable(false);

        disableIncorrectButtons();

        if (userAnswer.getText().substring(3).equals(correctAnswer)) {
            score += 1;
            explanation.setText("Correct! " + correctAnswer + " is the correct answer, good job!");
            explanation.setStyle("-fx-background-color: #ECFCE3; -fx-font-size: 20px;");
        } else {
            explanation.setText("Incorrect! " + correctAnswer + " is the correct answer, next time!");
            explanation.setStyle("-fx-background-color: #FFC2C2; -fx-font-size: 20px;");
        }
    }

    private void disableIncorrectButtons() {
        for (Button b : List.of(option1, option2, option3, option4)) {
            if (!b.getText().substring(3).equals(correctAnswer)) {
                b.setDisable(true);
            }
        }
    }

    /** Public void 'nextQuestion' is used to get the next question for the user.
     * The buttons are all enabled again after being disabled when the answer was submitted, and the label and message are hidden.
     * The next button is disabled to enforce users to submit an answer before continuing.
     * A conditional statement determines if the quiz is still continuing which will increase the index for what question the user is on.
     * If the user has finished the quiz it will change to the scene and fxml for 'quiz results' to display the score.
     *
     * @throws IOException
     */
    @FXML
    public void nextQuestion() throws IOException {
        option1.setDisable(false);
        option2.setDisable(false);
        option3.setDisable(false);
        option4.setDisable(false);

        explanation.setText(" ");
        explanation.setStyle("-fx-background-color: transparent;");
        Next.setDisable(true);

        if(answerIndex < 10){
            session.nextQuestion();
            answerIndex++;
            getQuestions();
        } else{
            Stage stage = (Stage) Next.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-results.fxml"));
            stage.setScene(new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT));
        }
    }
}