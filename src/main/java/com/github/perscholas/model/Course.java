package com.github.perscholas.model;

import javax.persistence.*;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name = "Course")
public class Course implements CourseInterface{
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = " name")
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
        return instructor;
    }

    @Override
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';
    }
}
