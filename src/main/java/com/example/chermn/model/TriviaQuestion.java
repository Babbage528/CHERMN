package com.example.chermn.model;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        String encodedCategory = this.category;
        byte[] decodedCategoryBytes = Base64.getDecoder().decode(encodedCategory);
        String result = new String(decodedCategoryBytes, StandardCharsets.UTF_8);
        return result;
    }

    public String getQuestion() {
        String encodedQuestion = this.question;
        byte[] decodedQuestionBytes = Base64.getDecoder().decode(encodedQuestion);
        String result = new String(decodedQuestionBytes, StandardCharsets.UTF_8);
        return result;
    }

    public String getCorrectAnswer () {
        String encodedAnswer = this.correctAnswer;
        byte[] decodedAnswerBytes = Base64.getDecoder().decode(encodedAnswer);
        String result = new String(decodedAnswerBytes, StandardCharsets.UTF_8);
        return result;
    }

    public List<String> getIncorrectAnswers () {
        Base64.Decoder decoder = Base64.getDecoder();

        return this.incorrectAnswers.stream()
                .map(encoded -> {
                    byte[] decodedIncorrectAnswerBytes = decoder.decode(encoded);
                    // Converts byte array back to a String using UTF-8
                    return new String(decodedIncorrectAnswerBytes, StandardCharsets.UTF_8);
                })
                .collect(Collectors.toList());
    }

}
