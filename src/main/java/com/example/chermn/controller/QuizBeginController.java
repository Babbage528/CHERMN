package com.example.chermn.controller;

import com.example.chermn.QuizBegin;
import com.example.chermn.Session;
import com.example.chermn.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;

/**
 * Controller for the quiz setup screen.
 * <p>
 * Handles category and difficulty selection, navigation to the quiz questions,
 * and returning to the homepage.
 */
public class QuizBeginController extends BaseController {

    @FXML
    private Label categoryLabel;

    @FXML
    private Label difficultyLabel;

    /** Stores the selected quiz category. */
    private static int categorySelection;

    /** Stores the selected difficulty level. */
    private static int difficultySelection;

    /**
     * Default constructor for QuizBeginController.
     * Required for JavaFX controller instantiation.
     */
    public QuizBeginController() {}

    /**
     * Returns the user to the homepage screen.
     *
     * @throws IOException if the homepage FXML cannot be loaded
     */
    @FXML
    protected void returnToHomepageButtonClick() throws IOException {
        Stage stage = (Stage) categoryLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(QuizBegin.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), QuizBegin.WIDTH, QuizBegin.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Begins the quiz by loading the quiz questions screen.
     *
     * @param event the button click event that triggered the action
     * @throws IOException if the quiz questions FXML cannot be loaded
     * @throws JSONException if JSON parsing fails during quiz setup
     */
    @FXML
    protected void beginQuizButtonClick(ActionEvent event) throws IOException, JSONException {
        Stage stage = (Stage) categoryLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(QuizBegin.class.getResource("quiz-questions.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), QuizBegin.WIDTH, QuizBegin.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Sets the category label text.
     *
     * @param text the category name to display
     */
    public void setCategoryText(String text) {
        categoryLabel.setText(text);
    }

    /**
     * Sets the difficulty label text.
     *
     * @param text the difficulty level to display
     */
    public void setDifficultyText(String text) {
        difficultyLabel.setText(text);
    }

    /**
     * Stores the selected category index.
     *
     * @param selection the category index chosen by the user
     */
    public static void setCategorySelection(int selection) {
        categorySelection = selection;
    }

    /**
     * Stores the selected difficulty index.
     *
     * @param selection the difficulty index chosen by the user
     */
    public static void setDifficultySelection(int selection) {
        difficultySelection = selection;
    }

    /**
     * Returns the selected category index.
     *
     * @return the selected category
     */
    public static int getCategorySelection() {
        return categorySelection;
    }

    /**
     * Returns the selected difficulty index.
     *
     * @return the selected difficulty
     */
    public static int getDifficultySelection() {
        return difficultySelection;
    }
}