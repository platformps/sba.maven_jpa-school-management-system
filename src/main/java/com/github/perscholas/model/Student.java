package com.github.perscholas.model;


import javax.persistence.*;
import java.util.Objects;

// Annotate and Implement respective interface and define behaviors

@Entity
@TableGenerator(name = "student")

public class Student implements StudentInterface{
    @Id
    @GeneratedValue
    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String password;

    public Student(){

    }

    public Student(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
        return "[ Email: " + email +
                " Name: " + name +
                " Password: " + password + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student student = (Student) obj;
        return email.equals(student.email) &&
                Objects.equals(name, student.name) &&
                Objects.equals(password, student.password);
    }
/*
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Course)) return false;
        if (obj == null) return false;
        Student student = (Student) obj;
        return student.email.equals(email) &&
                student.name.equals(name) &&
                student.password.equals(password);
    }
    *
 */

}
