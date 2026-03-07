package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static Connection conn = null;
    // Relative path for portability
    private static final String URL = "jdbc:sqlite:./data/school.db";

    public static Connection getConnection() {
        try {
            // Connect only if connection is null or closed
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL);
                System.out.println("Connected to SQLite database!");

                // Create tables immediately
                createTables();
            }
        } catch (SQLException e) {
            System.out.println("Database connection error!");
            e.printStackTrace();
        }
        return conn;
    }

    private static void createTables() {
        try (Statement stmt = conn.createStatement()) {
            // Create users table
            String usersTable = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "username TEXT NOT NULL,"
                    + "password TEXT NOT NULL"
                    + ");";
            stmt.execute(usersTable);

            // Create students table (replace columns with your actual schema)
            String studentsTable = "CREATE TABLE IF NOT EXISTS students ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "age INTEGER,"
                    + "email TEXT"
                    + ");";
            stmt.execute(studentsTable);

            System.out.println("Tables created or already exist!");
        } catch (SQLException e) {
            System.out.println("Error creating tables!");
            e.printStackTrace();
        }
    }

    // Close connection on app exit
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}