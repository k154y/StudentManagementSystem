
package model;
import java.util.Scanner;
import database.DatabaseOperations;
import database.DatabaseConnection;
import java.sql.*;

public class Student extends Person implements DatabaseOperations  {
    private int id;
    private String course;
    private double marks;
    private Scanner sc=new Scanner(System.in);
    
    public Student(int id,String course,String name,String email, double marks){
        super(name,email);
        this.id=id;
        this.course=course;
        this.marks=marks;
        
    }
    public static ResultSet getAll(int userId){
    try{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM students WHERE user_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        return ps.executeQuery();
    }catch(SQLException e){
        e.printStackTrace();
    }
    return null;
}
    @Override
    public void display(){
        System.out.printf("ID:%d %nName:%s %nCourse:%s %nMarks:%.2f %n",
                  id, name, course, marks);
    }
    @Override
    public void add(int userId){
    try{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO students(name,email,course,marks,user_id) VALUES(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, course);
        ps.setDouble(4, marks);
        ps.setInt(5, userId);

        ps.executeUpdate();
        conn.close();

    } catch(SQLException e){
        e.printStackTrace();
    }
}
    @Override
    public void delete(){
        try{
        Connection conn=database.DatabaseConnection.getConnection();
        String sql="DELETE FROM students WHERE id=? ";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        conn.close();
          }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Deleted Succesfully");
        }
    }
    @Override
    public void update(){
          String sql = "UPDATE students SET name=?, email=?, course=?, marks=? WHERE id=?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, course);
        ps.setDouble(4, marks);
        ps.setInt(5, id);


    } catch (SQLException e) {
        e.printStackTrace();
        
    }
         
    }
    @Override
    public void search(String keyword){
         
    String sql = "SELECT * FROM students WHERE name LIKE ? OR email LIKE ? OR course LIKE ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        String searchValue = "%" + keyword + "%";

        ps.setString(1, searchValue);
        ps.setString(2, searchValue);
        ps.setString(3, searchValue);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Course: " + rs.getString("course"));
            System.out.println("Marks: " + rs.getDouble("marks"));
            System.out.println("------------------------");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    public static ResultSet searchResult(String keyword) throws SQLException {
    String sql = "SELECT * FROM students WHERE name LIKE ? OR email LIKE ? OR course LIKE ?";

    Connection conn = DatabaseConnection.getConnection();
    PreparedStatement ps = conn.prepareStatement(sql);

    String searchValue = "%" + keyword + "%";
    ps.setString(1, searchValue);
    ps.setString(2, searchValue);
    ps.setString(3, searchValue);

    return ps.executeQuery(); // return ResultSet to caller
}
}
