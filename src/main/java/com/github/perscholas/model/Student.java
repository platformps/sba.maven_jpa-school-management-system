package com.github.perscholas.model;

// TODO - Annotate and Implement respective interface and define behaviors

import javax.persistence.*;

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
