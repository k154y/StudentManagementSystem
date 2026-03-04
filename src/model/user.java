/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author k.yves
 */
public class user 
{
    private int id;
    private String password;
    private String username;
    private String  email;
    public user(int id,String password,String username,String email){
        this.id=id;
        this.password=password;
        this.username=username;
        this.email=email;
    }
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
