package com.github.perscholas.model;

import javax.persistence.*;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name = "course")
public class Course implements CourseInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "instructor", nullable = false, length = 50)
    private String instructor;


    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getInstructor() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setInstructor(String instructor) {

    }
}
