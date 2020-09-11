package com.github.perscholas.model;

import javax.persistence.*;

@Entity
@Table(name = "Course")
// TODO - Annotate and Implement respective interface and define behaviors
public class Course  implements CourseInterface{

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", length = 50)
    private String name;

    @Basic
    @Column(name = "instructor", length = 50)
    private String instructor;

    public Course(){
        this(null,null,null);
    }

    public Course(Integer id, String name, String instructor){
        this.id =id;
        this.name = name;
        this.instructor = instructor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;

        Course course = (Course) o;

        if (getId() != null ? !getId().equals(course.getId()) : course.getId() != null) return false;
        if (getName() != null ? !getName().equals(course.getName()) : course.getName() != null) return false;
        return getInstructor() != null ? getInstructor().equals(course.getInstructor()) : course.getInstructor() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getInstructor() != null ? getInstructor().hashCode() : 0);
        return result;
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