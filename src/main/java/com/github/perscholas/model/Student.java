package com.github.perscholas.model;

import javax.persistence.*;
import java.util.Set;

// TODO - Annotate and Implement respective interface and define behaviors

@Entity
@Table(name="student")
public class Student implements StudentInterface {
    @Id
    private String email;

    @ManyToMany
            @JoinTable(
                    name = "student_course",
                    joinColumns = @JoinColumn(name = "email"),
                    inverseJoinColumns = @JoinColumn(name = "course_id")
            )
    Set<Course> studentCourse;

    private String name;
    private String password;

    @Column (name = "name")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column (name= "email")
    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column (name= "password")
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //Nullary constructor
    public Student(){
    Student student = new Student();
    }

    public Student(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
