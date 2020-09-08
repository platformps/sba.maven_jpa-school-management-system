package com.github.perscholas.model;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name = "Course")
public class Course implements CourseInterface {

    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "instructor")
    private String instructor;

public Course() {

}

public Integer getId() {
    return id;
}

public void setID(Integer id) {
    this.id = id;
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

public void setInstructor(String instructor)  {
    this.instructor = instructor;
}

}