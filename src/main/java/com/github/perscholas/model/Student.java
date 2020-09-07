package com.github.perscholas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "Student")
public class Student implements StudentInterface {
    
    @Id
    private String email;
    private String name;
    private String password;
    
    @OneToMany
    private List<Course> courses;
    
    public Student() {
    }
    
    public Student(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    @Override
    public List<Course> getCourses() {
        return courses;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", courses=" + courses +
                '}';
    }
}
