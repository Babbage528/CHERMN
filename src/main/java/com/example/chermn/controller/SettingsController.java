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
        // Existing TTS toggle + voice setup
        ttsToggle.setSelected(SpeechHelper.isTtsEnabled());
        voiceChoice.getItems().addAll(
                "Microsoft David Desktop",
                "Microsoft Zira Desktop",
                "Microsoft Mark Desktop"
        );
        voiceChoice.setValue(SpeechHelper.getSelectedVoice());

        // NEW: Bind Sound FX slider to TTS volume
        soundFXSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            SpeechHelper.setTtsVolume(newVal.doubleValue());
        });
        // Disable controls if TTS is off
        voiceChoice.setDisable(!SpeechHelper.isTtsEnabled());
        soundFXSlider.setDisable(!SpeechHelper.isTtsEnabled());
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
        GameInstuctionsController.showHelpDialog();
    }


    // READ ALOUD (TTS) SETTINGS
    /**
     * Toggles the Read Aloud (TTS) feature on or off.
     * Also enables/disables the voice selector and volume slider.
     */
    @FXML
    private void toggleTTS() {
        boolean enabled = ttsToggle.isSelected();

        // Update TTS engine
        SpeechHelper.setTtsEnabled(enabled);

        // Grey out / enable controls
        voiceChoice.setDisable(!enabled);
        soundFXSlider.setDisable(!enabled);
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