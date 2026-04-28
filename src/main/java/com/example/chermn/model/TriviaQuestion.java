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

    /** Public 'TriviaQuestion' defines the parameters and construction of an object of type trivia question.
     * @param category string of quiz category
     * @param question string of question text
     * @param correctAnswer string for the correct answer
     * @param incorrectAnswers list of incorrect answers strings
     */
    public TriviaQuestion(String category, String question, String correctAnswer, List<String> incorrectAnswers) {
        this.category = category;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    /** Public string 'getCategory' decodes an api request response. Intended as a getter for a quiz category.
     */
    public String getCategory() {
        String encodedCategory = this.category;
        byte[] decodedCategoryBytes = Base64.getDecoder().decode(encodedCategory);
        String result = new String(decodedCategoryBytes, StandardCharsets.UTF_8);
        return result;
    }

    /** Public string 'getQuestion' decodes an api request response. Intended as a getter for individual question text.
     */
    public String getQuestion() {
        String encodedQuestion = this.question;
        byte[] decodedQuestionBytes = Base64.getDecoder().decode(encodedQuestion);
        String result = new String(decodedQuestionBytes, StandardCharsets.UTF_8);
        return result;
    }

    /** Public string 'getCorrectAnswer' decodes an api request response. Intended as a getter for a question's correct answer string.
     */
    public String getCorrectAnswer () {
        String encodedAnswer = this.correctAnswer;
        byte[] decodedAnswerBytes = Base64.getDecoder().decode(encodedAnswer);
        String result = new String(decodedAnswerBytes, StandardCharsets.UTF_8);
        return result;
    }

    /** Public list of strings 'getIncorrectAnswers' decodes an api request response. Intended as a getter for a list of incorrect answer strings.
     */
    public List<String> getIncorrectAnswers () {
        Base64.Decoder decoder = Base64.getDecoder();

        return this.incorrectAnswers.stream()
                .map(encoded -> {
                    byte[] decodedIncorrectAnswerBytes = decoder.decode(encoded);
                    return new String(decodedIncorrectAnswerBytes, StandardCharsets.UTF_8); // Converts byte array back to a string using UTF-8
                })
                .collect(Collectors.toList());
    }

}
