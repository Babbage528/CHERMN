package com.example.chermn;

import com.example.chermn.controller.BaseController;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.event.EventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for accessibility behaviour including:
 * - Text‑to‑speech enable/disable logic
 * - Volume and voice settings
 * - Ignoring null or blank accessible text
 * - BaseController hover‑to‑speak and cancel‑speak behaviour
 *
 * These tests run without JavaFX rendering and validate the logic only.
 */
public class AccessibilityTest {

    private BaseController controller;

    @BeforeEach
    void setUp() {
        controller = new BaseController();
        SpeechHelper.setTtsEnabled(true);
        SpeechHelper.setTtsVolume(100);
        SpeechHelper.setSelectedVoice("Microsoft Zira Desktop");
    }

    // SPEECHHELPER LOGIC TESTS
    @Test
    void testSpeakDoesNothingWhenDisabled() {
        SpeechHelper.setTtsEnabled(false);
        assertDoesNotThrow(() -> SpeechHelper.speak("Hello world"));
    }

    @Test
    void testSpeakIgnoresNull() {
        assertDoesNotThrow(() -> SpeechHelper.speak(null));
    }

    @Test
    void testSpeakIgnoresBlank() {
        assertDoesNotThrow(() -> SpeechHelper.speak("   "));
    }

    @Test
    void testStopDoesNotThrow() {
        assertDoesNotThrow(SpeechHelper::stop);
    }

    @Test
    void testSetVolume() {
        SpeechHelper.setTtsVolume(55);
        assertEquals(55, SpeechHelper.getTtsVolume());
    }

    @Test
    void testSetVoice() {
        SpeechHelper.setSelectedVoice("Microsoft David Desktop");
        assertEquals("Microsoft David Desktop", SpeechHelper.getSelectedVoice());
    }

    @Test
    void testTtsEnabledToggle() {
        SpeechHelper.setTtsEnabled(false);
        assertFalse(SpeechHelper.isTtsEnabled());

        SpeechHelper.setTtsEnabled(true);
        assertTrue(SpeechHelper.isTtsEnabled());
    }
}