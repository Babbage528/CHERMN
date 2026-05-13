package com.example.chermn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.chermn.DatabaseConnection;

/**
 * Handles user progress database operations
 */
public class UserProgressDAO {

    /**
     * Ypdates user progress for a category.
     * @param userId user ID
     * @param categoryId category ID
     * @param newStage new progress stage
     */
    public void updateProgress(int userId, int categoryId, int newStage) {

        String sql = """
            UPDATE USER_PROGRESS
            SET current_growth_stage = ?
            WHERE user_id = ? AND category_id = ?
        """;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newStage);
            stmt.setInt(2, userId);
            stmt.setInt(3, categoryId);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getting current progress for a user/category
     * @param userId user ID
     * @param categoryId category ID
     * @return current progress stage
     */
    public int getProgress(int userId, int categoryId) {

        String sql = """
            SELECT current_growth_stage
            FROM USER_PROGRESS
            WHERE user_id = ? AND category_id = ?
        """;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, categoryId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("current_growth_stage");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}