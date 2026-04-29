package com.example.chermn.controller;

import com.example.chermn.model.TriviaQuestion;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.List;
import java.util.ArrayList;

import static java.net.URLDecoder.decode;

public class QuizBeginApiService {

    // dont hard code the request, cant change difficulty or category
    // public static final String API_REQUEST = "https://opentdb.com/api.php?amount=10&type=multiple&difficulty=easy&category=27&encode=base64";

    // map categories to IDs - these numbers might be wrong!
    private static int mapCategory(String category) {
        return switch (category.toLowerCase()){
            case "animals" -> 27;
            case "vehicles" -> 28;
            case "nature" -> 23;
            default -> 27;
        };
    }


    /** Public static 'fetchQuestions' creates a Http client to request and retrieve the content from a request API_REQUEST.
     * Utilises the public getters in TriviaQuestion.java to loop through the request response and move items of interest into
     * a list of trivia questions questionList. The list of questions can then be used by the quiz session/quiz controllers.
     */
    public List<TriviaQuestion> fetchQuestions(String category, String difficulty) {
        List<TriviaQuestion> questionList = new ArrayList<>();

        try {
            int categoryId = mapCategory(category); // get mapped ID
            String apiUrl = "https://opentdb.com/api.php?amount=10"
                    + "&type=multiple"
                    + "&difficulty=" + difficulty
                    + "&category=" +categoryId
                    + "&encode=base64";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject fullResponse = new JSONObject(response.body());
            JSONArray resultsArray = fullResponse.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject jsonQuestion = resultsArray.getJSONObject(i);

                String categoryDecoded = decode(jsonQuestion.getString("category"));
                String questionDecoded = decode(jsonQuestion.getString("question"));
                String correctAnswerDecoded = decode(jsonQuestion.getString("correct_answer"));

                JSONArray jsonIncorrectAnswers = jsonQuestion.getJSONArray("incorrect_answers");
                List<String> incorrectAnswers = new ArrayList<>();
                for (int j = 0; j < jsonIncorrectAnswers.length(); j++) {
                    incorrectAnswers.add(decode(jsonIncorrectAnswers.getString(j)));
                }

                questionList.add(new TriviaQuestion(categoryDecoded, questionDecoded, correctAnswerDecoded, incorrectAnswers));
            }



        }catch (Exception e) {
            System.out.println("Error fetching from the API: " + e.getMessage()); // for testing
        }

        return questionList;

    }

    private String decode(String base64){
        return new String(Base64.getDecoder().decode(base64));
    }


}
