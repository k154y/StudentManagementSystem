package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static Connection conn = null;
    private static final String URL = "jdbc:sqlite:C:/Users/User/Desktop/school.db";

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL);
                System.out.println("Connected to SQLite database!");
                createTables(); // optional: create tables once
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS students (...)"; // your table schema
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}