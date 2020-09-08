package com.github.perscholas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
public class Student implements StudentInterface{

    @Id
    private String email;
    private String name;
    private String password;

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setPassword(String password) {

    }
}
