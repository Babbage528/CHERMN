package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import com.example.chermn.model.TriviaQuestion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Collections;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class QuizQuestionsController  extends BaseController{
    /// setting score for new quiz
    public static int score = 0;
    public static String theQuestion;

    @FXML
    /// buttons from quiz-questions.fxml
    private Button option1, option2, option3, option4,  questionbutton, Next;

    @FXML
    /// label from quiz-questions.fxml
    private Label explanation;

    String correctAnswer = null;

    /// setting window attributes
    public static final String TITLE = "Farmer Fred's Trivia";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    int answerIndex = 1;

    public QuizQuestionsController() throws IOException, InterruptedException {
    }

    /** public 'getQuestions' retries the api response from QuizBegin, including the question, correct answers and incorrect answer.
     * It adds the correct answers and incorrect answers into a list and randomizes the list so that the answers are not always in the same place.
     * The Text on the questions and answer buttons in the fxml are then set to the question and answers retrieved from the api, with appropriate formatting such as 'a), b)...' and 'Q1.'.
     *
     */
    @FXML
    public void getQuestions() {
        List<String> answers = new ArrayList<>();
        /// retrieving api questions from QuizBeginApiService
        QuizBeginApiService apiService = new QuizBeginApiService();
        List<TriviaQuestion> realQuestions = apiService.fetchQuestions();
        QuizSessionController session = new QuizSessionController(realQuestions);

        /// setting 'currentQuestion' to cycle through
        TriviaQuestion currentQuestion = session.getCurrentQuestion();
        theQuestion = currentQuestion.getQuestion();

        /// defining responses from api
        correctAnswer = currentQuestion.getCorrectAnswer();
        List<String> incorrectAnswers = currentQuestion.getIncorrectAnswers();
        answers.add(correctAnswer);

        /// collating both correct and incorrect answers from api
        for (int j = 0; j < incorrectAnswers.size(); j++) {
            String answer = incorrectAnswers.get(j);
            answers.add(answer);
        }

        /// randomize answers so correct answer isn't always same position
        Collections.shuffle(answers);

        /// Display Question and answers with formatting
        questionbutton.setText("Q" + answerIndex + ". " + currentQuestion.getQuestion());
        option1.setText("a) " + answers.get(0));
        option2.setText("b) " + answers.get(1));
        option3.setText("c) " + answers.get(2));
        option4.setText("d) " + answers.get(3));

        resetButtonStyles();
    }

    private void resetButtonStyles() {
        option1.getStyleClass().setAll("answer-button", "answer-a");
        option2.getStyleClass().setAll("answer-button", "answer-b");
        option3.getStyleClass().setAll("answer-button", "answer-c");
        option4.getStyleClass().setAll("answer-button", "answer-d");

        option1.setDisable(false);
        option2.setDisable(false);
        option3.setDisable(false);
        option4.setDisable(false);

        explanation.setText("");
        explanation.getStyleClass().setAll("explanation-label");

        Next.setDisable(true);
    }

    /** Public void 'Answer Submitted' controls the UI and score when a user clicks an answer button also posting and returning an api response from the ollama ai api.
     * @param actionEvent is used to check for any of the buttons submitted (as users can select correct or incorrect answer and the same code block needs to run - DRY code).
     * Once a button clicking event has been registered, the incorrect option buttons are disabled.
     * There is then a conditional statement to check if the user submitted the correct answer which updates the score, gives an appropriate message and sets the label colour to green.
     * If the user submits an incorrect answer, the score is not updated, an appropriate message is displayed and the label is coloured red.
     * The labels that change colour also have the response from the ollama ai api to explain what and why the correct answer is.
     */
    public void AnswerSubmitted(javafx.event.ActionEvent actionEvent) throws IOException, InterruptedException {
        Button userAnswer = (Button) actionEvent.getSource();
        /// disable next button so users cant skip through questions
        Next.setDisable(false);

        /// disable all buttons
        option1.setDisable(true);
        option2.setDisable(true);
        option3.setDisable(true);
        option4.setDisable(true);

        /// highlight correct answer
        highlightCorrectAnswer();

        /// defining prompt for the api
        String newprompt = """ 
                using no personal pronouns say """ + " " + correctAnswer + " is the correct answer to " +  theQuestion + " " + """
                 in 10 words with small explanation" , 
                 """;
        String jsonPayload = """
            {
              "model": "llama3",
              "prompt": " """ +newprompt+""" 
              "stream": false
            }
            """;

        /// sending ai prompt to ollama
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:11434/api/generate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        /// formatting ai api response
        JSONObject jsonObject = new JSONObject(response.body());

        String aiResponse = jsonObject.getString("response");
        /// setting label formatting and text depending on answer

        boolean isCorrect = userAnswer.getText().substring(3).equals(correctAnswer);
        if (isCorrect) {
            /// incrementing score
            score += 1;
            explanation.setText("Correct! "  + aiResponse);
            /// green background for label
            explanation.setStyle("-fx-background-color: #ECFCE3; -fx-font-size: 20px;");
        } else {
            explanation.setText("Incorrect! "  + aiResponse);
            /// red background for label
            explanation.setStyle("-fx-background-color: #FFC2C2; -fx-font-size: 20px;");
        }
    }


    private void highlightCorrectAnswer() {
        List<Button> buttons = List.of(option1, option2, option3, option4);
        for (Button b : buttons) {
            String text = b.getText().substring(3);
            if (text.equals(correctAnswer)) {
                b.getStyleClass().add("correct-answer");
            } else {
                b.getStyleClass().add("wrong-answer");
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
        /// enabling all answer buttons
        option1.setDisable(false);
        option2.setDisable(false);
        option3.setDisable(false);
        option4.setDisable(false);

        /// resetting button formatting
        resetButtonStyles();

        /// resetting label
        explanation.setText(" ");
        explanation.setStyle("-fx-background-color: transparent;");

        /// enabling next button
        Next.setDisable(true);

        /// incrementing index of question and re-running getQuestions for new question
        if(answerIndex < 10){
            answerIndex++;
            getQuestions();
        }
        else{
            /// change scene after quiz finished
            Stage stage = (Stage) Next.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-results.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
            ///  reset score
            score = 0;
        }
    }
}
