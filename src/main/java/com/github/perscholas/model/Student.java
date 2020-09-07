package com.github.perscholas.model;

import javax.persistence.*;
import java.util.Set;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name = "Student")
public class Student implements StudentInterface {

    @Id
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "password")
    private String password;

//    @ManyToMany
//    @JoinTable(
//            name = "course_registered",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "course_id"))
//    Set<Course> registeredCourses;


    public Student() {

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

    public void setPassword(String password) {
        this.password = password;
    }
}
