package com.github.perscholas.model;




public class StudentBuilder {
    private String email;
    private String name;
    private String password;

    public StudentBuilder() {
    }

    public StudentBuilder(Student dataToBeUpdated) {
        this.email = dataToBeUpdated.getEmail();
        this.name = dataToBeUpdated.getName();
        this.password = dataToBeUpdated.getPassword();
    }

    public StudentBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder setPassword(String password) {
        this.password = password;
        return this;
    }



    public Student build() {
        return new Student(email, name, password);
    }
}