package com.github.perscholas.model;

public final class StudentBuilder {
    private String email;
    private String name;
    private String password;

    public StudentBuilder() {
    }

    public static StudentBuilder aStudent() {
        return new StudentBuilder();
    }

    public StudentBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public Student build() {
        Student student = new Student();
        student.setEmail(email);
        student.setName(name);
        student.setPassword(password);
        return student;
    }
}
