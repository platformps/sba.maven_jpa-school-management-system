package com.github.perscholas.model;

import com.github.perscholas.service.CourseService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name = "student")
public class Student implements StudentInterface{

    @Id
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "name")
     private  String name;

    @Basic
    @Column(name = "password")
     private String password;

    private List<CourseInterface> studentCourses;

    public Student(){
        studentCourses = new ArrayList<>();
    }

    public Student(String name, String email, String password){
        this.name=name;
        this.email=email;
        this.password=password;
    }

@Override
    public List<CourseInterface> getClasses(){

        return studentCourses;
    }

    public void addClasses(int course){
        CourseService service= new CourseService();

        studentCourses.add(service.getAllCourses().stream()
        .filter(c ->c.getId()
                .equals(course))
                .findFirst()
                .orElse(null));
    }

    public void removeClasses(int id){
        for (CourseInterface course:studentCourses) {
            if(id == course.getId()) studentCourses.remove(course);
        }
    }

    public String getId(){
        return getEmail();
}



    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setEmail(String email) {
this.email=email;
    }

    @Override
    public void setName(String name) {
this.name=name;
    }

    @Override
    public void setPassword(String password) {
this.password=password;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", Classes='"+ getClasses().toString()+'}';}
}
