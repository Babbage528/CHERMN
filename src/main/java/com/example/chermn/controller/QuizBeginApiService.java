package com.example.chermn.controller;

import com.example.chermn.model.TriviaQuestion;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for building API requests and fetching quiz questions.
 * <p>
 * Communicates with the external trivia API, parses JSON responses,
 * and converts them into {@link TriviaQuestion} model objects.
 */
public class QuizBeginApiService extends BaseController {

    /** Stores the constructed API request URL. */
    public static String API_REQUEST = "";

    /**
     * Default constructor for QuizBeginApiService.
     * Required for controller and service instantiation.
     */
    public QuizBeginApiService() {}

    /**
     * Returns the full API request URL used to fetch trivia questions.
     *
     * @return the API request string
     */
    public static String getApiRequest() {
        return API_REQUEST;
    }

    /**
     * Fetches trivia questions from the external API and converts them into model objects.
     *
     * @return a list of {@link TriviaQuestion} objects parsed from the API response
     */
    public static List<TriviaQuestion> fetchQuestions() {
        List<TriviaQuestion> questions = new ArrayList<>();

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_REQUEST))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(response.body());
            JSONArray results = json.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject q = results.getJSONObject(i);

                String question = q.getString("question");
                String correct = q.getString("correct_answer");

                JSONArray incorrectArray = q.getJSONArray("incorrect_answers");
                List<String> incorrect = new ArrayList<>();
                for (int j = 0; j < incorrectArray.length(); j++) {
                    incorrect.add(incorrectArray.getString(j));
                }

                questions.add(new TriviaQuestion(question, correct, incorrect));
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return questions;
    }
}