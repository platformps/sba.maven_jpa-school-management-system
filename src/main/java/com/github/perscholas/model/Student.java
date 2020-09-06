package com.github.perscholas.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name = "student")
public class Student implements StudentInterface, Serializable {
    @Id
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "password")
    private String password;

    @ManyToMany()
    @JoinTable(name = "student_course", joinColumns = {@JoinColumn(name = "email")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<CourseInterface> courses=new ArrayList<>();

    public List<CourseInterface> getCourses() {
        return courses;
    }

    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Student() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", courses=" + courses.stream().map(CourseInterface::getId).collect(Collectors.toList()) +
                '}';
    }
}
