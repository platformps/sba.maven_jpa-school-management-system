package com.github.perscholas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Student implements StudentInterface {

    @Id
    private String email;
    private String name;
    private String password;
    private List<CourseInterface> studentCourses;

    public Student() {
        studentCourses = new ArrayList<>();
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

    public List<CourseInterface> getStudentCourses() {
        return studentCourses;
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
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", studentCourses=" + studentCourses +
                '}';
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
