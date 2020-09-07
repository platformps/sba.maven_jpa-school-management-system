package com.github.perscholas.model;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name = "course")
public class Course implements CourseInterface {

    @Id
    @Column(name = "id")
    private Integer id;

    @Id
    @Column(name = "instructor")
    private String instructor;

    @Id
    @Column(name = "name")
    private String name;

    public Course(){

    }

    public Course(Integer id,String instructor,String name){
        this.id=id;
        this.instructor=instructor;
        this.name=name;
    }



    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getInstructor() {
        return instructor;
    }

    @Override
    public void setId(Integer id) {
this.id=id;
    }

    @Override
    public void setName(String name) {
this.name=name;
    }

    @Override
    public void setInstructor(String instructor) {
this.instructor=instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';}
}
