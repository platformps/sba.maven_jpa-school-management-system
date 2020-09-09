package com.github.perscholas.model;


import javax.persistence.*;
import java.util.List;

// Annotate and Implement respective interface and define behaviors

@Entity
@TableGenerator(name = "student", pkColumnValue="email")

public class Student implements StudentInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
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


    public String toString() {
        return "[email= " + email +
                " name= " + name +
                " password= " + password + "]";
    }

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

}
