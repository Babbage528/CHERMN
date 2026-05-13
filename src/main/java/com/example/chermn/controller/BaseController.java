package com.example.chermn.controller;

import com.example.chermn.SpeechHelper;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * BaseController provides shared accessibility behaviour for all controllers.
 * It implements a hover-to-speak system that only triggers text-to-speech when
 * the mouse stops moving over a UI element. If the cursor leaves the element
 * before the delay finishes, speech is cancelled.
 *
 * <p>To use this behaviour in FXML, attach:
 *
 * <pre>
 *     accessibleText="Some text"
 *     onMouseEntered="#speakAccessibleText"
 *     onMouseExited="#cancelSpeak"
 * </pre>
 *
 * <p>This prevents accidental speech when the user quickly moves the cursor
 * across the screen and ensures speech only occurs during intentional hovering.
 */
public class BaseController {

    /** Delay before speaking, used to detect whether the mouse has settled. */
    private final PauseTransition settleDelay = new PauseTransition(Duration.millis(180));

    /** Tracks the last hovered node to ensure speech only occurs on the correct element. */
    private Node lastHoveredNode = null;

    /**
     * Called when the mouse enters a node. Starts a delay timer and only speaks
     * if the mouse remains on the same node after the delay.
     *
     * @param e the mouse event triggered when entering a node
     */
    public void speakAccessibleText(MouseEvent e) {
        Node node = (Node) e.getSource();
        String text = node.getAccessibleText();

        if (text == null || text.isBlank()) {
            return;
        }

        lastHoveredNode = node;

        settleDelay.stop();

        settleDelay.setOnFinished(ev -> {
            if (lastHoveredNode == node) {
                SpeechHelper.speak(text);
            }
        });

        settleDelay.playFromStart();
    }

    /**
     * Called when the mouse exits a node. Cancels any pending speech.
     *
     * @param e the mouse event triggered when leaving a node
     */
    public void cancelSpeak(MouseEvent e) {
        settleDelay.stop();
        lastHoveredNode = null;
    }
}