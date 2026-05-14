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

    /** Score for the current quiz session. */
    public static int score = 0;

    /** Stores the current question text for AI explanation. */
    public static String theQuestion;

    // Buttons from quiz-questions.fxml
    @FXML private Button option1, option2, option3, option4, questionbutton, Next;

    // Label for AI explanation
    @FXML private Label explanation;

    /** Correct answer from API. */
    private String correctAnswer = null;

    /// setting window attributes
    public static final String TITLE = "Farmer Fred's Trivia";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    /** Tracks question number. */
    private int answerIndex = 1;

    /** Stores original FXML styles so they can be restored. */
    private String option1BaseStyle;
    private String option2BaseStyle;
    private String option3BaseStyle;
    private String option4BaseStyle;
    private String explanationBaseStyle;

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
        answers.add(correctAnswer);
        answers.addAll(incorrectAnswers);

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

        // Highlight selected answer
        highlightSelectedAnswer(userAnswer);

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

        // Check correctness
        boolean isCorrect =
                userAnswer.getText().substring(3).equals(correctAnswer);

        if (isCorrect) {
            score += 1;
            explanation.setText("Correct! " + aiResponse);
            explanation.setStyle(explanationBaseStyle +
                    "; -fx-background-color: #ECFCE3; -fx-font-size: 20px;");
        } else {
            explanation.setText("Incorrect! " + aiResponse);
            explanation.setStyle(explanationBaseStyle +
                    "; -fx-background-color: #FFC2C2; -fx-font-size: 20px;");
        }
    }

    /**
     * Highlights the selected answer in green and greys out all others.
     *
     * @param selected the button the user clicked
     */
    private void highlightSelectedAnswer(Button selected) {

        List<Button> buttons = List.of(option1, option2, option3, option4);

        for (Button b : buttons) {

            String baseStyle =
                    (b == option1) ? option1BaseStyle :
                            (b == option2) ? option2BaseStyle :
                                    (b == option3) ? option3BaseStyle :
                                            option4BaseStyle;

            if (b == selected) {
                b.setStyle(baseStyle +
                        "; -fx-background-color: #4CAF50; -fx-text-fill: white;");
            } else {
                b.setStyle(baseStyle +
                        "; -fx-background-color: #d3d3d3; -fx-text-fill: #666666;");
            }

            b.setDisable(true);
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
        explanation.setStyle(explanationBaseStyle);

        /// enabling next button
        Next.setDisable(true);

        if (answerIndex < 10) {
            answerIndex++;
            getQuestions();
        } else {
            Stage stage = (Stage) Next.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-results.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
            ///  reset score
            score = 0;
        }
    }
}
