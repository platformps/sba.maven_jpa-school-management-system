package com.github.perscholas.model;

// TODO - Annotate and Implement respective interface and define behaviors

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course implements CourseInterface {

    @Id
    @Column
    private Integer id;
    private String name;
    private String instructor;

    @Column (name= "id")
    @Basic
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column (name= "name")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column (name = "instructor")
    @Basic
    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Course(){
        Course course = new Course();
    }

    public Course( int id, String name, String instructor){
            this.id = id;
            this.name = name;
            this.instructor = instructor;
    }
}
