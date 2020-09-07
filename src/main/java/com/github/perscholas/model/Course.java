package com.github.perscholas.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name="course", schema = "management_system")
public class Course implements CourseInterface, Serializable {
    @Id
    @Column(name="id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "instructor")
    private String instructor;

//    @ManyToMany(mappedBy = "courses")
//    @JoinTable(name = "student_course", joinColumns = {@JoinColumn(name = "email")},
//            inverseJoinColumns = {@JoinColumn(name = "id")})
 //   private List<Student> students;
    @ManyToOne()
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    //    public List<Student> getStudents() {
//        return students;
//    }

    public Course() {

    }

    public Course(Integer id, String name, String instructor) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }

    public Integer getId() {
        return id;
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

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

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
}
