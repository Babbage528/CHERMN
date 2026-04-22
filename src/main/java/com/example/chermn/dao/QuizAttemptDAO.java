package com.example.chermn.dao;

import com.example.chermn.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class QuizAttemptDAO {

    //saves the score from the quiz
    public void saveQuizAttempt(int userId, int categoryId, double score, boolean isPassed) {

        String sql = """
            INSERT INTO QUIZ_ATTEMPT (user_id, category_id, score, is_passed)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, categoryId);
            stmt.setDouble(3, score);
            stmt.setBoolean(4, isPassed);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}