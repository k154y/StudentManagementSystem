
package model;
import java.util.Scanner;
import database.DatabaseOperations;

public class Student extends Person implements DatabaseOperations  {
    private int id;
    private String course;
    private double marks;
    private Scanner sc=new Scanner(System.in);
    
    public Student(int id,String course,String name,String email, double marks){
        super(String name,String email);
        this.id=id;
        this.course=course;
        this.marks=marks;
        
    }
    @Override
    void display(){
        System.out.printf("ID:%d %n Name:%s %n Course:%s %n Marks:%d %n",id,name,course,marks);
    }
    @Override
    void add(){
        system.out.print("Enter name and Email");
        email=sc.nextLine();
        name=sc.nextLine(); 
    }
    @Override
    void delete(){
         system.out.print("Enter name and Email");
    }
    @Override
    void update(){
         system.out.print("Enter name and Email");
    }
    @Override
    void search(){
         system.out.print("Enter name and Email");
    }
}
