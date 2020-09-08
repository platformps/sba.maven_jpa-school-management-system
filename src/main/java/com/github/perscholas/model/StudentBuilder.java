package com.github.perscholas.model;

public class StudentBuilder {
    private String email;
    private String password;
    private String name;

    public StudentBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Student createStudent() {
        return new Student(email, password, name);
    }
}