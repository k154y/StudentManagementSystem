
package GUI;
import java.sql.*;
import javax.swing.JOptionPane;
import database.DatabaseConnection;
import model.User;
import java.util.prefs.Preferences;


public class LoginPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginPage.class.getName());
     private Preferences prefs = Preferences.userRoot().node(this.getClass().getName());
    
  

    public LoginPage() {
        initComponents();
        Register.addActionListener(this::RegisterActionPerformed);
      
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        Login = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        remember_me = new javax.swing.JCheckBox();
        successfull = new javax.swing.JLabel();
        progressbar = new javax.swing.JProgressBar();
        Reset = new javax.swing.JButton();
        Register = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(230, 240, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Login");

        email.setText("Email");

        Login.setBackground(new java.awt.Color(55, 112, 248));
        Login.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Login.setForeground(new java.awt.Color(255, 255, 255));
        Login.setText("Login");
        Login.addActionListener(this::LoginActionPerformed);

        jLabel3.setText("New User? ");

        password.setText("Password");

        remember_me.setBackground(new java.awt.Color(230, 240, 255));
        remember_me.setText("Remember Me");

        successfull.setBackground(new java.awt.Color(255, 51, 0));

        Reset.setText("Reset");

        Register.setBackground(new java.awt.Color(55, 112, 248));
        Register.setText("Register Here");
        Register.addActionListener(this::RegisterActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Register)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(successfull, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remember_me)
                    .addComponent(progressbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(Login)
                .addGap(23, 23, 23)
                .addComponent(Reset)
                .addGap(18, 18, 18)
                .addComponent(remember_me)
                .addGap(31, 31, 31)
                .addComponent(successfull)
                .addGap(18, 18, 18)
                .addComponent(progressbar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(Register))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        email.getAccessibleContext().setAccessibleName("");
        email.getAccessibleContext().setAccessibleDescription("");
        email.getAccessibleContext().setAccessibleParent(email);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
     try {
        String userEmail = email.getText().trim();
        String userPass = new String(password.getPassword()).trim();

        if (userEmail.isEmpty() || userPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter email and password!");
            return;
        }

        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);

        pst.setString(1, userEmail);
        pst.setString(2, userPass);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            // Get user data
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String emailDB = rs.getString("email");
            String passwordDB = rs.getString("password");

            // Create User object
            User loggedUser = new User(id, username, emailDB, passwordDB);

            // Handle Remember Me using Preferences
            java.util.prefs.Preferences prefs = java.util.prefs.Preferences.userRoot().node(this.getClass().getName());
            if (remember_me.isSelected()) {
                prefs.putInt("user_id", id);
                prefs.put("user_email", emailDB);
                prefs.put("user_name", username);
            } else {
                prefs.remove("user_id");
                prefs.remove("user_email");
                prefs.remove("user_name");
            }

            // Show success message
            successfull.setText("Login Successful! Loading...");
            progressbar.setValue(0);
            progressbar.setVisible(true);

            // Progress bar animation
            new Thread(() -> {
                for (int i = 0; i <= 100; i += 10) {
                    try {
                        Thread.sleep(50); // adjust speed of progress
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    final int value = i;
                    progressbar.setValue(value);
                }

                // Open MainPage after loading
                java.awt.EventQueue.invokeLater(() -> {
                    MainPage main = new MainPage(loggedUser);
                    main.setVisible(true);
                    this.dispose(); // close login page
                });

            }).start();

        } else {
            JOptionPane.showMessageDialog(this, "Invalid Email or Password!");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_LoginActionPerformed

    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterActionPerformed
        // TODO add your handling code here:
        Register registerPage = new Register(); // create Register JFrame
    registerPage.setVisible(true);          // show the Register page
    this.dispose(); 
    }//GEN-LAST:event_RegisterActionPerformed

  
  public static void main(String args[]) {

    java.util.prefs.Preferences prefs =
        java.util.prefs.Preferences.userRoot().node(LoginPage.class.getName());

    int savedUserId = prefs.getInt("user_id", -1);

    if(savedUserId != -1){

        String username = prefs.get("user_name", "");
        String emailSaved = prefs.get("user_email", "");

        User savedUser = new User(savedUserId, username, emailSaved, "");

        java.awt.EventQueue.invokeLater(() -> {
            MainPage main = new MainPage(savedUser);
            main.setVisible(true);
        });

    } else {

        java.awt.EventQueue.invokeLater(() -> {
            new LoginPage().setVisible(true);
        });

    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Login;
    private javax.swing.JButton Register;
    private javax.swing.JButton Reset;
    private javax.swing.JTextField email;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField password;
    private javax.swing.JProgressBar progressbar;
    private javax.swing.JCheckBox remember_me;
    private javax.swing.JLabel successfull;
    // End of variables declaration//GEN-END:variables
}
