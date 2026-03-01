
package model;
import java.util.Scanner;
import database.DatabaseOperations;

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
    @Override
    public void display(){
        System.out.printf("ID:%d %nName:%s %nCourse:%s %nMarks:%.2f %n",
                  id, name, course, marks);
    }
    @Override
    public void add(){
        System.out.print("Enter name and Email");
        email=sc.nextLine();
        name=sc.nextLine(); 
    }
    @Override
    public void delete(){
         System.out.print("Enter name and Email");
    }
    @Override
    public void update(){
         System.out.print("Enter name and Email");
    }
    @Override
    public void search(String keyword){
         System.out.print("Enter name and Email"+keyword);
    }
}
