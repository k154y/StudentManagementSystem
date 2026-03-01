package model;

public abstract class person {
    protected String name;
    protected String email;
    public person(String name,String email){
        this.name=name;
        this.email=email;
    }
    
    public abstract void display();
}
