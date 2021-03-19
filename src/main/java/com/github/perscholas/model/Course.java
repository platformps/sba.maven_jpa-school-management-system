package com.github.perscholas.model;

import javax.persistence.*;
import java.util.Objects;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name = "course")
public class Course implements CourseInterface{
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "instructor", nullable = false, length = 50)
    private String instructor;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "student")
    private Student student;

    public Course(){}

    public Course(Integer id, String name, String instructor) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getInstructor() {
        return this.instructor;
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
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(name, course.name) &&
                Objects.equals(instructor, course.instructor);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
