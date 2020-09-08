package com.github.perscholas.model;

import org.eclipse.persistence.annotations.PrimaryKey;

import javax.persistence.*;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
public class Course implements CourseInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
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
