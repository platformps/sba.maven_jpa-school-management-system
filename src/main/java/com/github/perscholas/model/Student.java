package com.github.perscholas.model;

// TODO - Annotate and Implement respective interface and define behaviors

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student implements StudentInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;


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
}
