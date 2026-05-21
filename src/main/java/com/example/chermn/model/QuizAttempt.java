package com.example.chermn.model;

import java.sql.Date;

/**
 * Represents a single quiz attempt by a student.
 */
public class QuizAttempt {

    private int categoryId;
    private double score;
    private boolean passed;

    public QuizAttempt(int categoryId, double score, boolean passed) {
        this.categoryId = categoryId;
        this.score = score;
        this.passed = passed;
    }

    public int getCategoryId() { return categoryId; }
    public double getScore() { return score; }
    public boolean isPassed() { return passed; }

    public String getCategoryName() {
        return switch (categoryId) {
            case 1 -> "Animals";
            case 2 -> "Nature";
            case 3 -> "Vehicles";
            default -> "Unknown";
        };
    }
}