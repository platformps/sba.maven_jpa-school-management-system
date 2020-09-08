package com.github.perscholas.model;

import javax.persistence.*;
import java.util.Objects;
import javax.persistence.*;
import java.util.Set;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name = "Course")
public class Course implements CourseInterface{

    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
}
