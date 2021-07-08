package com.github.perscholas.model;

// TODO - Annotate and Implement respective interface and define behaviors

import javax.persistence.*;
import java.util.Objects;

/**
 * Implemented by Monica Deshmukh
 * 9/6/2020
 */
@Entity
@Table(name="Course")
public class Course implements CourseInterface {
    @Id
    private Integer id;

    @Column
    @Basic
    private String name;

    @Column
    @Basic
    private String instructor;

    /*@ManyToMany(mappedBy = "sCourses")    //maps to the list object in the Student class
     List<StudentInterface> studentCoursesList;*/

    //nullary constructor
    public Course(){
        this(null,null,null);
    }

    //non-nullary constructor
    public Course(Integer id, String name, String instructor){
        this.id = id;
        this.name = name;
        this.instructor = instructor;
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
      return "[Id: " + id + " Name: " +  name + " Instructor: " + instructor + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(name, course.name) &&
                Objects.equals(instructor, course.instructor);
    }
}
