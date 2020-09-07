package com.github.perscholas.model;

// TODO - Annotate and Implement respective interface and define behaviors

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Implemnted by Monica Deshmukh
 * 9/6/2020
 */
@Entity
@Table(name="Student")
public class Student implements StudentInterface{
    @Id
    private String email;

    @Column
    @Basic
    private String name;

    @Column
    @Basic
    private String password;

    /*@ManyToMany
    @JoinTable(
            name = "StudentCourses",
            joinColumns = @JoinColumn(name = "email"),
            inverseJoinColumns = @JoinColumn(name = "id"))*/
    private List<CourseInterface> sCourses;

    //nullary constructor
    public Student(){
        this(null,null,null, null);
    }

    //non-nullary constructors
    public Student(String email, String name, String password){
        this(email, name, password,null);
    }

    public Student(String email, String name, String password, List<CourseInterface> sCourses){
        this.email = email;
        this.name = name;
        this.password = password;
        this.sCourses = sCourses;
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
        this.email = email;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public void setCourses(List<CourseInterface> sCourses) {this.sCourses = sCourses;}

    public List<CourseInterface> getCourses() {return this.sCourses;}

    @Override
    public String toString() {
        return "[ Email: " + email + " Name: " + name + " Password: " + password + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return email.equals(student.email) &&
                Objects.equals(name, student.name) &&
                Objects.equals(password, student.password);
    }

}
