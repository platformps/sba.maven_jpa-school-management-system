package com.github.perscholas.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name="Course")
public class Course implements CourseInterface {
    @Id
    @Column(name="id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "instructor")
    private String instructor;

    ///ATTEMPT AT JPA TABLE JOIN
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "email") })
    private List<Student> studentList;
    //////////////////

    public Course () {

    }

    public Course (Integer id, String name, String instructor) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getInstructor() {
        return instructor;
    }

    @Override
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return getName() + " with instructor " + getInstructor()
                + " (course ID: " + getId() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(name, course.name) &&
                Objects.equals(instructor, course.instructor) &&
                Objects.equals(studentList, course.studentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instructor, studentList);
    }

    public void add(Course course) {
    }
}
