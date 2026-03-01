
package database;
import java.sql.*;
public class DatabaseConnection {
    private static final String URL="";
    private  static final  String User="";
    private static final String Password="";
    public static Connection getConnection(){
        
    try{
        Connection conn=DriverManager.getConnection(URL,User,Password);
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
