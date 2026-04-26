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

    public String getCorrectAnswer () {
        return this.correctAnswer;
    }

    public List<String> getIncorrectAnswers () {return this.incorrectAnswers;}

}
