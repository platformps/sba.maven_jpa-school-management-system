package com.github.perscholas.model;

// TODO - Annotate and Implement respective interface and define behaviors
public class Course implements CourseInterface {
    private Integer id;
    private String name;
    private String Instructor;

    public Course(int id, String name, String instructor) {
        this.id = id;
        this.name = name;
        Instructor = instructor;
    }

    public Course() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public String getInstructor() {
        return Instructor;
    }

    @Override
    public void setInstructor(String instructor) {
        Instructor = instructor;
    }
}
