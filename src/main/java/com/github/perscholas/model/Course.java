package com.github.perscholas.model;

import javax.persistence.*;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name="course")
public class Course implements CourseInterface{
    @Id
    @Column(name="id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "instructor")
    private String instructor;


    public Course() {
    }

    public Course(Integer id, String name, String instructor) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
