package com.github.perscholas.model;

public class CourseBuilder {
    private Integer id;
    private String name;
    private String instructor;

    public CourseBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public CourseBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder setInstructor(String instructor) {
        this.instructor = instructor;
        return this;
    }

    public Course createCourse() {
        return new Course(id, name, instructor);
    }
}