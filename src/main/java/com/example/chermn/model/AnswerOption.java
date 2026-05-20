package com.example.chermn.model;

/**
 * Represents an answer option.
 */
public class AnswerOption {
    private String text;
    private boolean isCorrect;

    /**
     * Creates a new answer option.
     *
     * @param text the text displayed for this option
     * @param isCorrect whether this option is the correct answer
     */
    public AnswerOption(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    /**
     * Returns the text of this answer option.
     *
     * @return the option text
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the text of this answer option.
     *
     * @return the option text
     */
    public boolean isCorrect() {
        return isCorrect;
    }
}