package model;

public abstract class person {
    protected String name;
    protected String Email;
    public person(String name,String Email){
        this.name=name;
        this.Email=Email;
    }
    
    public abstract void display();
}
