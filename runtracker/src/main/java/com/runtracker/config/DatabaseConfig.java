package com.runtracker.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConfig {
   private static final String DB_URL = "jdbc:postgresql://localhost:5432/runtracker";
   private static final String DB_USER = "your_db_user";
   private static final String DB_PASSWORD = "your_db_password";

   protected Connection getConnection() throws SQLException {
      return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
   }
}