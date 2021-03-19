package com.github.perscholas.model;

// TODO - Annotate and Implement respective interface and define behaviors

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student implements StudentInterface {



    @Id
    @Column(unique = true, nullable = false, name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public List<CourseInterface> getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(List<CourseInterface> studentCourse) {
        this.studentCourse = studentCourse;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private List<CourseInterface> studentCourse = new ArrayList<>();

    public Student(){}

    public Student(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
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

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(email, student.email) &&
                Objects.equals(password, student.password) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
