package com.resume.util;

import java.sql.*;

public class DBManager {

    private static final String URL = "jdbc:sqlite:resume.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            createTableIfNotExists();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Establish Connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Create table if not exists
    private static void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "name TEXT, email TEXT, education TEXT, " +
                     "experience TEXT, skills TEXT)";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert User Data
    public static void saveUser(String name, String email, String education, String experience, String skills) {
        String sql = "INSERT INTO users (name, email, education, experience, skills) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, education);
            ps.setString(4, experience);
            ps.setString(5, skills);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
