package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import com.example.chermn.SpeechHelper;
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


/**
 * Quiz controller for question logic and updates.
 * Controller for handling quiz questions, answer submission,
 * UI updates, scoring, and navigation to the next question.
 *
 * Now includes full Text-To-Speech (TTS) accessibility:
 * - Hovering the question reads it aloud
 * - Hovering any answer option reads it aloud
 * - Speech stops when the mouse leaves
 * - Respects global TTS toggle, voice, and volume
 */
public class QuizQuestionsController extends BaseController {
    /**
     * Stores all quiz questions fetched from the API.
     * Loaded once per quiz to prevent repeated questions.
     */
    private List<TriviaQuestion> realQuestions = null;

    /** Tracks the index of the current question within the quiz. */
    private int currentQuestionIndex = 0;

    /** Score for the current quiz session. */
    public static int score = 0;

    /** Stores the current question text for AI explanation. */
    public static String theQuestion;

    // Buttons from quiz-questions.fxml
    @FXML private Button option1, option2, option3, option4, questionbutton, Next;

    // Label for AI explanation
    @FXML private Label explanation;

    /** The correct answer for the currently displayed question. */
    private String correctAnswer = null;

    /** Title for the actual window of the application. **/
    public static final String TITLE = "Farmer Fred's Trivia";

    /** Width constant for the actual window of the application. **/
    public static final int WIDTH = 1280;

    /** Height constant for the actual window of the application. **/
    public static final int HEIGHT = 720;

    /** Human‑readable question number (1–10) shown in the UI. */
    private int answerIndex = 1;

    /** Base CSS styles so they can be restored. */
    private String option1BaseStyle;
    private String option2BaseStyle;
    private String option3BaseStyle;
    private String option4BaseStyle;
    private String explanationBaseStyle;

    /**
     * Default constructor for the QuizQuestionsController.
     * <p>
     * Required by JavaFX for controller instantiation. Does not perform any
     * initialisation logic but declares exceptions to satisfy the class structure.
     *
     * @throws IOException if an I/O error occurs during controller setup
     * @throws InterruptedException if the controller initialisation is interrupted
     */
    public QuizQuestionsController() throws IOException, InterruptedException {}

    /**
     * Called automatically after FXML loads.
     * Captures original styles and attaches hover-to-speak listeners.
     */
    @FXML
    private void initialize() {
        // Store original styles
        option1BaseStyle = option1.getStyle();
        option2BaseStyle = option2.getStyle();
        option3BaseStyle = option3.getStyle();
        option4BaseStyle = option4.getStyle();
        explanationBaseStyle = explanation.getStyle();

        // QUESTION hover-to-speak
        questionbutton.setOnMouseEntered(e -> SpeechHelper.speak(questionbutton.getText()));
        questionbutton.setOnMouseExited(e -> SpeechHelper.stop());

        // ANSWER BUTTON hover-to-speak
        setupHoverToSpeak(option1);
        setupHoverToSpeak(option2);
        setupHoverToSpeak(option3);
        setupHoverToSpeak(option4);
    }

    /**
     * Attaches hover-to-speak behaviour to a button.
     * Reads the button text aloud when hovered.
     *
     * @param btn the answer button to attach listeners to
     */
    private void setupHoverToSpeak(Button btn) {
        btn.setOnMouseEntered(e -> SpeechHelper.speak(btn.getText()));
        btn.setOnMouseExited(e -> SpeechHelper.stop());
    }

