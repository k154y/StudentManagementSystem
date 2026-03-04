
package studentmanagementsystem;

import database.DatabaseConnection;
import java.sql.Connection;
public class StudentManagementSystem {

  
    public static void main(String[] args) {
          Connection conn = DatabaseConnection.getConnection();

        if(conn != null){
            System.out.println("Database is working!");
        }
        else{
            System.out.println("Database connection error!");
        }
    }
    
}
