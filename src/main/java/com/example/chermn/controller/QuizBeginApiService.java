package com.example.chermn.controller;

import com.example.chermn.model.TriviaQuestion;
import com.example.chermn.controller.HomepageController;
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

    /** Public static String 'getApiRequest' determines the category of quiz required for the api request. It finds what
     * button was clicked and returns the corresponding API call for use in the https request.
     */
    public static String getApiRequest() {

        if (HomepageController.categorySelection == 1) {
            return "https://opentdb.com/api.php?amount=10&type=multiple&difficulty=easy&category=27&encode=base64";
        }
        else if (HomepageController.categorySelection == 2) {
            return "https://opentdb.com/api.php?amount=10&type=multiple&difficulty=easy&category=28&encode=base64";
        }
        else if (HomepageController.categorySelection == 3) {
            return "https://opentdb.com/api.php?amount=10&type=multiple&difficulty=easy&category=17&encode=base64";
        }
        return "";
    }

    public static String API_REQUEST = "";

    /** Public static 'fetchQuestions' creates a Http client to request and retrieve the content from a request API_REQUEST.
     * Utilises the public getters in TriviaQuestion.java to loop through the request response and move items of interest into
     * a list of trivia questions questionList. The list of questions can then be used by the quiz session/quiz controllers.
     */
    public static List<TriviaQuestion> fetchQuestions() {
        List<TriviaQuestion> questionList = new ArrayList<>();

        try {
            API_REQUEST = getApiRequest();
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
            System.out.println("Error fetching from the API: " + e.getMessage()); // for testing
        }

        return questionList;

    }



}
