
package database;
import java.sql.*;
public class DatabaseConnection {
    private static final String URL="jdbc:mysql://localhost:3306/school"; 
    private  static final  String USER="root";
    private static final String PASSWORD="1234";
    public static Connection getConnection(){
        
    try{
        Connection conn=DriverManager.getConnection(URL,USER,PASSWORD);
        System.out.print("connected successfully!");
        return conn;
        }
    catch(SQLException e){
        System.out.println("Connection failled");
         e.printStackTrace();
         return null;
    }
}
}
