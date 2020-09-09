package com.github.perscholas.model;

import javax.persistence.*;
import java.util.List;

// Annotate and Implement respective interface and define behaviors
@Entity
@TableGenerator(name = "course", pkColumnValue="id")

public class Course implements CourseInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "instructor")
    private String instructor;

    @Column(name = "name")
    private String name;

    public List<Course> course;

    public Course(String id, String name, String instructor){
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


    public List<Course> getCourses() {
        return course;
    }

    public void setCourses(List<Course> course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "[id= " + id +
                " name= " + name +
                " instructor= " + instructor + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Course)) return false;
        if (obj == null) return false;
        Course course = (Course) obj;
        return course.id == id &&
                course.name.equals(name) &&
                course.instructor.equals(instructor);
    }

}
