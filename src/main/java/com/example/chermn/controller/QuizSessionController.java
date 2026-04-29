package com.example.chermn.controller;

import com.example.chermn.model.TriviaQuestion;

import java.util.List;

public class QuizSessionController {
    private final List<TriviaQuestion> currentQuiz;
    private int currentQuestionIndex = 0;

    public QuizSessionController(List<TriviaQuestion> realQuestions) {
        this.currentQuiz = realQuestions;
    }

    /** Public TriviaQuestion 'getCurrentQuestions' handles the return of the current trivia question. Acts as a getter.*/
    public TriviaQuestion getCurrentQuestion() {
        if (currentQuestionIndex < currentQuiz.size()) {
            return currentQuiz.get(currentQuestionIndex);
        }
        return null;
    }
    public void nextQuestion() {
        if (currentQuestionIndex < currentQuiz.size()){
            currentQuestionIndex++;
        }
    }
}