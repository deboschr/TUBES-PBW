package com.runtracker.dao;

import com.runtracker.config.DatabaseConfig;
import com.runtracker.models.Activity;
import com.runtracker.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class ActivityDAO extends DatabaseConfig {

   public List<Activity> findByUser(User user) {
      List<Activity> activities = new ArrayList<>();
      String sql = "SELECT a.*, mr.activity_id AS is_race " +
            "FROM activity a " +
            "LEFT JOIN member_race mr ON a.activity_id = mr.activity_id " +
            "WHERE a.pengguna_id = ?";

      try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

         stmt.setLong(1, user.getId());
         ResultSet rs = stmt.executeQuery();

         while (rs.next()) {
            Activity activity = mapRowToActivity(rs);
            boolean isRace = rs.getLong("is_race") != 0;
            activity.setType(isRace ? "Race" : "Exercise"); // Tentukan type di DAO
            activities.add(activity);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return activities;
   }

   public Optional<Activity> findById(long id) {
      String sql = "SELECT * FROM activity WHERE activity_id = ?";
      try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setLong(1, id);
         ResultSet rs = stmt.executeQuery();
         if (rs.next()) {
            return Optional.of(mapRowToActivity(rs));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return Optional.empty();
   }

   public void save(Activity activity) {
      String sql = "INSERT INTO activity (pengguna_id, title, description, duration, distance, created_at) VALUES (?, ?, ?, ?, ?, ?)";
      try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

         stmt.setLong(1, activity.getPengguna().getId());
         stmt.setString(2, activity.getTitle());
         stmt.setString(3, activity.getDescription());
         stmt.setDouble(4, activity.getDuration());
         stmt.setDouble(5, activity.getDistance());
         stmt.setLong(6, System.currentTimeMillis());
         stmt.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void update(Activity activity) {
      String sql = "UPDATE activity SET title = ?, description = ?, duration = ?, distance = ?, updated_at = ? WHERE activity_id = ?";
      try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

         stmt.setString(1, activity.getTitle());
         stmt.setString(2, activity.getDescription());
         stmt.setDouble(3, activity.getDuration());
         stmt.setDouble(4, activity.getDistance());
         stmt.setLong(5, activity.getUpdatedAt());
         stmt.setLong(6, activity.getId());
         stmt.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void delete(Long id) {
      String sql = "DELETE FROM activity WHERE activity_id = ?";
      try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

         stmt.setLong(1, id);
         stmt.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   private Activity mapRowToActivity(ResultSet rs) throws SQLException {
      Activity activity = new Activity();
      activity.setId(rs.getLong("activity_id"));
      activity.setTitle(rs.getString("title"));
      activity.setDescription(rs.getString("description"));
      activity.setDuration(rs.getDouble("duration"));
      activity.setDistance(rs.getDouble("distance"));
      activity.setCreatedAt(rs.getLong("created_at"));
      activity.setUpdatedAt(rs.getLong("updated_at"));
      return activity;
   }

}
