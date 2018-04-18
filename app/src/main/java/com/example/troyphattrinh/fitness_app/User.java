package com.example.troyphattrinh.fitness_app.Model;

public class User {

    private String name;
    private String password;
    private String dob;
    private String email;


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getDob(){
        return dob;
    }

    public void setId(String dob){
        this.dob = dob;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
