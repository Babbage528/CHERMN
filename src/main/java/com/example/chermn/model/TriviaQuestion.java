package com.example.chermn.model;
import java.util.List;

public class TriviaQuestion {

    private String category;
    private String question;
    private String correctAnswer;
    private List<String> incorrectAnswers;

    public TriviaQuestion(String category, String question, String correctAnswer, List<String> incorrectAnswers) {
        this.category = category;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getCategory() {
        return this.category;
    }

    public String getQuestion() {
        return this.question;
    }

    public boolean checkAnswer (String userGuess) {
        return this.correctAnswer.equalsIgnoreCase(userGuess);
    }

}
