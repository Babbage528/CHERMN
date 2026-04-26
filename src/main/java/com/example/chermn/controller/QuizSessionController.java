package com.example.chermn.controller;

import com.example.chermn.model.TriviaQuestion;

import java.util.List;

public class QuizSessionController {
    private List<TriviaQuestion> currentQuiz;
    private int currentQuestionIndex = 0;

    public QuizSessionController(List<TriviaQuestion> realQuestions) {
        this.currentQuiz = realQuestions;
    }

    public TriviaQuestion getCurrentQuestion() {
        if (currentQuestionIndex < currentQuiz.size()) {
            return currentQuiz.get(currentQuestionIndex);
        }
        return null;
    }
}