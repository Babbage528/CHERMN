package com.example.chermn.dao;

import com.example.chermn.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserProgressDAO {

    //updates the farm (progress)
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

    //returns the current progress
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