    /**
     * Loads and displays the current quiz question.
     * <p>
     * Fetches questions from the API only once, then cycles through them
     * using {@code currentQuestionIndex}. Randomizes answer order and updates
     * all UI elements for the question screen.
     */
    @FXML
    public void getQuestions() {
        List<String> answers = new ArrayList<>();
        /// retrieving api questions from QuizBeginApiService - once at start of each quiz
        if (realQuestions == null) {
            QuizBeginApiService apiService = new QuizBeginApiService();
            realQuestions = apiService.fetchQuestions();
        }

        /// setting 'currentQuestion' to cycle through
        TriviaQuestion currentQuestion = realQuestions.get(currentQuestionIndex);
        theQuestion = currentQuestion.getQuestion();

        /// collating both correct and incorrect answers from api
        correctAnswer = currentQuestion.getCorrectAnswer();
        List<String> incorrectAnswers = currentQuestion.getIncorrectAnswers();
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
     * Restores original button styles and resets UI for the next question.
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
     * Handles user answer selection.
     * <p>
     * Highlights the chosen option, checks correctness, updates the score,
     * and retrieves a short explanation from the Ollama AI model. Also disables
     * all answer buttons to prevent multiple submissions.
     *
     * @param actionEvent the button click event triggered by the user's answer
     * @throws IOException if an input is not read properly or as expected.
     * @throws InterruptedException if a process is interrupted by a subsequent process.
     */
    public void AnswerSubmitted(javafx.event.ActionEvent actionEvent) throws IOException, InterruptedException {
        Button userAnswer = (Button) actionEvent.getSource();
        /// disable next button so users cant skip through questions
        Next.setDisable(false);

        // Highlight selected answer
        highlightSelectedAnswer(userAnswer.getText(), correctAnswer);

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
        String aiResponse = jsonObject.optString("response", "(No explanation available)");

        /// Check correctness - allowing for unwanted formatting text
        String selected = userAnswer.getText().substring(3).trim();
        String correct = correctAnswer.trim();

        boolean isCorrect = selected.equalsIgnoreCase(correct);

        if (isCorrect) {
            score += 1;
            explanation.setText("Correct! " + aiResponse);
            explanation.setStyle(explanationBaseStyle +
                    "; -fx-background-color: #ECFCE3; -fx-text-fill: #000000; -fx-font-size: 20px;");
        } else {
            explanation.setText("Incorrect! " + aiResponse);
            explanation.setStyle(explanationBaseStyle +
                    "; -fx-background-color: #FFC2C2; -fx-text-fill: #000000; -fx-font-size: 20px;");
        }
    }

    /**
     * Highlights the selected answer in green and greys out all others.
     *
     * @param selected the button the user clicked
     */
    private void highlightSelectedAnswer(String selected, String correct) {
        if (option1.getText().substring(3).equals(correctAnswer)) {
            ///correct answer format
            option1.setDisable(true);
            option1.setStyle("-fx-background-color: #E7FF76; -fx-text-fill: #3E7C2B; -fx-font-weight: bold; -fx-background-radius: 12; -fx-cursor: hand; -fx-font-size: 20px;-fx-opacity: 1.0;");
            ///incorrect answers
            option2.setDisable(true);
            option3.setDisable(true);
            option4.setDisable(true);
        } else if (option2.getText().substring(3).equals(correctAnswer)) {
            ///correct answer format
            option2.setDisable(true);
            option2.setStyle("-fx-background-color: #6DBE45; -fx-text-fill: #3E7C2B; -fx-font-weight: bold; -fx-background-radius: 12; -fx-cursor: hand; -fx-font-size: 20px;-fx-opacity: 1.0;");
            /// incorrect answers
            option1.setDisable(true);
            option3.setDisable(true);
            option4.setDisable(true);
        } else if (option3.getText().substring(3).equals(correctAnswer)) {
            ///correct answer format
            option3.setDisable(true);
            option3.setStyle("-fx-background-color: #6DBE45; -fx-text-fill: #3E7C2B; -fx-font-weight: bold; -fx-background-radius: 12; -fx-cursor: hand; -fx-font-size: 20px;-fx-opacity: 1.0;");
            /// incorrect answers
            option1.setDisable(true);
            option2.setDisable(true);
            option4.setDisable(true);
        } else {
            ///correct answer format
            option4.setDisable(true);
            option4.setStyle("-fx-background-color: #E7FF76; -fx-text-fill: #3E7C2B; -fx-font-weight: bold; -fx-background-radius: 12; -fx-cursor: hand; -fx-font-size: 20px;-fx-opacity: 1.0;");
            /// incorrect answers
            option1.setDisable(true);
            option2.setDisable(true);
            option3.setDisable(true);

        }

    }


    /**
     * Moves the quiz to the next question.
     * <p>
     * Re-enables all answer buttons, resets styles, hides the explanation label,
     * and disables the Next button to ensure the user must submit an answer before
     * progressing. Increments both the displayed question number and the internal
     * {@code currentQuestionIndex}. If more questions remain, loads the next one;
     * otherwise navigates to the quiz results screen.
     *
     * @throws IOException if the results screen FXML cannot be loaded
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

        currentQuestionIndex++;
        answerIndex++;
        // still going?
        if (currentQuestionIndex < realQuestions.size()){
            getQuestions();
        } else { // go to results screen
            Stage stage = (Stage) Next.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-results.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
            ///  reset score
            score = 0;
        }
    }
}
