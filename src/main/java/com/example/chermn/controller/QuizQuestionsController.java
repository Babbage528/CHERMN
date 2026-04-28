
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

    JSONObject jsonObject = new JSONObject("{\"response_code\":0,\"results\":[{\"type\":\"multiple\",\"difficulty\":\"easy\",\"category\":\"Science &amp; Nature\",\"question\":\"Alzheimer&#039;s disease primarily affects which part of the human body?\",\"correct_answer\":\"Brain\",\"incorrect_answers\":[\"Lungs\",\"Skin\",\"Heart\"]},{\"type\":\"multiple\",\"difficulty\":\"easy\",\"category\":\"Science &amp; Nature\",\"question\":\"How many planets make up our Solar System?\",\"correct_answer\":\"8\",\"incorrect_answers\":[\"7\",\"9\",\"6\"]},{\"type\":\"multiple\",\"difficulty\":\"easy\",\"category\":\"Science &amp; Nature\",\"question\":\"Which of these Elements is a metalloid?\",\"correct_answer\":\"Antimony\",\"incorrect_answers\":[\"Tin\",\"Bromine\",\"Rubidium\"]},{\"type\":\"multiple\",\"difficulty\":\"easy\",\"category\":\"Science &amp; Nature\",\"question\":\"Which is the most abundant element in the universe?\",\"correct_answer\":\"Hydrogen\",\"incorrect_answers\":[\"Helium\",\"Lithium\",\"Oxygen\"]},{\"type\":\"multiple\",\"difficulty\":\"easy\",\"category\":\"Science &amp; Nature\",\"question\":\"Human cells typically have how many copies of each gene?\",\"correct_answer\":\"2\",\"incorrect_answers\":[\"1\",\"4\",\"3\"]},{\"type\":\"multiple\",\"difficulty\":\"easy\",\"category\":\"Science &amp; Nature\",\"question\":\"What is the hottest planet in the Solar System?\",\"correct_answer\":\"Venus\",\"incorrect_answers\":[\"Mars\",\"Mercury\",\"Jupiter\"]},{\"type\":\"multiple\",\"difficulty\":\"easy\",\"category\":\"Science &amp; Nature\",\"question\":\"The asteroid belt is located between which two planets?\",\"correct_answer\":\"Mars and Jupiter\",\"incorrect_answers\":[\"Jupiter and Saturn\",\"Mercury and Venus\",\"Earth and Mars\"]},{\"type\":\"multiple\",\"difficulty\":\"easy\",\"category\":\"Science &amp; Nature\",\"question\":\"Which type of rock is created by intense heat AND pressure?\",\"correct_answer\":\"Metamorphic\",\"incorrect_answers\":[\"Sedimentary\",\"Igneous\",\"Diamond\"]},{\"type\":\"multiple\",\"difficulty\":\"easy\",\"category\":\"Science &amp; Nature\",\"question\":\"The medical term for the belly button is which of the following?\",\"correct_answer\":\"Umbilicus\",\"incorrect_answers\":[\"Nevus\",\"Nares\",\"Paxillus\"]},{\"type\":\"multiple\",\"difficulty\":\"easy\",\"category\":\"Science &amp; Nature\",\"question\":\"What is the official name of the star located closest to the North Celestial Pole?\",\"correct_answer\":\"Polaris\",\"incorrect_answers\":[\"Eridanus\",\"Gamma Cephei\",\"Iota Cephei\"]}]}");
    JSONArray resultsArray = jsonObject.getJSONArray("results");

    @FXML
    private Pane container;

    @FXML
    private Button option1, option2, option3, option4, start, questionbutton, Next;

    @FXML
    private Label explanation;

    String correctAnswer = null;

    public static final String TITLE = "Farmer Fred's Trivia";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    int answerIndex = 1;

    public QuizQuestionsController() throws JSONException {
    }

    @FXML
    public void getQuestions() {
        List<String> answers = new ArrayList<>();

            QuizBeginApiService apiService = new QuizBeginApiService();
            List<TriviaQuestion> realQuestions = apiService.fetchQuestions();
            QuizSessionController session = new QuizSessionController(realQuestions);

            TriviaQuestion currentQuestion = session.getCurrentQuestion();

            ///
            ///    while (currentQuestion1 != null) {
            ///        System.out.println("Category: " + currentQuestion1.getCategory());
            ///        System.out.println("Question: " + currentQuestion1.getQuestion());
            ///        System.out.print("Your answer: ");
            ///    }
            /// JSONObject jsonQuestion = resultsArray.getJSONObject(currentQuestion);

            correctAnswer = currentQuestion.getCorrectAnswer();
            List<String> incorrectAnswers = currentQuestion.getIncorrectAnswers();
            answers.add(correctAnswer);

            for (int j = 0; j < incorrectAnswers.size(); j++) {
                String answer = incorrectAnswers.get(j);
                answers.add(answer);
            }

            /// randomize answers so correct answer isn't always same position
            Collections.shuffle(answers);

            /// Display Question and answers
            questionbutton.setText("Q" + answerIndex + ". " + currentQuestion.getQuestion());
            option1.setText("a) " + answers.get(0));
            option2.setText("b) " + answers.get(1));
            option3.setText("c) " + answers.get(2));
            option4.setText("d) " + answers.get(3));

        }

    public void AnswerSubmitted(javafx.event.ActionEvent actionEvent) {
        Button userAnswer = (Button) actionEvent.getSource();
        Next.setDisable(false);
        if (option1.getText().substring(3).equals(correctAnswer)) {
            option2.setDisable(true);
            option3.setDisable(true);
            option4.setDisable(true);
        } else if (option2.getText().substring(3).equals(correctAnswer)) {
            option1.setDisable(true);
            option3.setDisable(true);
            option4.setDisable(true);
        } else if (option3.getText().substring(3).equals(correctAnswer)) {
            option1.setDisable(true);
            option2.setDisable(true);
            option4.setDisable(true);
        } else {
            option1.setDisable(true);
            option2.setDisable(true);
            option3.setDisable(true);

        }
        if (userAnswer.getText().substring(3).equals(correctAnswer)) {
            score += 1;
            explanation.setText("Correct! " + correctAnswer + " is the correct answer, good job!");
            explanation.setStyle("-fx-background-color: #ECFCE3; -fx-font-size: 20px;");
        } else {
            explanation.setText("Incorrect! " + correctAnswer + " is the correct answer, next time!");
            explanation.setStyle("-fx-background-color: #FFC2C2; -fx-font-size: 20px;");
        }
    }

    @FXML
    public void nextQuestion() throws IOException, JSONException {
        option1.setDisable(false);
        option2.setDisable(false);
        option3.setDisable(false);
        option4.setDisable(false);

        explanation.setText(" ");
        explanation.setStyle("-fx-background-color: transparent;");

        Next.setDisable(true);

        if(answerIndex < 10){
            answerIndex++;
            getQuestions();
        }
        else{
            /// explanation.setText("score: " + score);
            Stage stage = (Stage) Next.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-results.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
        }
    }



    }









