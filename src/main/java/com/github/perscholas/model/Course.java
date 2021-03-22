package com.github.perscholas.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

// Annotate and Implement respective interface and define behaviors
@Entity
@TableGenerator(name = "course")

public class Course implements CourseInterface {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String instructor;

    @Column
    private String name;


    public Course() {
    }

    public Course(Integer id, String instructor, String name) {
        this.id = id;
        this.instructor = instructor;
        this.name = name;
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
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }


    @Override
    public String toString() {
        return "[id: " + id +
                " name: " +  name +
                " Instructor: " + instructor + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Course)) return false;
        Course course = (Course) obj;
        return Objects.equals(id, course.id) &&
                Objects.equals(name, course.name) &&
                Objects.equals(instructor, course.instructor);
    }
}
