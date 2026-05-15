package com.example.chermn.controller;

import com.example.chermn.model.TriviaQuestion;

import java.util.List;

/**
 * Quiz session controller handles the return of current quiz session question.
 * Passes questions from the API controller to other quiz controllers.
 */
public class QuizSessionController extends BaseController{
    private List<TriviaQuestion> currentQuiz;
    private int currentQuestionIndex = 0;

    public QuizSessionController(List<TriviaQuestion> realQuestions) {
        this.currentQuiz = realQuestions;
    }

    /** Public TriviaQuestion 'getCurrentQuestions' handles the return of the current trivia question. Acts as a getter.
     */
    public TriviaQuestion getCurrentQuestion() {
        if (currentQuestionIndex < currentQuiz.size()) {
            return currentQuiz.get(currentQuestionIndex);
        }
        return null;
    }
}