package database;

import java.sql.Connection;
import java.sql.Statement;

public class InitDatabase {
    public static void main(String[] args) {
        String usersTable = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT NOT NULL UNIQUE,"
                + "email TEXT NOT NULL UNIQUE,"
                + "password TEXT NOT NULL"
                + ");";

        String studentsTable = "CREATE TABLE IF NOT EXISTS students ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "email TEXT NOT NULL,"
                + "course TEXT NOT NULL,"
                + "marks REAL NOT NULL,"
                + "user_id INTEGER NOT NULL,"
                + "FOREIGN KEY(user_id) REFERENCES users(id)"
                + ");";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(usersTable);
            stmt.execute(studentsTable);

            System.out.println("Tables created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}