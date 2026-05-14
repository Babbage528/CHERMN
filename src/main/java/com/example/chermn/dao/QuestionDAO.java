package com.example.chermn.dao;

import com.example.chermn.model.AnswerOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.chermn.DatabaseConnection;

/**
 * handles database operations related to the quiz questions
 */
public class QuestionDAO {

    /**
     * this gets the question text based on ID
     * @param questionId the question ID
     * @return question text or null
     */
    public String getQuestionText(int questionId) {
        try {
            Connection conn = DatabaseConnection.connect();

            String sql = "SELECT question_text FROM QUESTION WHERE question_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, questionId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("question_text");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * collects all answer options for a question.
     * @param questionId the question ID
     * @return list of answers
     */
    public List<AnswerOption> getAnswers(int questionId) {

        List<AnswerOption> answers = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.connect();

            String sql = """
                SELECT answer_text, is_correct
                FROM ANSWER_OPTION
                WHERE question_id = ?
            """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, questionId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                answers.add(new AnswerOption(
                    rs.getString("answer_text"),
                    rs.getBoolean("is_correct")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return answers;
    }
}