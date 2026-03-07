package studentmanagementsystem;

import database.DatabaseConnection;
import GUI.LoginPage;  // Make sure your login page class is imported
import java.sql.Connection;

import javax.swing.SwingUtilities;

public class StudentManagementSystem {

    public static void main(String[] args) {
        // Step 1: Connect to the database
        Connection conn = DatabaseConnection.getConnection();

        if (conn != null) {
            System.out.println("Database is working!");

            // Step 2: Launch GUI safely on Event Dispatch Thread
            SwingUtilities.invokeLater(() -> {
                new LoginPage().setVisible(true);
            });

        } else {
            System.out.println("Database connection error!");
        }

        // Optional: Add a shutdown hook to close connection on exit
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            DatabaseConnection.closeConnection();
//        }));
    }
}