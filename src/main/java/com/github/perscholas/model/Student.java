package com.github.perscholas.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name = "Student")
public class Student implements StudentInterface {

    @Id
    @Column(name = "email", length = 50)
    private String email;

    @Basic
    @Column(name = "name", length = 50)
    private String name;

    @Basic
    @Column(name = "password", length = 50)
    private String password;
    private List<CourseInterface> courses;

    public Student(){
        this(null,null,null, null);
    }
    public Student(String email,String name, String password, List<CourseInterface> courses){
        this.email = email;
        this.name = name;
        this.password = password;
        this.courses = courses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (getEmail() != null ? !getEmail().equals(student.getEmail()) : student.getEmail() != null) return false;
        if (getName() != null ? !getName().equals(student.getName()) : student.getName() != null) return false;
        if (getPassword() != null ? !getPassword().equals(student.getPassword()) : student.getPassword() != null)
            return false;
        return Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", courses=" + courses +
                '}';
    }
}
