package com.example.chermn.model;

/**
 * Represents a single quiz attempt by a student.
 */
public class QuizAttempt {

    private int categoryId;
    private double score;
    private boolean passed;

    /**
     * Public constructor that initialises a user's quiz attempt, taking
     * the trivia category ID, the user's score, and a boolean that denotes whether
     * the user passed or failed the quiz.
     * @param categoryId the trivia quiz category
     * @param score the user's quiz score
     * @param passed whether the user passed or failed the quiz
     */
    public QuizAttempt(int categoryId, double score, boolean passed) {
        this.categoryId = categoryId;
        this.score = score;
        this.passed = passed;
    }

}