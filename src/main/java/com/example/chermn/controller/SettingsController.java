package com.example.chermn.controller;

import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.SpeechHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller for the settings screen.
 * Handles navigation, audio sliders, help dialog,
 * sign-out functionality, and Text-To-Speech (TTS) settings.
 */
public class SettingsController extends BaseController {

    // Buttons on the settings screen
    @FXML private Button closeButton;
    @FXML private Button signOutButton;
    @FXML private Button getHelpButton;

    // Sliders for audio settings
    @FXML private Slider musicSlider;
    @FXML private Slider soundFXSlider;

    // NEW: Read Aloud (TTS) toggle
    @FXML private CheckBox ttsToggle;

    // NEW: Voice selection dropdown
    @FXML private ChoiceBox<String> voiceChoice;

    /**
     * Called automatically when the FXML loads.
     * Initializes UI elements such as the TTS toggle and voice list.
     */
    @FXML
    public void initialize() {

        // Load current TTS enabled/disabled state from SpeechHelper
        ttsToggle.setSelected(SpeechHelper.isTtsEnabled());

        // Populate available Windows voices
        voiceChoice.getItems().addAll(
                "Microsoft David Desktop",
                "Microsoft Zira Desktop",
                "Microsoft Mark Desktop"
        );

        // Set the currently selected voice
        voiceChoice.setValue(SpeechHelper.getSelectedVoice());
    }

    /**
     * Handles the close button click event.
     * Returns the user to the homepage.
     *
     * @throws IOException if the homepage cannot be loaded.
     */
    @FXML
    protected void closeButtonClick() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(OnBoarding.class.getResource("homepage.fxml"));
        Scene scene = new Scene(loader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Handles the sign-out button click event.
     * Clears the current user session and returns to onboarding.
     *
     * @throws IOException if the onboarding screen cannot be loaded.
     */
    @FXML
    protected void signOutButtonClick() throws IOException {
        Session.clearCurrentUser();
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("onboarding-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Handles the Get Help button click.
     * Displays a help dialog containing game instructions.
     */
    @FXML
    protected void getHelpButtonClick() {
        showHelpDialog();
    }

    /**
     * Displays a help dialog with instructions and game objectives.
     */
    protected void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Farmer Fred's Trivia Application: Instructions");

        Label content = new Label(
                "This is a long instruction message that will tell the user how to use the game with instructions and objectives. " +
                        "Could start off being like: there are a variety of trivia topics to choose from including ... " +
                        "they range in varying difficulty levels - easy to hard - that you can progress through. " +
                        "You can keep track of your progress through the progress bar at the top of the homepage screen " +
                        "or through your profile which you can access via clicking on the farmhouse."
        );

        content.setWrapText(true);
        content.setMaxWidth(Double.MAX_VALUE);
        alert.getDialogPane().setPrefWidth(800);
        alert.getDialogPane().setContent(content);
        content.setStyle("-fx-font-size: 14px;");

        alert.showAndWait();
    }

    // ============================================================
    // NEW: READ ALOUD (TTS) SETTINGS
    // ============================================================

    /**
     * Toggles the Read Aloud (TTS) feature on or off.
     * Triggered when the user clicks the checkbox.
     */
    @FXML
    private void toggleTTS() {
        SpeechHelper.setTtsEnabled(ttsToggle.isSelected());
    }

    /**
     * Updates the selected TTS voice.
     * Triggered when the user chooses a voice from the dropdown.
     */
    @FXML
    private void changeVoice() {
        SpeechHelper.setSelectedVoice(voiceChoice.getValue());
    }
}