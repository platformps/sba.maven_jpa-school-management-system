package com.github.perscholas.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Student", schema = "school_system")
public class Student implements StudentInterface {
    private String email;
    private String name;
    private String password;

    public Student(){
    }
    public Student(String email,String name, String password){
        this.email = email;
        this.name = name;
        this.password = password;
    }

    @Id
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student that = (Student) o;

        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
