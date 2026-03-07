package studentmanagementsystem;

import database.DatabaseConnection;
import GUI.LoginPage;
import GUI.MainPage;
import model.User;

import java.sql.Connection;
import java.util.prefs.Preferences;
import javax.swing.SwingUtilities;

public class StudentManagementSystem {

    public static void main(String[] args) {
        // 1. Connect to the database
        Connection conn = DatabaseConnection.getConnection();

        if (conn != null) {
            System.out.println("Database is working!");

            // 2. Check saved preferences for Remember Me
            Preferences prefs = Preferences.userRoot().node(LoginPage.class.getName());
            int savedUserId = prefs.getInt("user_id", -1);

            if (savedUserId != -1) {
                // Auto-login saved user
                String username = prefs.get("user_name", "");
                String emailSaved = prefs.get("user_email", "");
                User savedUser = new User(savedUserId, username, emailSaved, "");

                // Open MainPage directly
                SwingUtilities.invokeLater(() -> {
                    MainPage main = new MainPage(savedUser);
                    main.setVisible(true);
                });
            } else {
                // No saved user → show login page
                SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
            }

        } else {
            System.out.println("Database connection error!");
        }
    }
}