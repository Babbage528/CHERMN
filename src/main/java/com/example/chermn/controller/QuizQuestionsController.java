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
import java.util.ArrayList;
import java.util.List;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Controller for handling quiz questions, answer submission,
 * UI updates, scoring, and navigation to the next question.
 * Works with the existing FXML layout and preserves all inline styling.
 */
public class QuizQuestionsController extends BaseController {

    /// setting score for new quiz
    public static int score = 0;

    /// stores the current question text for AI explanation
    public static String theQuestion;

    @FXML
    /// buttons from quiz-questions.fxml
    private Button option1, option2, option3, option4, questionbutton, Next;

    @FXML
    /// label from quiz-questions.fxml
    private Label explanation;

    /// correct answer from API
    private String correctAnswer = null;

    /// window attributes
    public static final String TITLE = "Farmer Fred's Trivia";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    /// tracks question number
    private int answerIndex = 1;

    /// store original FXML styles so we can restore them
    private String option1BaseStyle;
    private String option2BaseStyle;
    private String option3BaseStyle;
    private String option4BaseStyle;
    private String explanationBaseStyle;

    public QuizQuestionsController() throws IOException, InterruptedException {}

    /**
     * Called by JavaFX after FXML is loaded.
     * Captures the original inline styles from FXML so they can be restored later.
     */
    @FXML
    private void initialize() {
        option1BaseStyle = option1.getStyle();
        option2BaseStyle = option2.getStyle();
        option3BaseStyle = option3.getStyle();
        option4BaseStyle = option4.getStyle();
        explanationBaseStyle = explanation.getStyle();
    }

    /**
     * Retrieves a question from the API, randomizes answers,
     * and updates the UI with the new question and answer options.
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

        /// collating both correct and incorrect answers from api
        answers.add(correctAnswer);
        answers.addAll(incorrectAnswers);

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

    /**
     * Resets all answer buttons to their original FXML styling
     * and enables them for the next question.
     */
    private void resetButtonStyles() {
        option1.setStyle(option1BaseStyle);
        option2.setStyle(option2BaseStyle);
        option3.setStyle(option3BaseStyle);
        option4.setStyle(option4BaseStyle);

        option1.setDisable(false);
        option2.setDisable(false);
        option3.setDisable(false);
        option4.setDisable(false);

        explanation.setText("");
        explanation.setStyle(explanationBaseStyle);

        Next.setDisable(true);
    }

    /**
     * Handles answer submission, locks UI, sends AI explanation request,
     * updates score, and displays explanation.
     *
     * @param actionEvent the button click event from any answer option
     */
    public void AnswerSubmitted(javafx.event.ActionEvent actionEvent) throws IOException, InterruptedException {
        Button userAnswer = (Button) actionEvent.getSource();

        /// enable next button
        Next.setDisable(false);

        /// highlight selected answer (green) and grey out others
        highlightSelectedAnswer(userAnswer);

        /// defining prompt for the api
        String newprompt = """
                using no personal pronouns say """ + " " + correctAnswer + " is the correct answer to " + theQuestion + " " + """
                 in 10 words with small explanation" ,
                 """;

        String jsonPayload = """
            {
              "model": "llama3",
              "prompt": " """ + newprompt + """
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

        /// prevents crash if "response" is missing
        String aiResponse = jsonObject.optString("response", "(No explanation available)");

        /// checking if user selected correct answer
        boolean isCorrect = userAnswer.getText().substring(3).equals(correctAnswer);

        if (isCorrect) {
            /// incrementing score
            score += 1;
            explanation.setText("Correct! " + aiResponse);
            /// green background for label
            explanation.setStyle(explanationBaseStyle + "; -fx-background-color: #ECFCE3; -fx-font-size: 20px;");
        } else {
            explanation.setText("Incorrect! " + aiResponse);
            /// red background for label
            explanation.setStyle(explanationBaseStyle + "; -fx-background-color: #FFC2C2; -fx-font-size: 20px;");
        }
    }

    /**
     * Highlights the selected answer in green and greys out all others.
     * Uses the original FXML styles as a base so the "pretty green grid"
     * is preserved and only colours are adjusted.
     *
     * @param selected the button the user clicked
     */
    private void highlightSelectedAnswer(Button selected) {
        List<Button> buttons = List.of(option1, option2, option3, option4);

        for (Button b : buttons) {
            String baseStyle;
            if (b == option1) baseStyle = option1BaseStyle;
            else if (b == option2) baseStyle = option2BaseStyle;
            else if (b == option3) baseStyle = option3BaseStyle;
            else baseStyle = option4BaseStyle;

            if (b == selected) {
                /// green on top of original style
                b.setStyle(baseStyle + "; -fx-background-color: #4CAF50; -fx-text-fill: white;");
            } else {
                /// grey on top of original style
                b.setStyle(baseStyle + "; -fx-background-color: #d3d3d3; -fx-text-fill: #666666;");
            }
            b.setDisable(true);
        }
    }

    /**
     * Loads the next question, resets UI, and navigates to results when finished.
     *
     * @throws IOException if FXML fails to load
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
        explanation.setStyle(explanationBaseStyle);

        /// enabling next button
        Next.setDisable(true);

        /// incrementing index of question and re-running getQuestions for new question
        if (answerIndex < 10) {
            answerIndex++;
            getQuestions();
        } else {
            /// change scene after quiz finished
            Stage stage = (Stage) Next.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-results.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);

            /// reset score
            score = 0;
        }
    }
}