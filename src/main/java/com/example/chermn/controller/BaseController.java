package com.example.chermn.controller;

import com.example.chermn.SpeechHelper;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class BaseController {

    // Universal hover-to-speak handler for all screens
    public void speakAccessibleText(MouseEvent e) {
        Node node = (Node) e.getSource();
        String text = node.getAccessibleText();

        if (text != null && !text.isBlank()) {
            SpeechHelper.speak(text);
        }
    }
}