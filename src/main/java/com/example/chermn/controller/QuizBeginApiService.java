package com.example.chermn.controller;

import com.example.chermn.model.TriviaQuestion;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class QuizBeginApiService {

    public static final String API_REQUEST = "https://opentdb.com/api.php?amount=10&type=multiple&difficulty=easy";

    public static List<TriviaQuestion> fetchQuestions() {
        List<TriviaQuestion> questionList = new ArrayList<>();

        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_REQUEST))
                    .GET()
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String rawJsonText = response.body();

            JSONObject fullResponse = new JSONObject(rawJsonText);

            JSONArray resultsArray = fullResponse.getJSONArray("results");
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject jsonQuestion = resultsArray.getJSONObject(i);

                String category = jsonQuestion.getString("category");
                String question = jsonQuestion.getString("question");
                String correctAnswer = jsonQuestion.getString("correct_answer");

                JSONArray jsonIncorrectAnswers = jsonQuestion.getJSONArray("incorrect_answers");
                List<String> incorrectAnswers = new ArrayList<>();
                for (int j = 0; j < jsonIncorrectAnswers.length(); j++) {
                    incorrectAnswers.add(jsonIncorrectAnswers.getString(j));
                }

                TriviaQuestion newQuestion = new TriviaQuestion(category, question, correctAnswer, incorrectAnswers);
                questionList.add(newQuestion);
            }



        }catch (Exception e) {
            System.out.println("Error fetching from the API: " + e.getMessage());
        }

        return questionList;

    }

}
